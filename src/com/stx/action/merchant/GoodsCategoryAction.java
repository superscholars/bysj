package com.stx.action.merchant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.entity.merchant.GoodsCategory;
import com.stx.entity.merchant.Merchant;
import com.stx.service.merchant.GoodsCategoryService;
import com.stx.service.merchant.MerchantService;
import com.stx.util.RequestUtils;
import com.stx.util.StringUtils;

/**
 * 商品分类
 * 
 * @author gzzdsg 2016年3月12日
 */
public class GoodsCategoryAction extends ActionSupport implements
		ModelDriven<GoodsCategory> {

	private static final long serialVersionUID = -7906157409937440196L;

	private GoodsCategory goodsCategory = new GoodsCategory();

	@Override
	public GoodsCategory getModel() {
		// TODO Auto-generated method stub
		return goodsCategory;
	}

	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Autowired
	private MerchantService merchantService;

	/**
	 * 打开修改信息界面
	 * 
	 * @return
	 */
	public String openPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User currentUser = (User) request.getSession().getAttribute(
				Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(currentUser
				.getId());
		List<GoodsCategory> categorys = goodsCategoryService
				.queryCategoryById(merchant.getId());
		request.setAttribute(Constants.PAGE, "category");
		request.setAttribute("categorys", categorys);
		return "index";
	}
	
	/**
	 * 打开添加页面
	 * @return
	 */
	public String addCategoryPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "category");
		return "addPage";
	}
	
	/**
	 * 提交添加
	 */
	public String doAdd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "category");
		HttpSession session = request.getSession();
		User loginContext = (User) session.getAttribute(Constants.USER);
		/** 获取当前登录的商户信息 **/
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		String result = goodsCategoryService.verifyAdd(merchant.getId(), goodsCategory);
		if(StringUtils.isNotEmpty(result)){
			request.setAttribute("err", result);
			return "addPage";
		}
		goodsCategory.setMerchantId(merchant.getId());
		Boolean flag = goodsCategoryService.saveCategory(goodsCategory);
		if(!flag){
			request.setAttribute("err", "网络不佳添加失败。");
			return "addPage";
		}
		request.setAttribute("success", "添加分类成功");
		List<GoodsCategory> categorys = goodsCategoryService
				.queryCategoryById(merchant.getId());
		request.setAttribute("categorys", categorys);
		return "index";
	}
	
	/**
	 * 编辑分类
	 * @return
	 */
	public String editPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "category");
		Long categoryId = RequestUtils.getId(request);
		if(categoryId==null){
			request.setAttribute("err", "参数错误");
			return "index";
		}
		GoodsCategory category = goodsCategoryService.getOne(categoryId);
		if(category==null){
			request.setAttribute("err", "查询失败");
			return "index";
		}
		request.setAttribute("category", category);
		return "editPage";
	}
	
	/**
	 * 执行保存操作
	 * @return
	 */
	public String doUpdate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "category");
		String result = goodsCategoryService.verifyUpdate(goodsCategory);
		if(StringUtils.isNotEmpty(result)) {
			request.setAttribute("err", result);
			return "editPage";
		}
		goodsCategoryService.updateCategory(goodsCategory);
		request.setAttribute("success", "编辑成功");
		User currentUser = (User) request.getSession().getAttribute(
				Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(currentUser
				.getId());
		List<GoodsCategory> categorys = goodsCategoryService
				.queryCategoryById(merchant.getId());
		request.setAttribute("categorys", categorys);
		return "index";
	}
	
	/**
	 * 执行删除操作
	 * @return
	 */
	public String remove(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "category");
		Long categoryId = RequestUtils.getId(request);
		if(categoryId==null){
			request.setAttribute("err", "路径错误");
			return "index";
		}
		goodsCategoryService.deleteCategory(categoryId);
		request.setAttribute("success", "删除成功");
		User currentUser = (User) request.getSession().getAttribute(
				Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(currentUser
				.getId());
		List<GoodsCategory> categorys = goodsCategoryService
				.queryCategoryById(merchant.getId());
		request.setAttribute("categorys", categorys);
		return "index";
	}

}
