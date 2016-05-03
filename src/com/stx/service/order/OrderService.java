package com.stx.service.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.dao.ComplaintDao;
import com.stx.dao.GoodsInfoDao;
import com.stx.dao.OrderDao;
import com.stx.dao.OrderProcessDao;
import com.stx.dao.PriceInfoDao;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.entity.admin.Complaint;
import com.stx.entity.merchant.Goods;
import com.stx.entity.merchant.Merchant;
import com.stx.entity.merchant.Strategy;
import com.stx.entity.order.GoodsInfo;
import com.stx.entity.order.Order;
import com.stx.entity.order.OrderProcess;
import com.stx.entity.order.PriceInfo;
import com.stx.entity.user.Address;
import com.stx.entity.user.MerchantComment;
import com.stx.entity.vo.OrderDetailVo;
import com.stx.entity.vo.OrderProcessVo;
import com.stx.service.merchant.GoodsService;
import com.stx.service.merchant.MerchantService;
import com.stx.service.merchant.StrategyService;
import com.stx.service.user.MerchantCommentService;
import com.stx.util.CollectionUtils;
import com.stx.util.OrderNumber;

/**
 * 订单管理
 * 
 * @author gzzdsg 2016年3月14日
 */
@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private GoodsInfoDao goodsInfoDao;
	@Autowired
	private ComplaintDao complaintDao;
	@Autowired
	private StrategyService strategyService;
	@Autowired
	private PriceInfoDao priceInfoDao;
	@Autowired
	private OrderProcessDao orderProcessDao;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private MerchantCommentService merchantCommentService;

	/**
	 * 根据商户id和状态码查询
	 * 
	 * @param merchantId
	 * @param statusS
	 * @param statusE
	 * @return
	 */
	public List<Order> queryByMerchantAndStatus(Long merchantId,
			Integer statusS, Integer statusE) {
		return orderDao.findOrdersByStatus(merchantId, statusS, statusE);
	}

	/**
	 * 找寻用户订单信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<Order> queryByUserId(Long userId) {
		return orderDao.findOrdersByUid(userId);
	}

	/**
	 * 生成一个未支付订单
	 * 
	 * @param loginContext
	 * @param merchant
	 * @param address
	 * @param goodsList
	 * @param counts
	 */
	public Long generateOrder(User loginContext, Merchant merchant,
			Address address, List<Goods> goodsList, Integer[] counts) {
		/** 保存订单 **/
		Order order = new Order();
		order.setOrderNumber(OrderNumber.generator());
		order.setUserId(loginContext.getId());
		order.setMerchantId(merchant.getId());
		order.setCreateTime(new Date());
		order.setMerchantName(merchant.getName());
		String userAddr = address.getAddressAttr() + " "
				+ address.getHouseNumber() + " " + address.getRealName()
				+ address.getSex() + "收。  " + address.getMobile();
		order.setUserAddr(userAddr);
		order.setStatus(Constants.STATUS_NOTPAY);
		order.setDeliveryTime(merchant.getDeliveryTime());
		Long orderId = orderDao.saveOrder(order);

		/** 基础价格，用于计价 **/
		Float amount = 0f;

		/** 保存商品信息 **/
		GoodsInfo goodsInfo = null;
		for (int i = 0; i < goodsList.size(); i++) {
			Goods goods = goodsList.get(i);
			Integer count = counts[i];
			goodsInfo = new GoodsInfo();
			goodsInfo.setOrderId(orderId);
			goodsInfo.setGoodsId(goods.getId());
			goodsInfo.setGoodsName(goods.getName());
			goodsInfo.setGoodsCount(count);
			goodsInfo.setGoodsPrice(goods.getPrice());
			amount = amount + goods.getPrice() * count; // 累计总价
			goodsInfoDao.saveGoodsInfo(goodsInfo);
		}

		/** 保存消费信息 **/
		PriceInfo priceInfo = new PriceInfo();
		priceInfo.setOrderId(orderId);
		/** 动态计价 **/
		Float balance = calculateBalance(merchant.getId(), amount,
				loginContext.getId());
		priceInfo.setAmount(amount); // 设置商品总价
		priceInfo.setBalance(balance); // 设置优惠额度
		Float boxPrice = merchant.getBoxPrice();
		priceInfo.setBoxPrice(boxPrice); // 设置餐盒费
		Float deliveryPrice = merchant.getDeliveryPrice();
		priceInfo.setDeliveryPrice(deliveryPrice);// 设置配送费
		if (balance > amount) {
			balance = amount;
		}
		Float payPrice = amount - balance + boxPrice + deliveryPrice;
		priceInfo.setPayPrice(payPrice);
		priceInfoDao.savePriceInfo(priceInfo);

		return orderId;
	}

	/**
	 * 验证再下一单是否可行
	 * 
	 * @param oldOrderId
	 * @return
	 */
	public String validateAgain(Long oldOrderId) {
		Order oldOrder = orderDao.findOrdersById(oldOrderId);
		Merchant merchant = merchantService.getMerchantByMerchantId(oldOrder
				.getMerchantId());
		if (merchant.getOpenFlag() == Constants.FLAG_CLOSE) {
			return "商户以下架";
		}
		List<GoodsInfo> goodsInfoList = goodsInfoDao
				.findGoodsInfoByOid(oldOrderId);
		for (GoodsInfo goodsInfo : goodsInfoList) {
			if (goodsService.getOne(goodsInfo.getGoodsId()) == null) {
				return "订单中" + goodsInfo.getGoodsName() + "不再提供。";
			}
		}
		return null;
	}

	/**
	 * 再下一单
	 * 
	 * @param orderId
	 * @return
	 */
	public Long againOrder(Long oldOrderId, User loginContext) {
		Order oldOrder = orderDao.findOrdersById(oldOrderId);
		Merchant merchant = merchantService.getMerchantByMerchantId(oldOrder
				.getMerchantId());
		/** 保存订单 **/
		Order order = new Order();
		order.setOrderNumber(OrderNumber.generator());
		order.setUserId(loginContext.getId());
		order.setMerchantId(merchant.getId());
		order.setCreateTime(new Date());
		order.setMerchantName(merchant.getName());
		order.setUserAddr(oldOrder.getUserAddr());
		order.setStatus(Constants.STATUS_NOTPAY);
		order.setDeliveryTime(merchant.getDeliveryTime());
		Long orderId = orderDao.saveOrder(order);

		/** 获取商品信息 **/
		List<GoodsInfo> goodsInfoList = goodsInfoDao
				.findGoodsInfoByOid(oldOrderId);
		List<Goods> goodsList = new ArrayList<Goods>();
		List<Integer> counts = new ArrayList<Integer>();
		for (GoodsInfo goodsInfo : goodsInfoList) {
			goodsList.add(goodsService.getOne(goodsInfo.getGoodsId()));
			counts.add(goodsInfo.getGoodsCount());
		}

		/** 基础价格，用于计价 **/
		Float amount = 0f;

		/** 保存商品信息 **/
		GoodsInfo goodsInfo = null;
		for (int i = 0; i < goodsList.size(); i++) {
			Goods goods = goodsList.get(i);
			Integer count = counts.get(i);
			goodsInfo = new GoodsInfo();
			goodsInfo.setOrderId(orderId);
			goodsInfo.setGoodsId(goods.getId());
			goodsInfo.setGoodsName(goods.getName());
			goodsInfo.setGoodsCount(count);
			goodsInfo.setGoodsPrice(goods.getPrice());
			amount = amount + goods.getPrice() * count; // 累计总价
			goodsInfoDao.saveGoodsInfo(goodsInfo);
		}

		/** 保存消费信息 **/
		PriceInfo priceInfo = new PriceInfo();
		priceInfo.setOrderId(orderId);
		/** 动态计价 **/
		Float balance = calculateBalance(merchant.getId(), amount,
				loginContext.getId());
		priceInfo.setAmount(amount); // 设置商品总价
		priceInfo.setBalance(balance); // 设置优惠额度
		Float boxPrice = merchant.getBoxPrice();
		priceInfo.setBoxPrice(boxPrice); // 设置餐盒费
		Float deliveryPrice = merchant.getDeliveryPrice();
		priceInfo.setDeliveryPrice(deliveryPrice);// 设置配送费
		if (balance > amount) {
			balance = amount;
		}
		Float payPrice = amount - balance + boxPrice + deliveryPrice;
		priceInfo.setPayPrice(payPrice);
		priceInfoDao.savePriceInfo(priceInfo);

		return orderId;
	}

	/**
	 * 完成下单
	 * 
	 * @param orderId
	 * @param eatType
	 * @param payType
	 */
	public void createOrder(Long orderId, Integer eatType, Integer payType) {
		/** 补全订单信息 **/
		Order order = orderDao.findOrdersById(orderId);
		order.setEatType(eatType);
		order.setPayType(payType);
		order.setStatus(Constants.STATUS_WAITTING);
		orderDao.updateOrder(order);
		/** 添加订单进程 **/
		OrderProcess orderProcess = new OrderProcess();
		orderProcess.setOrderId(orderId);
		orderProcess.setOrderStatus(Constants.STATUS_WAITTING);
		orderProcess.setProcessDesc(Constants.MSG_WAITTING);
		orderProcess.setProcessStatus(Constants.STATUS_NAME_WAITTING);
		orderProcess.setProcessTime(new Date());
		orderProcessDao.saveOrderProcess(orderProcess);
	}

	/**
	 * 验证接收
	 * 
	 * @param orderId
	 * @return
	 */
	public String validateReceive(Long orderId) {
		Order order = orderDao.findOrdersById(orderId);
		if (order.getStatus() != Constants.STATUS_WAITTING) {
			return "状态不对，操作失败";
		}
		return null;
	}

	/**
	 * 验证备好
	 * 
	 * @param orderId
	 * @return
	 */
	public String validateReady(Long orderId) {
		Order order = orderDao.findOrdersById(orderId);
		if (order.getStatus() != Constants.STATUS_READING) {
			return "状态不对，操作失败";
		}
		return null;
	}

	/**
	 * 验证送出
	 * 
	 * @param orderId
	 * @return
	 */
	public String validateSend(Long orderId) {
		Order order = orderDao.findOrdersById(orderId);
		if (order.getStatus() != Constants.STATUS_OK) {
			return "状态不对，操作失败";
		}
		return null;
	}

	/**
	 * 接收订单
	 * 
	 * @param orderId
	 */
	public void receiveOrder(Long orderId) {
		Order order = orderDao.findOrdersById(orderId);
		order.setStatus(Constants.STATUS_READING);
		orderDao.updateOrder(order);
		/** 添加订单进程 **/
		OrderProcess orderProcess = new OrderProcess();
		orderProcess.setOrderId(orderId);
		orderProcess.setOrderStatus(Constants.STATUS_READING);
		orderProcess.setProcessDesc(Constants.MSG_READING);
		orderProcess.setProcessStatus(Constants.STATUS_NAME_READING);
		orderProcess.setProcessTime(new Date());
		orderProcessDao.saveOrderProcess(orderProcess);
	}

	/**
	 * 备好订单
	 * 
	 * @param orderId
	 */
	public void readyOrder(Long orderId) {
		Order order = orderDao.findOrdersById(orderId);
		order.setStatus(Constants.STATUS_OK);
		orderDao.updateOrder(order);
		/** 添加订单进程 **/
		OrderProcess orderProcess = new OrderProcess();
		orderProcess.setOrderId(orderId);
		orderProcess.setOrderStatus(Constants.STATUS_OK);
		orderProcess.setProcessDesc(Constants.MSG_OK);
		orderProcess.setProcessStatus(Constants.STATUS_NAME_OK);
		orderProcess.setProcessTime(new Date());
		orderProcessDao.saveOrderProcess(orderProcess);
	}

	/**
	 * 送出订单
	 * 
	 * @param orderId
	 */
	public void sendOrder(Long orderId) {
		Order order = orderDao.findOrdersById(orderId);
		order.setStatus(Constants.STATUS_ON_WAY);
		orderDao.updateOrder(order);
		/** 添加订单进程 **/
		OrderProcess orderProcess = new OrderProcess();
		orderProcess.setOrderId(orderId);
		orderProcess.setOrderStatus(Constants.STATUS_ON_WAY);
		orderProcess.setProcessDesc(Constants.MSG_ON_WAY);
		orderProcess.setProcessStatus(Constants.STATUS_NAME_ON_WAY);
		orderProcess.setProcessTime(new Date());
		orderProcessDao.saveOrderProcess(orderProcess);
	}

	/**
	 * 根据id查找订单信息
	 * 
	 * @param orderId
	 * @return
	 */
	public Order getOrderById(Long orderId) {
		return orderDao.findOrdersById(orderId);
	}

	/**
	 * 查询订单商品信息
	 * 
	 * @param orderId
	 * @return
	 */
	public List<GoodsInfo> queryGoodsInfoByOid(Long orderId) {
		return goodsInfoDao.findGoodsInfoByOid(orderId);
	}

	/**
	 * 查询订单费用
	 * 
	 * @param orderId
	 * @return
	 */
	public PriceInfo getOrderPriceByOid(Long orderId) {
		return priceInfoDao.findPriceInfoByOid(orderId);
	}

	/**
	 * 获取用户订单中心的展示对象
	 * 
	 * @param userId
	 * @return
	 */
	public List<OrderProcessVo> queryProcessVo(Long userId) {
		List<Order> orderList = orderDao.findOrdersByUid(userId);
		if (CollectionUtils.isEmpty(orderList)) {
			return null;
		}
		List<OrderProcessVo> orderVoList = new ArrayList<OrderProcessVo>();
		OrderProcessVo orderVo = null;
		for (Order order : orderList) {
			orderVo = new OrderProcessVo();
			orderVo.setOrder(order);
			orderVo.setLogoAddr(merchantService.getMerchantByMerchantId(
					order.getMerchantId()).getLogoAddr());
			orderVo.setPriceInfo(priceInfoDao.findPriceInfoByOid(order.getId()));
			orderVo.setProcessList(orderProcessDao.findProcessByOid(order
					.getId()));
			orderVoList.add(orderVo);
		}
		return orderVoList;
	}

	/**
	 * 计算优惠价格
	 * 
	 * @param merchantId
	 * @param amount
	 * @return
	 */
	private Float calculateBalance(Long merchantId, Float amount, Long userId) {
		/** 声明总体变量 **/
		Float surplus = amount; // 剩余
		Float balance = 0f; // 减免
		Strategy strategy = null; // 当前优惠

		/** 先计算新用户优惠 **/
		List<Strategy> xList = strategyService.listStrategyByType(merchantId,
				Constants.STRATEGY_XINYONGHU);
		if (CollectionUtils.isNotEmpty(xList)) {
			if (orderDao.isNewUser(userId)) {
				strategy = xList.get(0);
				surplus = surplus - strategy.getBalancePrice(); // 降价后价格变化
				balance = balance + strategy.getBalancePrice(); // 优惠价格变化
			}
		}

		/** 再计算满减活动 **/
		List<Strategy> mList = strategyService.listStrategyByType(merchantId,
				Constants.STRATEGY_MANJIAN);
		if (CollectionUtils.isNotEmpty(mList)) {
			for (int i = 0; i < mList.size(); i++) {
				strategy = mList.get(i);
				if (surplus < strategy.getPremisePrice()) {
					continue;
				}
				surplus = surplus - strategy.getBalancePrice(); // 降价后价格变化
				balance = balance + strategy.getBalancePrice(); // 优惠价格变化
				break;
			}
		}

		/** 最后计算折扣 **/
		List<Strategy> zList = strategyService.listStrategyByType(merchantId,
				Constants.STRATEGY_ZHEKOU);
		if (CollectionUtils.isNotEmpty(zList)) {
			for (int i = 0; i < zList.size(); i++) {
				strategy = zList.get(i);
				if (surplus < strategy.getPremisePrice()) {
					continue;
				}
				balance = balance + surplus
						* (1 - (strategy.getDiscount() / 10.0f)); // 优惠价格变化
			}
		}

		return balance;
	}

	/**
	 * 完成订单
	 * 
	 * @param orderId
	 * @return
	 */
	public String finishOrder(User loginContext, Long orderId, Integer buzz,
			String comment) {
		/** 订单状态设置为完成，并记录完成时间 **/
		Order order = orderDao.findOrdersById(orderId);
		if (order.getStatus() != 40 && order.getEatType() == 1) {
			return "该订单不能完成。";
		}
		if (order.getStatus() != 30 && order.getEatType() == 2) {
			return "该订单不能完成。";
		}
		if (buzz == null || comment == null) {
			return "评价和满意度不能为空。";
		}
		order.setStatus(Constants.STATUS_FINISH);
		order.setFinishTime(new Date());
		orderDao.updateOrder(order);

		/** 商品增加销量与按情况增加点赞量 **/
		List<GoodsInfo> goodsInfoList = goodsInfoDao
				.findGoodsInfoByOid(orderId);
		for (GoodsInfo goodsInfo : goodsInfoList) {
			goodsService.addGoodsMonthCount(goodsInfo.getGoodsId(),
					goodsInfo.getGoodsCount(), buzz);
		}

		/** 商户增加销量 **/
		merchantService.addMonthCount(order.getMerchantId());

		/** 增加商户评价 **/
		MerchantComment merchantComment = new MerchantComment();
		merchantComment.setCommentContent(comment);
		merchantComment.setCreateBy(loginContext.getId());
		merchantComment.setCreateName(loginContext.getNickName());
		merchantComment.setCreateTime(new Date());
		merchantComment.setMerchantId(order.getMerchantId());
		merchantCommentService.saveMerchantComment(merchantComment);

		/** 记录订单流程 **/
		OrderProcess orderProcess = new OrderProcess();
		orderProcess.setOrderId(orderId);
		orderProcess.setOrderStatus(Constants.STATUS_FINISH);
		orderProcess.setProcessDesc(Constants.MSG_FINISH);
		orderProcess.setProcessStatus(Constants.STATUS_NAME_FINISH);
		orderProcess.setProcessTime(new Date());
		orderProcessDao.saveOrderProcess(orderProcess);
		return null;
	}

	/** 取消订单 **/
	public String cancelOrder(Long orderId) {
		/** 设置订单状态为取消 **/
		Order order = orderDao.findOrdersById(orderId);
		if (order.getStatus() == 60) {
			return "该订单早已被取消。";
		}
		order.setStatus(Constants.STATUS_CANCEL);
		orderDao.updateOrder(order);

		/** 记录订单流程 **/
		OrderProcess orderProcess = new OrderProcess();
		orderProcess.setOrderId(orderId);
		orderProcess.setOrderStatus(Constants.STATUS_CANCEL);
		orderProcess.setProcessDesc(Constants.MSG_CANCEL);
		orderProcess.setProcessStatus(Constants.STATUS_NAME_CANCEL);
		orderProcess.setProcessTime(new Date());
		orderProcessDao.saveOrderProcess(orderProcess);
		return null;
	}

	/**
	 * 获取订单详情对象
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderDetailVo getOrderDetail(Long orderId) {
		Order order = orderDao.findOrdersById(orderId);
		if (order == null) {
			return null;
		}
		OrderDetailVo detailVo = new OrderDetailVo();
		detailVo.setOrder(order);
		detailVo.setGoodsInfoList(goodsInfoDao.findGoodsInfoByOid(orderId));
		detailVo.setPriceInfo(priceInfoDao.findPriceInfoByOid(orderId));
		detailVo.setOrderProcessList(orderProcessDao.findProcessByOid(orderId));

		return detailVo;
	}

	/**
	 * 评价订单
	 * 
	 * @param content
	 * @param orderId
	 * @param loginContext
	 * @return 失败原因，成功返回空
	 */
	public String complaintOrder(String content, Long orderId, User loginContext) {
		if (StringUtils.isEmpty(content)) {
			return "投诉内容为空。";
		}
		Order order = orderDao.findOrdersById(orderId);
		if (order == null) {
			return "订单不存在。";
		}
		Complaint complaint = new Complaint();
		complaint.setOrderId(orderId);
		complaint.setContent(content);
		complaint.setCreateTime(new Date());
		complaint.setMerchantId(order.getMerchantId());
		complaint.setMerchantName(order.getMerchantName());
		complaint.setUserId(loginContext.getId());
		complaint.setUserName(loginContext.getNickName());
		complaintDao.saveComplaint(complaint);
		return null;
	}

	// ========================管理员模块

	public List<Order> adminSearchOrder(String startDateStr, String endDateStr,
			String searchType, String searchValue) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate =null;
			Date endDate = null;
			if(StringUtils.isNotEmpty(startDateStr)){
				startDate =  sdf.parse(startDateStr);
			}
			if(StringUtils.isNotEmpty(endDateStr)){
				endDate = sdf.parse(endDateStr);
			}
			return orderDao.adminSearch(startDate, endDate, searchType, searchValue);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("管理员查询订单异常。。。");
		return null;
	}

}
