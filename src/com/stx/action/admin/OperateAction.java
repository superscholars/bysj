package com.stx.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.entity.admin.Complaint;
import com.stx.entity.merchant.Merchant;
import com.stx.entity.merchant.MerchantType;
import com.stx.entity.vo.CommentVo;
import com.stx.entity.vo.GoodsVo;
import com.stx.service.UserService;
import com.stx.service.admin.ComplaintService;
import com.stx.service.admin.PunishService;
import com.stx.service.merchant.GoodsService;
import com.stx.service.merchant.MerchantService;
import com.stx.service.merchant.MerchantTypeService;
import com.stx.service.merchant.StrategyService;
import com.stx.service.user.MerchantCommentService;
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
	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private MerchantTypeService merchantTypeService;
	@Autowired
	private StrategyService strategyService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private MerchantCommentService merchantCommentService;
	@Autowired
	private PunishService punishService;
	
	// ====================审核商户
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
	
	
	//==================投诉中心
	
	/**
	 * 进入投诉中心
	 * 
	 * @return
	 */
	public String complaint(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "complaint");
		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");
		List<Complaint> complaints = complaintService.listComplaint(startDateStr, endDateStr);
		request.setAttribute("complaintList", complaints);
		return "complaint";
	}
	
	//===================禁闭商户
	
	/**
	 * 紧闭商户主页
	 * @return
	 */
	public String punishMerchant(){
		HttpServletRequest request = ServletActionContext.getRequest();
		punishMerchantCondition(request);
		return "punishMerchant";
	}
	
	/**
	 * 禁闭商户操作
	 */
	public String doPunishMerchant(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long merchantId = RequestUtils.getId(request);
		String merchantName = request.getParameter("name");
		Integer punishDays = Integer.parseInt(request.getParameter("days"));
		String reason = request.getParameter("reason");
		User loginContext = RequestUtils.getUser(request);
		String result = punishService.punishMerchant(loginContext, merchantId, merchantName, reason, punishDays);
		if(result==null){
			request.setAttribute("success", "处罚成功。");
		}else{
			request.setAttribute("err", result);
		}
		punishMerchantCondition(request);
		return "punishMerchant";
	}
	
	/**
	 * 打开商户主界面
	 * @return
	 */
	public String merchantMain(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long merchantId = RequestUtils.getId(request);
		User loginContext = RequestUtils.getUser(request);
		request = merchantCondition(request,merchantId,loginContext.getId());
		return "merchatMain";
	}
	
	/**
	 * 跳转商户界面携带参数
	 * @param request
	 * @param merchantId
	 * @param userId
	 * @return
	 */
	private HttpServletRequest merchantCondition(HttpServletRequest request ,Long merchantId , Long userId){
		Merchant merchant = merchantService.getMerchantByMerchantId(merchantId);
		request.setAttribute("merchant", merchant);
		String strategyStr = strategyService.getStrategyShow(merchantId);
		request.setAttribute("strategy", strategyStr);
		List<GoodsVo> goodsList = goodsService.queryGoods(merchantId);
		request.setAttribute("goodsList", goodsList);
		List<CommentVo> commentList = merchantCommentService.queryMerchantCommentByMerchantId(merchantId);
		request.setAttribute("commentList", commentList);
		Double punishTime = punishService.validatePunish(merchantId);
		if(punishTime == 0){
			request.setAttribute("success", "该商家未被禁闭");
		}else{
			request.setAttribute("err", "商家还有"+punishTime+"消失解禁");
		}
		return request;
	}
	
	/**
	 * 跳转至禁闭商户的携带参数
	 * 
	 * @param request
	 */
	private void punishMerchantCondition(HttpServletRequest request){
		request.setAttribute(Constants.PAGE, "punishMerchant");
		String merchantName = request.getParameter("merchantName");
		String merchantType = request.getParameter("merchantType");
		request.setAttribute("merchantType", merchantType);
		request.setAttribute("merchantName", merchantName);
		List<MerchantType> merchantTypeList = merchantTypeService.queryAllMerchantType();
		request.setAttribute("merchantTypeList", merchantTypeList);
		List<Merchant> merchantList = merchantService.pageAllMerchant(merchantType, merchantName); 
		request.setAttribute("merchantList", merchantList);
	}
	
	//======================禁闭用户
	/**
	 * 进入禁闭用户界面
	 * @return
	 */
	public String confineUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		confineCondition(request);
		return "confine";
	}
	
	/**
	 * 禁闭用户
	 * @return
	 */
	public String doConfineUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long userId = RequestUtils.getId(request);
		userService.changeLoginSwitch(userId, Constants.LOGIN_SWITCH_CLOSE);
		confineCondition(request);
		return "confine";
	}
	
	/**
	 * 解禁用户
	 * @return
	 */
	public String unConfineUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long userId = RequestUtils.getId(request);
		userService.changeLoginSwitch(userId, Constants.LOGIN_SWITCH_OPEN);
		confineCondition(request);
		return "confine";
	}
	
	/**
	 * 禁闭用户的展示内容
	 * @param request
	 */
	private void confineCondition(HttpServletRequest request){
		request.setAttribute(Constants.PAGE, "confineUser");
		List<User> userList = userService.queryAllUser();
		request.setAttribute("userList", userList);
	}
	
	

}
