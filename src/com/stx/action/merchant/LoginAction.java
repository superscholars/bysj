package com.stx.action.merchant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.entity.merchant.Merchant;
import com.stx.entity.order.Order;
import com.stx.entity.vo.merchant.OrderVo;
import com.stx.service.UserService;
import com.stx.service.merchant.MerchantService;
import com.stx.service.order.OrderService;
import com.stx.util.CollectionUtils;

/**
 * 商户登陆
 * @author gzzdsg
 * 2016年3月12日
 */
public class LoginAction extends ActionSupport implements ModelDriven<User> {

	/** 序列化版本ID */
	private static final long serialVersionUID = 1992777968166886077L;

	/** 模型都去哦那个需要使用的对象 */
	private User user = new User();

	/**
	 * 模型驱动需要使用的对象
	 */
	@Override
	public User getModel() {
		return this.user;
	}

	/**
	 * 第二次密码
	 */
	private String password2;

	/**
	 * 修改密码：旧密码
	 */
	private String oldPassword;

	/**
	 * 修改密码：新密码
	 */
	private String newPassword;

	/**
	 * 修改密码：确认新密码
	 */
	private String newPassword2;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	/**
	 * 头像
	 */
	private File head;

	// 提交过来的file的名字
	private String headFileName;

	// 提交过来的file的MIME类型
	private String headContentType;

	public String getHeadFileName() {
		return headFileName;
	}

	public void setHeadFileName(String headFileName) {
		this.headFileName = headFileName;
	}

	public String getHeadContentType() {
		return headContentType;
	}

	public void setHeadContentType(String headContentType) {
		this.headContentType = headContentType;
	}

	public File getHead() {
		return head;
	}

	public void setHead(File head) {
		this.head = head;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	/**
	 * 依赖注入
	 */
	@Autowired
	private UserService userService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 跳转至商户登陆首页
	 * 
	 * @return
	 */
	public String login() {
		return "index";
	}

	/**
	 * 主页面
	 * 
	 * @return
	 */
	public String goHome() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "order");
		User loginContext = (User) request.getSession().getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId()); 
		//查询未接订单 status = WAITTING
		List<Order> wList = orderService.queryByMerchantAndStatus(merchant.getId(), 0, Constants.STATUS_WAITTING);
		//查询处理中订单 status > WAITTING AND status < FINISH
		List<Order> cList = orderService.queryByMerchantAndStatus(merchant.getId(), Constants.STATUS_WAITTING, Constants.STATUS_FINISH-1);
		//查询完成的订单 status = FINISH
		List<Order> fList = orderService.queryByMerchantAndStatus(merchant.getId(), Constants.STATUS_ON_WAY, Constants.STATUS_CANCEL);
		request.setAttribute("waitting", wList);
		request.setAttribute("doing", cList);
		request.setAttribute("finish", fList);
		return "home";
	}

	/**
	 * 执行商户登陆操作
	 * 
	 * @return
	 */
	public String doLogin() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User currentUser = userService.merchantLogin(user);
		request.setAttribute(Constants.PAGE, "order");
		if (currentUser == null) {
			request.setAttribute("err", "用户名或密码错误。");
			return "index";
		}
		if (currentUser.getUserType() != Constants.MERCHANT_TYPE) {
			request.setAttribute("err", "非商户用户，请前往对应登陆站点。");
			return "index";
		}
		if (currentUser.getLoginSwitch() == Constants.LOGIN_SWITCH_WAITING) {
			request.setAttribute("err", "商户还尚未通过审核。");
			return "index";
		}
		if(currentUser.getLoginSwitch() == Constants.LOGIN_SWITCH_CLOSE){
			request.setAttribute("err", "您已经被紧闭，请联系管理员。");
			return "index";
		}
		/** 登陆成功的话，将用户信息放入session **/
		ActionContext.getContext().getSession()
				.put("loginContext", currentUser);
		request.setAttribute(Constants.PAGE, "order");
		Merchant merchant = merchantService.getMerchantByUserId(currentUser.getId()); 
		request = orderCondition(request, merchant.getId());
		return "home";
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

	/**
	 * 跳转至商户注册页面
	 * 
	 * @return
	 */
	public String register() {
		return "register";
	}

	/**
	 * 执行注册操作
	 * 
	 * @return
	 */
	public String doRegist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		this.user.setHeadPath("head.jpg");
		String reason = userService.registMerchantUser(user, password2);
		if (reason != null) {
			request.setAttribute("err", reason);
			return "register";
		}else{
			request.setAttribute("success", "注册成功，联系管理员进行审核。");
		}
		return "index";
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	public String logout() {
		ActionContext.getContext().getSession().remove("loginContext");
		return "index";
	}

	/**
	 * 打开修改信息页面
	 * 
	 * @return
	 */
	public String goSetting() {
		return "setting";
	}

	/**
	 * 修改头像
	 * 
	 * @return
	 */
	public String settingHead() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginContext = (User) request.getSession().getAttribute(Constants.USER);
		String headPath = ServletActionContext.getServletContext().getRealPath(
				"/image/head");
		String result = userService.updateUserHead(loginContext, head,
				headPath, headFileName);
		if (result != null) {
			request.setAttribute("err", result);
			return "setting";
		}
		return "home";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String settingPassword() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Object obj = ActionContext.getContext().getSession()
				.get("loginContext");
		if (obj == null) {
			return "index";
		}
		User loginContext = (User) obj;
		String result = userService.updatePassword(loginContext, oldPassword,
				newPassword, newPassword2);
		if (result != null) {
			request.setAttribute("err", result);
			return "setting";
		}
		return "index";
	}
	
	

}
