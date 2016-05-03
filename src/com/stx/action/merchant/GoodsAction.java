package com.stx.action.merchant;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.entity.merchant.Goods;
import com.stx.entity.merchant.GoodsCategory;
import com.stx.entity.merchant.Merchant;
import com.stx.entity.vo.GoodsVo;
import com.stx.service.merchant.GoodsCategoryService;
import com.stx.service.merchant.GoodsService;
import com.stx.service.merchant.MerchantService;
import com.stx.util.RequestUtils;
import com.stx.util.StringUtils;

/**
 * 菜品管理操作
 * @author gzzdsg
 * 2016年3月12日
 */
public class GoodsAction extends ActionSupport implements ModelDriven<Goods>{

	private static final long serialVersionUID = -8179152400997245780L;

	private Goods goods = new Goods();
	
	@Override
	public Goods getModel() {
		// TODO Auto-generated method stub
		return goods;
	}
	
	private File pic;
	private String picFileName;
	private String picContentType;
	
	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	@Autowired
	private MerchantService merchantService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsCategoryService categoryService;
	
	/**
	 * 主页
	 * @return
	 */
	public String openPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "goods");
		HttpSession session = request.getSession();
		User loginContext = (User) session.getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		if(!goodsService.categoryExists(merchant.getId())){
			request.setAttribute("categoryflag",false);
		}else{
			request.setAttribute("categoryflag",true);
		}
		List<GoodsVo> list = goodsService.queryGoods(merchant.getId());
		request.setAttribute("goodsList", list);
		
		return "index";
	}
	
	/**
	 * 跳转至添加页
	 * 
	 * @return
	 */
	public String addPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "goods");
		User loginContext = (User) request.getSession().getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		List<GoodsCategory> categoryList = categoryService.queryCategoryById(merchant.getId());
		request.setAttribute("categoryList", categoryList);
		return "addPage";
	}
	
	/**
	 * 执行添加操作
	 * 
	 * @return
	 */
	public String doAdd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginContext = (User) request.getSession().getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		request.setAttribute(Constants.PAGE, "goods");
		String path = ServletActionContext.getServletContext().getRealPath(
				"/image/goods");
		goods.setBuzz(0);
		goods.setMonthCount(0);
		String result = goodsService.validateAdd(goods, picFileName);
		if(StringUtils.isNotEmpty(result)){
			request.setAttribute("err", result);
			request.setAttribute("goods", goods);
			List<GoodsCategory> categoryList = categoryService.queryCategoryById(merchant.getId());
			request.setAttribute("categoryList", categoryList);
			return "addPage";
		}
		Long id = goodsService.saveGoods(goods);
		if(id==null){
			request.setAttribute("err", "插入失败");
			request.setAttribute("goods", goods);
			List<GoodsCategory> categoryList = categoryService.queryCategoryById(merchant.getId());
			request.setAttribute("categoryList", categoryList);
			return "addPage";
		}
		String picEnd = picFileName.substring(picFileName.length() - 4,
				picFileName.length());
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(pic));
			OutputStream os = new BufferedOutputStream(new FileOutputStream(
					new File(path, goods.getId()+picEnd)));
			byte[] buffer = new byte[500];
			while (-1 != (is.read(buffer, 0, buffer.length))) {
				os.write(buffer);
			}
			os.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "头像保存失败。";
		}
		goods.setGoodsPic(goods.getId()+picEnd);
		goodsService.updateGoods(goods);
		if(!goodsService.categoryExists(merchant.getId())){
			request.setAttribute("categoryflag",false);
		}else{
			request.setAttribute("categoryflag",true);
		}
		List<GoodsVo> list = goodsService.queryGoods(merchant.getId());
		request.setAttribute("goodsList", list);
		request.setAttribute("success", "添加成功");
		return "index";
	}
	
	/**
	 * 跳转至编辑页面
	 * @return
	 */
	public String editPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "goods");
		HttpSession session = request.getSession();
		User loginContext = (User) session.getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		Long goodsId = RequestUtils.getId(request);
		if(goodsId==null){
			if(!goodsService.categoryExists(merchant.getId())){
				request.setAttribute("categoryflag",false);
			}else{
				request.setAttribute("categoryflag",true);
			}
			List<GoodsVo> list = goodsService.queryGoods(merchant.getId());
			request.setAttribute("goodsList", list);
			request.setAttribute("err", "url错误");
			return "index";
		}
		Goods goods = goodsService.getOne(goodsId);
		if(goods==null){
			if(!goodsService.categoryExists(merchant.getId())){
				request.setAttribute("categoryflag",false);
			}else{
				request.setAttribute("categoryflag",true);
			}
			List<GoodsVo> list = goodsService.queryGoods(merchant.getId());
			request.setAttribute("goodsList", list);
			request.setAttribute("err", "url错误");
			return "index";
		}
		request.setAttribute("goods", goods);
		List<GoodsCategory> categoryList = categoryService.queryCategoryById(merchant.getId());
		request.setAttribute("categoryList", categoryList);
		return "editPage";
	}
	
	/**
	 * 编辑商品
	 * @return
	 */
	public String doUpdate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "goods");
		HttpSession session = request.getSession();
		User loginContext = (User) session.getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		String path = ServletActionContext.getServletContext().getRealPath(
				"/image/goods");
		if(StringUtils.isNotEmpty(picFileName)){
			String picEnd = picFileName.substring(picFileName.length() - 4,
					picFileName.length());
			if (!picEnd.equals(".jpg") && !picEnd.equals(".png")) {
				return "头像只支持jpg或png格式。";
			}
			try {
				InputStream is = new BufferedInputStream(new FileInputStream(pic));
				OutputStream os = new BufferedOutputStream(new FileOutputStream(
						new File(path, goods.getId()+picEnd)));
				byte[] buffer = new byte[500];
				while (-1 != (is.read(buffer, 0, buffer.length))) {
					os.write(buffer);
				}
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
				request.setAttribute("err", "图片上传失败");
				request.setAttribute("goods", goods);
				List<GoodsCategory> categoryList = categoryService.queryCategoryById(merchant.getId());
				request.setAttribute("categoryList", categoryList);
				return "editPage";
			}
			goods.setGoodsPic(goods.getId()+picEnd);
		}
		String result = goodsService.validateUpdate(goods);
		if(StringUtils.isEmpty(result)){
			request.setAttribute("err", result);
			request.setAttribute("goods", goods);
			List<GoodsCategory> categoryList = categoryService.queryCategoryById(merchant.getId());
			request.setAttribute("categoryList", categoryList);
			return "editPage";
		}
		goodsService.updateGoods(goods);
		request.setAttribute("categoryflag",true);
		List<GoodsVo> list = goodsService.queryGoods(merchant.getId());
		request.setAttribute("goodsList", list);
		request.setAttribute("success", "编辑成功");
		return "index";
	}
	
	/**
	 * 删除商品
	 * @return
	 */
	public String remove(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long goodsId = RequestUtils.getId(request);
		request.setAttribute(Constants.PAGE, "goods");
		HttpSession session = request.getSession();
		User loginContext = (User) session.getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		if(!goodsService.categoryExists(merchant.getId())){
			request.setAttribute("categoryflag",false);
		}else{
			request.setAttribute("categoryflag",true);
		}
		if(goodsId==null){
			request.setAttribute("err", "url错误");
		}
		goodsService.removeGoods(goodsId);
		if(!goodsService.categoryExists(merchant.getId())){
			request.setAttribute("categoryflag",false);
		}else{
			request.setAttribute("categoryflag",true);
		}
		List<GoodsVo> list = goodsService.queryGoods(merchant.getId());
		request.setAttribute("goodsList", list);
		
		request.setAttribute("success", "删除成功");
		return "index";
	}

}
