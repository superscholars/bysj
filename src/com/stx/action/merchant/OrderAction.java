package com.stx.action.merchant;

import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.entity.merchant.Merchant;
import com.stx.entity.order.Order;
import com.stx.entity.vo.OrderDetailVo;
import com.stx.entity.vo.merchant.OrderVo;
import com.stx.service.merchant.MerchantService;
import com.stx.service.order.OrderService;
import com.stx.util.CollectionUtils;
import com.stx.util.RequestUtils;

/**
 * 订单页面
 * @author gzzdsg
 * 2016年3月14日
 */
public class OrderAction extends ActionSupport {

	private static final long serialVersionUID = 9157725376992569852L;

	@Autowired
	private OrderService orderService;
	@Autowired
	private MerchantService merchantService;
	
	/**
	 * 打开主页
	 * @return
	 */
	public String openPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "order");
		User loginContext = (User) request.getSession().getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId()); 
		request = orderCondition(request, merchant.getId());
		return "index";
	}
	
	
	
	/**
	 * 接收订单
	 * @return
	 */
	public String receiveOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "order");
		User loginContext = RequestUtils.getUser(request);
		Long orderId = RequestUtils.getId(request);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		String result = orderService.validateReceive(orderId);
		if(result!=null){
			request.setAttribute("err", result);
		}else{
			orderService.receiveOrder(orderId);
		}
		//配置参数
		request = orderCondition(request, merchant.getId());
		return "index";
	}
	
	/**
	 * 备好商品
	 * @return
	 */
	public String readyOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "order");
		User loginContext = RequestUtils.getUser(request);
		Long orderId = RequestUtils.getId(request);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		String result = orderService.validateReady(orderId);
		if(result!=null){
			request.setAttribute("err", result);
		}else{
			orderService.readyOrder(orderId);
		}
		//配置参数
		request = orderCondition(request, merchant.getId());
		return "index";
	}
	
	/**
	 * 送出商品
	 * @return
	 */
	public String sendOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "order");
		User loginContext = RequestUtils.getUser(request);
		Long orderId = RequestUtils.getId(request);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		String result = orderService.validateSend(orderId);
		if(result!=null){
			request.setAttribute("err", result);
		}else{
			orderService.sendOrder(orderId);
		}
		//配置参数
		request = orderCondition(request, merchant.getId());
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
	 * 配置参数
	 * @param request
	 * @return
	 */
	private HttpServletRequest orderCondition(HttpServletRequest request, Long merchantId){
		//查询未接订单 status = WAITTING
		List<Order> wList = orderService.queryByMerchantAndStatus(merchantId, 0, Constants.STATUS_WAITTING);
		List<OrderVo> wtList = new ArrayList<OrderVo>();
		if(CollectionUtils.isNotEmpty(wList)){
			OrderVo orderVo = null;
			for(Order order : wList){
				orderVo = new OrderVo();
				orderVo.setOrder(order);
				orderVo.setGoodsInfoList(orderService.queryGoodsInfoByOid(order.getId()));
				wtList.add(orderVo);
			}
		}
		//查询处理中订单 status > WAITTING AND status < FINISH
		List<Order> cList = orderService.queryByMerchantAndStatus(merchantId, Constants.STATUS_WAITTING, Constants.STATUS_FINISH-1);
		List<OrderVo> clList = new ArrayList<OrderVo>();
		if(CollectionUtils.isNotEmpty(cList)){
			OrderVo orderVo = null;
			for(Order order : cList){
				orderVo = new OrderVo();
				orderVo.setOrder(order);
				orderVo.setGoodsInfoList(orderService.queryGoodsInfoByOid(order.getId()));
				clList.add(orderVo);
			}
		}
		//查询完成的订单 status = FINISH
		List<Order> fList = orderService.queryByMerchantAndStatus(merchantId, Constants.STATUS_ON_WAY, Constants.STATUS_CANCEL);
		List<OrderVo> fnlList = new ArrayList<OrderVo>();
		if(CollectionUtils.isNotEmpty(fList)){
			OrderVo orderVo = null;
			for(Order order : fList){
				orderVo = new OrderVo();
				orderVo.setOrder(order);
				orderVo.setGoodsInfoList(orderService.queryGoodsInfoByOid(order.getId()));
				fnlList.add(orderVo);
			}
		}
		request.setAttribute("waitting", wtList);
		request.setAttribute("doing", clList);
		request.setAttribute("finish", fnlList);
		return request;
	}
	
	
}
