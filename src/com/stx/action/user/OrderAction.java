package com.stx.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.entity.merchant.Merchant;
import com.stx.entity.order.GoodsInfo;
import com.stx.entity.order.Order;
import com.stx.entity.order.PriceInfo;
import com.stx.entity.vo.OrderDetailVo;
import com.stx.entity.vo.OrderProcessVo;
import com.stx.service.merchant.MerchantService;
import com.stx.service.order.OrderService;
import com.stx.util.CollectionUtils;
import com.stx.util.RequestUtils;

/**
 * 订单业务
 * @author gzzdsg
 * 2016年3月23日
 */
public class OrderAction extends ActionSupport {

	private static final long serialVersionUID = 7665343514585664207L;
	/**
	 * 订单id
	 */
	private Long orderId;
	/**
	 * 食用方式
	 */
	private Integer eatType;
	/**
	 * 支付方式
	 */
	private Integer payType;
	/**
	 * 商品点赞
	 */
	private Integer buzz;
	/**
	 * 评价商家
	 */
	private String comment;
	
	public Integer getBuzz() {
		return buzz;
	}

	public void setBuzz(Integer buzz) {
		this.buzz = buzz;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getEatType() {
		return eatType;
	}

	public void setEatType(Integer eatType) {
		this.eatType = eatType;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	
	@Autowired
	private OrderService orderService;
	@Autowired
	private MerchantService merchantService;
	
	/**
	 * 打开订单中心
	 * @return
	 */
	public String info(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginContext = RequestUtils.getUser(request);
		request = orderCondition(request ,loginContext.getId());
		return "index";
	}
	
	/**
	 * 支付订单
	 * @return
	 */
	public String createOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginContext = RequestUtils.getUser(request);
		String sessionKey = loginContext.getLoginName()+Constants.GOU;
		request.getSession().removeAttribute(sessionKey);
		/** 保存订单信息 **/
		orderService.createOrder(orderId, eatType, payType);
		request = orderCondition(request ,loginContext.getId());
		return "index";
	}
	
	/**
	 * 再来一单
	 * @return
	 */
	public String againOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "order");
		Long oldOrderId = RequestUtils.getId(request);
		User loginContext = RequestUtils.getUser(request);
		// 判断商家是否上架   判断商品是否供应
		String result = orderService.validateAgain(oldOrderId);
		if(result!=null){
			request.setAttribute("err", result);
			request = orderCondition(request ,loginContext.getId());
			return "index";
		}
		/** 再下一单 **/
		Long orderId = orderService.againOrder(oldOrderId, loginContext);
		/** 添加参数 **/
		Order order = orderService.getOrderById(orderId);
		List<GoodsInfo> goodsInfos = orderService.queryGoodsInfoByOid(orderId);
		PriceInfo priceInfo = orderService.getOrderPriceByOid(orderId);
		Merchant merchant = merchantService.getMerchantByMerchantId(order.getMerchantId());
		/** 传值 **/
		request.setAttribute("order", order);
		request.setAttribute("goodsInfos", goodsInfos);
		request.setAttribute("priceInfo", priceInfo);
		request.setAttribute("codFlag", merchant.getCodFlag());
		return "placeOrder";
	}
	
	/**
	 * 评价订单
	 * @return
	 */
	public String commentOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long orderId = RequestUtils.getId(request);
		request.setAttribute("orderId", orderId);
		return "comment";
	}
	
	/**
	 * 跳转至投诉界面
	 * @return
	 */
	public String complaint(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long orderId = RequestUtils.getId(request);
		User loginContext = RequestUtils.getUser(request);
		String content = request.getParameter("content");
		String result = orderService.complaintOrder(content, orderId, loginContext);
		if(result!=null){
			request.setAttribute("err", result);
		}
		request = orderCondition(request ,loginContext.getId());
		return "index";
	}
	
	/**
	 * 完成订单
	 * @return
	 */
	public String finishOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long orderId = RequestUtils.getId(request);
		User loginContext = RequestUtils.getUser(request);
		String result = orderService.finishOrder(loginContext,orderId,buzz,comment);
		if(result!=null){
			request.setAttribute("err", result);
		}
		request = orderCondition(request ,loginContext.getId());
		return "index";
	}
	
	/**
	 * 取消订单
	 * @return
	 */
	public String cancelOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long orderId = RequestUtils.getId(request);
		String result = orderService.cancelOrder(orderId);
		if(result!=null){
			request.setAttribute("err", result);
		}
		User loginContext = RequestUtils.getUser(request);
		request = orderCondition(request ,loginContext.getId());
		return "index";
	}
	
	/**
	 * 查看订单详情
	 * @return
	 */
	public String orderDetail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "order");
		Long orderId = RequestUtils.getId(request);
		OrderDetailVo detailVo = orderService.getOrderDetail(orderId);
		request.setAttribute("detailVo", detailVo);
		return "detail";
	}
	
	/**
	 * 进入订单中心的参数
	 * @param request
	 * @return
	 */
	private HttpServletRequest orderCondition(HttpServletRequest request, Long userId){
		request.setAttribute(Constants.PAGE, "order");
		List<OrderProcessVo> orderVoList = orderService.queryProcessVo(userId);
		if(CollectionUtils.isEmpty(orderVoList)){
			request.setAttribute("err", "尚未下过订单。");
			return request;
		}
		request.setAttribute("orderVoList", orderVoList);
		return request;
	}
	
}
