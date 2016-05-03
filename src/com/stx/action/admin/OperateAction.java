package com.stx.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.service.UserService;
import com.stx.service.merchant.MerchantService;
import com.stx.util.RequestUtils;

public class OperateAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = -1056776932189551292L;

	private User user = new User();
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	@Autowired
	private UserService userService;
	@Autowired
	private MerchantService merchantService;
	
	/**
	 * 审核商户
	 * 
	 * @return
	 */
	public String checkMerchant(){
		HttpServletRequest request = ServletActionContext.getRequest();
		checkCondition(request);
		return "check";
	}
	
	/**
	 * 通过商户
	 * 
	 * @return
	 */
	public String doCheckMerchant(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long userId = RequestUtils.getId(request);
		userService.changeUserLoginFlag(userId, Constants.LOGIN_SWITCH_OPEN);
		Long merchantId = merchantService.generateMerchant(userId);
		if(merchantId == null){
			request.setAttribute("err", "系统繁忙 ，请重试。");
		}else{
			request.setAttribute("success", "审核成功");
		}
		checkCondition(request);
		return "check";
	}
	
	/**
	 * 待审核的展示内容
	 * 
	 * @param request
	 */
	private void checkCondition(HttpServletRequest request){
		request.setAttribute(Constants.PAGE, "check");
		List<User> merchantList = userService.queryNotCheckMerchant();
		request.setAttribute("merchantList", merchantList);
	}
	

}
