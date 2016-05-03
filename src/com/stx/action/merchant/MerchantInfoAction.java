package com.stx.action.merchant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.entity.merchant.Merchant;
import com.stx.entity.order.Order;
import com.stx.entity.vo.merchant.OrderVo;
import com.stx.service.merchant.MerchantService;
import com.stx.service.merchant.MerchantTypeService;
import com.stx.service.order.OrderService;
import com.stx.util.CollectionUtils;

/**
 * 商铺信息管理
 * 
 * @author gzzdsg
 * 2016年3月10日
 */
public class MerchantInfoAction extends ActionSupport implements ModelDriven<Merchant>{

	/** 序列化版本ID */
	private static final long serialVersionUID = 1992777968166886077L;

	/** 模型都去哦那个需要使用的对象 */
	private Merchant merchant = new Merchant();
	
	@Override
	public Merchant getModel() {
		// TODO Auto-generated method stub
		return merchant;
	}
	
	/**
	 * logo
	 */
	private File logo;

	// 提交过来的file的名字
	private String logoFileName;

	// 提交过来的file的MIME类型
	private String logoContentType;
	
	/**
	 * 新设置的商户类型
	 */
	private String newMerchantType;
	
	public String getNewMerchantType() {
		return newMerchantType;
	}

	public void setNewMerchantType(String newMerchantType) {
		this.newMerchantType = newMerchantType;
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public String getLogoContentType() {
		return logoContentType;
	}

	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}

	/**
	 * 依赖注入
	 */
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private MerchantTypeService merchantTypeService;
	@Autowired
	private OrderService orderService;
	/**
	 * 打开修改信息界面
	 * 
	 * @return
	 */
	public String openPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User currentUser = (User)request.getSession().getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(currentUser.getId());
		request.setAttribute("merchant", merchant);
		request.setAttribute(Constants.PAGE, "merchant");
		request.setAttribute("merchantTypes", merchantTypeService.queryAllMerchantType());
		return "index";
	}
	
	/**
	 * 修改商铺信息
	 * 
	 * @return
	 */
	public String updateInfo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "merchant");
		User loginContext = (User)request.getSession().getAttribute(Constants.USER);
		Merchant currentMerchant = merchantService.getMerchantByUserId(loginContext.getId());
		merchant.setLogoAddr(currentMerchant.getLogoAddr());
		/** 如果newMerchantType不为空，那么使用此属性 **/
		if(newMerchantType!=null&&newMerchantType.trim().length()>1){
			/** 保存类型  **/ 
			merchantTypeService.saveMerchantType(newMerchantType);
			merchant.setMerchantType(newMerchantType);
		}
		/** 如果文件名不为空，那么保存文件，并将保存后的名称设置到merchant对象中 **/
		if(logoFileName!=null&&logoFileName.length()>1){
			String logoPath = ServletActionContext.getServletContext().getRealPath(
					"/image/logo");
			if (logoFileName == null) {
				request.setAttribute("err", "头像文件名不能为空。");
				return "index";
			}
			String logoEnd = logoFileName.substring(logoFileName.length() - 4,
					logoFileName.length());
			if (!logoEnd.equals(".jpg") && !logoEnd.equals(".png")) {
				request.setAttribute("err", "头像只支持jpg或png格式。");
				return "index";
			}
			String result = merchantService.updateLogo( logo,logoPath,loginContext.getLoginName()+logoEnd);
			if(result!=null){
				request.setAttribute("err", result);
				return "index";
			}
			merchant.setLogoAddr(loginContext.getLoginName()+logoEnd);
		}
		String result = merchantService.updateMerchantInfo(merchant,currentMerchant);
		if(result != null){
			request.setAttribute("err", result);
			request.setAttribute("merchant", merchant);
			return "index";
		}
		request.setAttribute(Constants.PAGE, "order");
		//查询未接订单 status = WAITTING
		List<Order> wList = orderService.queryByMerchantAndStatus(currentMerchant.getId(), 0, Constants.STATUS_WAITTING);
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
		List<Order> cList = orderService.queryByMerchantAndStatus(currentMerchant.getId(), Constants.STATUS_WAITTING, Constants.STATUS_FINISH-1);
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
		List<Order> fList = orderService.queryByMerchantAndStatus(currentMerchant.getId(), Constants.STATUS_ON_WAY, Constants.STATUS_CANCEL);
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
		return "home";
	}
	

}
