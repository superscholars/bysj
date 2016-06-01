package com.stx.action.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.entity.merchant.Goods;
import com.stx.entity.merchant.Merchant;
import com.stx.entity.order.GoodsInfo;
import com.stx.entity.order.Order;
import com.stx.entity.order.PriceInfo;
import com.stx.entity.user.Address;
import com.stx.service.merchant.MerchantService;
import com.stx.service.merchant.StrategyService;
import com.stx.service.order.OrderService;
import com.stx.service.user.AddressService;
import com.stx.util.CollectionUtils;
import com.stx.util.RequestUtils;

/**
 * 消费环节
 * @author gzzdsg
 * 2016年3月23日
 */
public class ShoppingAction extends ActionSupport implements ModelDriven<Address>{

	private static final long serialVersionUID = -1448838777921424131L;

	private Address address = new Address();
	
	@Override
	public Address getModel() {
		// TODO Auto-generated method stub
		return address;
	}
	
	/**
	 * 地址信息
	 */
	private Long addressId;
	
	/**
	 * 接收商品购买数量
	 */
	private Integer[] counts;
	

	public Integer[] getCounts() {
		return counts;
	}

	public void setCounts(Integer[] counts) {
		this.counts = counts;
	}
	
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Autowired
	private AddressService addressService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private StrategyService strategyService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 购物车主界面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String car(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User loginContext = RequestUtils.getUser(request);
		String sessionKey = loginContext.getLoginName()+Constants.GOU;
		Map<String ,Object> shopCar = (Map<String, Object>) session.getAttribute(sessionKey);
		if(session.getAttribute(sessionKey)==null){
			request.setAttribute(Constants.PAGE, "shopping");
			request.setAttribute("err", "购物车为空，请添加商品。");
			return "index";
		}
		List<Goods> goodsList = (List<Goods>) shopCar.get(Constants.GOODS_LIST);
		if(CollectionUtils.isEmpty(goodsList)){
			request.setAttribute(Constants.PAGE, "shopping");
			request.setAttribute("err", "购物车为空，请添加商品。");
			return "index";
		}
		request = shopCarCondition(request,shopCar,loginContext);
		return "index";
	}
	
	/**
	 * 删除购物车中的商品
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String removeGoods(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Long goodsId = RequestUtils.getId(request);
		User loginContext = RequestUtils.getUser(request);
		String sessionKey = loginContext.getLoginName()+Constants.GOU;
		Map<String ,Object> shopCar = (Map<String, Object>) session.getAttribute(sessionKey);
		if(session.getAttribute(sessionKey)==null){
			request.setAttribute(Constants.PAGE, "shopping");
			request.setAttribute("err", "购物车为空，请添加商品。");
			return "index";
		}
		/** 删除购物车中的指定的商品 **/
		List<Goods> goodsList = (List<Goods>) shopCar.get(Constants.GOODS_LIST);
		for(Goods goods : goodsList){
			if(goods.getId().equals(goodsId)){
				goodsList.remove(goods);
				break;
			}
		}
		if(CollectionUtils.isEmpty(goodsList)){
			request.setAttribute(Constants.PAGE, "shopping");
			request.setAttribute("err", "购物车为空，请添加商品。");
			return "index";
		}
		shopCar.put(Constants.GOODS_LIST, goodsList);
		/** 配置购物车页面参数 **/
		request = shopCarCondition(request,shopCar,loginContext);
		return "index";
	}
	
	/**
	 * 添加地址
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addAddress(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User loginContext = RequestUtils.getUser(request);
		String sessionKey = loginContext.getLoginName()+Constants.GOU;
		/** 添加地址默认状态为非默认选中 **/
		address.setStatus(2);
		address.setUserId(loginContext.getId());
		String result = addressService.validate(address);
		if(result!=null){
			request.setAttribute("err", result);
		}else{
			if("男".equals(address.getSex())){
				address.setSex("先生");
			}
			if("女".equals(address.getSex())){
				address.setSex("女士");
			}
			addressService.saveAddress(address);
		}
		Map<String ,Object> shopCar = (Map<String, Object>) session.getAttribute(sessionKey);
		if(session.getAttribute(sessionKey)==null){
			request.setAttribute("err", "购物车为空，请添加商品。");
			return "index";
		}
		request = shopCarCondition(request,shopCar,loginContext);
		request.setAttribute("mAddress", address);
		return "index";
	}
	
	/**
	 * 选择地址
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String chooseAddress(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User loginContext = RequestUtils.getUser(request);
		String sessionKey = loginContext.getLoginName()+Constants.GOU;
		Map<String ,Object> shopCar = (Map<String, Object>) session.getAttribute(sessionKey);
		if(session.getAttribute(sessionKey)==null){
			request.setAttribute("err", "购物车为空，请添加商品。");
			return "index";
		}
		request = shopCarCondition(request,shopCar,loginContext);
		
		Address address = addressService.getEntity(addressId);
		request.setAttribute("mAddress", address);
		return "index";
	}
	
	/**
	 * 生成一条未支付订单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String placeOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		request.setAttribute(Constants.PAGE, "shopping");
		User loginContext = RequestUtils.getUser(request);
		String sessionKey = loginContext.getLoginName()+Constants.GOU;
		Map<String ,Object> shopCar = (Map<String, Object>) session.getAttribute(sessionKey);
		List<Goods> goodsList = (List<Goods>) shopCar.get(Constants.GOODS_LIST);
		request.setAttribute("goodsList", goodsList);
		Long merchantId = (Long) shopCar.get(Constants.MERCHANT_ID);
		Merchant merchant = merchantService.getMerchantByMerchantId(merchantId);
		request.setAttribute("merchant", merchant);
		String strategy = strategyService.getStrategyShow(merchantId);
		request.setAttribute("strategy",strategy);
		Address address = addressService.getEntity(addressId);
		/** 生成一条未支付订单 **/
		Long orderId = orderService.generateOrder(loginContext, merchant, address, goodsList, counts);
		
		/** 添加参数 **/
		Order order = orderService.getOrderById(orderId);
		List<GoodsInfo> goodsInfos = orderService.queryGoodsInfoByOid(orderId);
		PriceInfo priceInfo = orderService.getOrderPriceByOid(orderId);
		
		/** 传值 **/
		request.setAttribute("order", order);
		request.setAttribute("goodsInfos", goodsInfos);
		request.setAttribute("priceInfo", priceInfo);
		request.setAttribute("codFlag", merchant.getCodFlag());
		return "placeOrder";
	}
	
	/**
	 * 配置参数
	 * @param request
	 * @param shopCar
	 * @param loginContext
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private HttpServletRequest shopCarCondition(HttpServletRequest request, Map<String ,Object> shopCar, User loginContext){
		List<Address> addressList = addressService.queryAddressByUserId(loginContext.getId());
		if(CollectionUtils.isNotEmpty(addressList)){
			request.setAttribute("mAddress", addressList.get(0));
			request.setAttribute("addressList", addressList);
		}
		List<Goods> goodsList = (List<Goods>) shopCar.get(Constants.GOODS_LIST);
		request.setAttribute("goodsList", goodsList);
		Long merchantId = (Long) shopCar.get(Constants.MERCHANT_ID);
		Merchant merchant = merchantService.getMerchantByMerchantId(merchantId);
		request.setAttribute("merchant", merchant);
		String strategy = strategyService.getStrategyShow(merchantId);
		request.setAttribute("strategy",strategy);
		request.setAttribute(Constants.PAGE, "shopping");
		return request;
	}

	
}
