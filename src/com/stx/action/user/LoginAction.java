package com.stx.action.user;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.entity.merchant.Goods;
import com.stx.entity.merchant.Merchant;
import com.stx.entity.merchant.MerchantType;
import com.stx.entity.vo.CommentVo;
import com.stx.entity.vo.GoodsVo;
import com.stx.entity.vo.StrategyVo;
import com.stx.service.UserService;
import com.stx.service.merchant.GoodsCategoryService;
import com.stx.service.merchant.GoodsService;
import com.stx.service.merchant.MerchantService;
import com.stx.service.merchant.MerchantTypeService;
import com.stx.service.merchant.StrategyService;
import com.stx.service.user.CollectionService;
import com.stx.service.user.MerchantCommentService;
import com.stx.util.RequestUtils;

public class LoginAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = -6887687826970160260L;

	private User user = new User();
	
	/**
	 * 用于接收注册时第二次密码
	 */
	private String password2;
	
	/**
	 * 用于接收搜索条件
	 */
	private String condition;
	
	/**
	 * 头像
	 */
	private File head;

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
	
	// 提交过来的file的名字
	private String headFileName;

	// 提交过来的file的MIME类型
	private String headContentType;
	
	public File getHead() {
		return head;
	}

	public void setHead(File head) {
		this.head = head;
	}

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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	
	@Autowired
	private UserService userService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private CollectionService collectionService;
	@Autowired
	private StrategyService strategyService;
	@Autowired
	private MerchantCommentService merchantCommentService;
	@Autowired
	private MerchantTypeService merchantTypeService;
	@Autowired
	private GoodsCategoryService categoryService;
	
	/**
	 * 跳转首页
	 * @return
	 */
	public String goHome(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		goHomeCondition(request);
		return "home";
	}
	
	/**
	 * 跳转至登陆首页
	 * 
	 * @return
	 */
	public String login() {
		return "index";
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
		String reason = userService.registUser(user, password2);
		if (reason != null) {
			request.setAttribute("err", reason);
			return "register";
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
		request = goHomeCondition(ServletActionContext.getRequest());
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
		request = goHomeCondition(ServletActionContext.getRequest());
		return "index";
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
		if (currentUser.getUserType() != Constants.USER_TYPE) {
			request.setAttribute("err", "请使用用户账号登陆");
			return "index";
		}
		if(currentUser.getLoginSwitch() == Constants.LOGIN_SWITCH_CLOSE){
			request.setAttribute("err", "您已经被禁闭，请联系管理员。");
			return "index";
		}
		/** 登陆成功的话，将用户信息放入session **/
		ActionContext.getContext().getSession()
				.put(Constants.USER, currentUser);
		
		request = goHomeCondition(request);
		return "home";
	}
	
	/**
	 * 按照商品名称查询
	 * @return
	 */
	public String search(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(condition==null||condition.trim().length()<1){
			request.setAttribute("searchErr", "搜索条件不能为空");
			request = goHomeCondition(request);
			return "home";
		}
		/** 商品名称 **/
		List<Merchant> merchantList = goodsService.findMerchantsByGoodsName(condition);
		request.setAttribute("merchantList", merchantList);
		request.setAttribute("condition", condition);
		return "search";
	}
	
	/**
	 * 商户类型
	 * @return
	 */
	public String merchantType(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String typeName = request.getParameter("type");
		if(typeName==null||typeName.trim().length()<1){
			request.setAttribute("searchErr", "商户id不对");
			request = goHomeCondition(request);
			return "home";
		}
		List<Merchant> merchantList = merchantService.getMerchantByType(typeName);
		request.setAttribute("merchantList", merchantList);
		request.setAttribute("condition", typeName);
		return "merchantType";
	}
	
	/**
	 * 根据商户id进入商户主界面
	 * @return
	 */
	public String merchant(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long merchantId = RequestUtils.getId(request);
		String cId = request.getParameter("categoryId");
		Long categoryId = 0L;
		if(cId != null){
			try{
				categoryId = Long.parseLong(cId);
			}catch (Exception e){
				System.out.println("转换异常");
			}
			merchantId = categoryService.queryMerchantIdByCategoryId(categoryId);
		}
		Merchant merchant = merchantService.getMerchantByMerchantId(merchantId);
		if(merchant==null){
			request.setAttribute("searchErr", "商户id不对");
			request = goHomeCondition(request);
			return "home";
		}
		User loginContext = RequestUtils.getUser(request);
		request = merchantCondition(request,merchantId,loginContext.getId());
		return "merchantMain";
	}
	
	/**
	 * 切换
	 * @return
	 */
	public String collection(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long merchantId = RequestUtils.getId(request);
		Merchant merchant = merchantService.getMerchantByMerchantId(merchantId);
		if(merchant==null){
			request.setAttribute("searchErr", "商户id不对");
			request = goHomeCondition(request);
			return "home";
		}
		User loginContext = RequestUtils.getUser(request);
		/** 改变收藏关系 **/
		collectionService.changeCollectionRelation(loginContext.getId(), merchantId);
		request = merchantCondition(request,merchantId,loginContext.getId());
		return "merchantMain";
	}
	
	
	/**
	 * 商品加入购物车
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addShopping(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Long goodsId = RequestUtils.getId(request);
		Long merchantId = RequestUtils.getMerchantId(request);
		User loginContext = RequestUtils.getUser(request);
		if(goodsId==null||merchantId==null){
			request.setAttribute("searchErr", "参数不对");
			request = goHomeCondition(request);
			return "home";
		}
		/** 生成唯一标识session key **/
		String sessionKey = loginContext.getLoginName()+Constants.GOU;
		/** 用于保存购物车内容的map **/
		Map<String,Object> shopCar = new HashMap<String,Object>();
		if(session.getAttribute(sessionKey)!=null){
			shopCar = (Map<String, Object>) session.getAttribute(sessionKey);
		}
		/** 用户保存商品信息的列表 **/
		List<Goods> goodsList = new ArrayList<Goods>();
		if(shopCar.get(Constants.GOODS_LIST)!=null){
			if(shopCar.containsKey(Constants.MERCHANT_ID)&&merchantId.equals(shopCar.get(Constants.MERCHANT_ID))){
				goodsList = (List<Goods>) shopCar.get(Constants.GOODS_LIST);
			}
		}
		for(Goods goods : goodsList){
			if(goods.getId() == goodsId){
				request.setAttribute("err", "购物车中已有该商品，如需增加数量请在购物车中添加");
				request = merchantCondition(request,merchantId,loginContext.getId());
				return "merchantMain";
			}
		}
		/** 存入购物车 **/
		goodsList.add(goodsService.getOne(goodsId));
		shopCar.put(Constants.GOODS_LIST, goodsList);
		shopCar.put(Constants.MERCHANT_ID, merchantId);
		session.setAttribute(sessionKey, shopCar);
		/** 添加商户页面参数 **/
		request = merchantCondition(request,merchantId,loginContext.getId());
		return "merchantMain";
	}
	
	/**
	 * 跳转商户界面携带参数
	 * @param request
	 * @param merchantId
	 * @param userId
	 * @return
	 */
	private HttpServletRequest merchantCondition(HttpServletRequest request ,Long merchantId , Long userId){
		Boolean collectionFlag = collectionService.findCollectionRelation(userId, merchantId);
		request.setAttribute("collection", collectionFlag);
		Merchant merchant = merchantService.getMerchantByMerchantId(merchantId);
		request.setAttribute("merchant", merchant);
		String strategyStr = strategyService.getStrategyShow(merchantId);
		request.setAttribute("strategy", strategyStr);
		List<GoodsVo> goodsList = goodsService.queryGoods(merchantId);
		request.setAttribute("goodsList", goodsList);
		List<CommentVo> commentList = merchantCommentService.queryMerchantCommentByMerchantId(merchantId);
		request.setAttribute("commentList", commentList);
		return request;
	}
	
	/**
	 * 跳转至主页附带参数
	 * @param request
	 * @return
	 */
	private HttpServletRequest goHomeCondition(HttpServletRequest request){
		request.setAttribute(Constants.PAGE, "home");
		List<MerchantType> typeList = merchantTypeService.queryAllMerchantType();
		request.setAttribute("typeList", typeList);
		Map<String ,List<Merchant>> merchants = query3Type();
		request.setAttribute("merchants", merchants);
		List<Goods> hotGoods = goodsService.queryHotGoods();
		request.setAttribute("hotGoods", hotGoods);
		List<Merchant> hotMerchant = merchantService.queryHotMerchant();
		request.setAttribute("hotMerchant", hotMerchant);
		List<StrategyVo> hotStrategy = strategyService.queryHotStrategy();
		request.setAttribute("hotStrategy", hotStrategy);
		return request;
	}
	
	/**
	 * 查三个类型的商户 
	 * @return
	 */
	private Map<String,List<Merchant>> query3Type(){
		List<MerchantType> typeList = merchantTypeService.query3MerchantType();
		Map<String ,List<Merchant>> result = new HashMap<String, List<Merchant>>();
		for(MerchantType type : typeList){
			result.put(type.getMerchantType(),merchantService.get3MerchantByType(type.getMerchantType()));
		}
		return result;
	}
	
}
