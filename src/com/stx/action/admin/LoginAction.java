package com.stx.action.admin;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.entity.order.Order;
import com.stx.entity.vo.OrderDetailVo;
import com.stx.service.UserService;
import com.stx.service.order.OrderService;
import com.stx.util.RequestUtils;

/**
 * 管理员登陆
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
	private OrderService orderService;
	
	/**
	 * 跳转至商户管理员登陆首页
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
		addCondition(request);
		return "home";
	}

	/**
	 * 执行管理员登陆操作
	 * 
	 * @return
	 */
	public String doLogin() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User currentUser = userService.merchantLogin(user);
		if (currentUser == null) {
			request.setAttribute("err", "用户名或密码错误。");
			return "index";
		}
		if (currentUser.getUserType() != Constants.ADMIN_TYPE) {
			request.setAttribute("err", "非管理员用户，请前往对应登陆站点。");
			return "index";
		}
		if(currentUser.getLoginSwitch() == Constants.LOGIN_SWITCH_CLOSE){
			request.setAttribute("err", "您已经被紧闭，请联系管理员。");
			return "index";
		}
		/** 登陆成功的话，将用户信息放入session **/
		ActionContext.getContext().getSession()
				.put("loginContext", currentUser);
		addCondition(request);
		return "home";
	}
	
	/**
	 * 配置跳转页面携带的参数
	 * 
	 * @param request
	 */
	private void addCondition(HttpServletRequest request){
		request.setAttribute(Constants.PAGE, "order");
		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");
		String searchType = request.getParameter("searchType");
		String searchValue = request.getParameter("searchValue");
		List<Order> orderList = orderService.adminSearchOrder(startDateStr, endDateStr, searchType, searchValue);
		request.setAttribute("orderList", orderList);
		request.setAttribute("startDate", startDateStr);
		request.setAttribute("endDate", endDateStr);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchValue", searchValue);
	}
	
	/**
	 * 查看订单详情
	 * 
	 * @return
	 */
	public String orderDetail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "order");
		Long orderId = RequestUtils.getId(request);
		OrderDetailVo detailVo = orderService.getOrderDetail(orderId);
		request.setAttribute("detailVo", detailVo);
		return "detail";
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
