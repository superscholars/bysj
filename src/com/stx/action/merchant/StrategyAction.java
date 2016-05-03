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
import com.stx.entity.merchant.Merchant;
import com.stx.entity.merchant.Strategy;
import com.stx.service.merchant.MerchantService;
import com.stx.service.merchant.StrategyService;
import com.stx.util.CollectionUtils;
import com.stx.util.RequestUtils;

/**
 * 优惠策略控制
 * @author gzzdsg
 * 2016年3月13日
 */
public class StrategyAction extends ActionSupport implements ModelDriven<Strategy>{

	private static final long serialVersionUID = -990579535707945900L;

	private Strategy strategy = new Strategy();
	
	@Override
	public Strategy getModel() {
		// TODO Auto-generated method stub
		return strategy;
	}

	@Autowired
	private MerchantService merchantService;
	@Autowired
	private StrategyService strategyService;
	
	/**
	 * 主页
	 * @return
	 */
	public String openPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "strategy");
		HttpSession session = request.getSession();
		User loginContext = (User) session.getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		/**
		 * 获取新用户优惠
		 */
		List<Strategy> xList = strategyService.listStrategyByType(merchant.getId(),Constants.STRATEGY_XINYONGHU);
		if(CollectionUtils.isEmpty(xList)){
			request.setAttribute("xinyonghu", null);
		}else{
			request.setAttribute("xinyonghu", xList.get(0));
		}
		/**
		 * 获取满减优惠
		 */
		List<Strategy> mList = strategyService.listStrategyByType(merchant.getId(), Constants.STRATEGY_MANJIAN);
		if(CollectionUtils.isEmpty(mList)){
			request.setAttribute("manjian", null);
		}else{
			request.setAttribute("manjian", mList);
		}
		/**
		 * 获取折扣优惠
		 */
		List<Strategy> zList = strategyService.listStrategyByType(merchant.getId(), Constants.STRATEGY_ZHEKOU);
		if(CollectionUtils.isEmpty(zList)){
			request.setAttribute("zhekou", null);
		}else{
			request.setAttribute("zhekou", zList);
		}
		return "index";
	}
	
	/**
	 * 跳转至策略添加页面
	 * @return
	 */
	public String addPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "strategy");
		return "addPage";
	}
	
	/**
	 * 提交添加
	 * @return
	 */
	public String doAdd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "strategy");
		HttpSession session = request.getSession();
		User loginContext = (User) session.getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		strategy.setMerchantId(merchant.getId());
		if(strategy.getStrategyType()==1){
			if(!strategyService.validateXinYongHu(strategy)){
				request.setAttribute("err", "已有新用户优惠不可添加");
				return "addPage";
			}
		}
		String result = strategyService.validateStrategy(strategy);
		if(result!=null){
			request.setAttribute("err", result);
			return "addPage";
		}
		strategyService.saveStrategy(strategy);
		/**
		 * 获取新用户优惠
		 */
		List<Strategy> xList = strategyService.listStrategyByType(merchant.getId(),Constants.STRATEGY_XINYONGHU);
		if(CollectionUtils.isEmpty(xList)){
			request.setAttribute("xinyonghu", null);
		}else{
			request.setAttribute("xinyonghu", xList.get(0));
		}
		/**
		 * 获取满减优惠
		 */
		List<Strategy> mList = strategyService.listStrategyByType(merchant.getId(), Constants.STRATEGY_MANJIAN);
		if(CollectionUtils.isEmpty(mList)){
			request.setAttribute("manjian", null);
		}else{
			request.setAttribute("manjian", mList);
		}
		/**
		 * 获取折扣优惠
		 */
		List<Strategy> zList = strategyService.listStrategyByType(merchant.getId(), Constants.STRATEGY_ZHEKOU);
		if(CollectionUtils.isEmpty(zList)){
			request.setAttribute("zhekou", null);
		}else{
			request.setAttribute("zhekou", zList);
		}
		request.setAttribute("success", "添加成功");
		return "index";
	}
	
	/**
	 * 跳转编辑页面
	 * @return
	 */
	public String editPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long strategyId = RequestUtils.getId(request);
		request.setAttribute(Constants.PAGE, "strategy");
		if(strategyId == null){
			HttpSession session = request.getSession();
			User loginContext = (User) session.getAttribute(Constants.USER);
			Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
			/**
			 * 获取新用户优惠
			 */
			List<Strategy> xList = strategyService.listStrategyByType(merchant.getId(),Constants.STRATEGY_XINYONGHU);
			if(CollectionUtils.isEmpty(xList)){
				request.setAttribute("xinyonghu", null);
			}else{
				request.setAttribute("xinyonghu", xList.get(0));
			}
			/**
			 * 获取满减优惠
			 */
			List<Strategy> mList = strategyService.listStrategyByType(merchant.getId(), Constants.STRATEGY_MANJIAN);
			if(CollectionUtils.isEmpty(mList)){
				request.setAttribute("manjian", null);
			}else{
				request.setAttribute("manjian", mList);
			}
			/**
			 * 获取折扣优惠
			 */
			List<Strategy> zList = strategyService.listStrategyByType(merchant.getId(), Constants.STRATEGY_ZHEKOU);
			if(CollectionUtils.isEmpty(zList)){
				request.setAttribute("zhekou", null);
			}else{
				request.setAttribute("zhekou", zList);
			}
			request.setAttribute("err", "url错误");
			return "index";
		}
		Strategy strategy = strategyService.getStrategyById(strategyId);
		request.setAttribute("strategy", strategy);
		return "editPage";
	}
	
	/**
	 * 执行修改操作
	 * @return
	 */
	public String doUpdate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "strategy");
		HttpSession session = request.getSession();
		User loginContext = (User) session.getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		if(strategy.getStrategyType()!=1){
			if(strategyService.validateXinYongHuUpdate(strategy)){
				request.setAttribute("strategy", strategy);
				request.setAttribute("err", "其他类型的优惠不能改为新用户优惠");
				return "index";
			}
		}
		String result = strategyService.validateStrategy(strategy);
		if(result==null){
			request.setAttribute("strategy", strategy);
			request.setAttribute("err", result);
			return "index";
		}
		/** 执行修改操作 **/
		strategyService.updateStrategy(strategy);
		/**
		 * 获取新用户优惠
		 */
		List<Strategy> xList = strategyService.listStrategyByType(merchant.getId(),Constants.STRATEGY_XINYONGHU);
		if(CollectionUtils.isEmpty(xList)){
			request.setAttribute("xinyonghu", null);
		}else{
			request.setAttribute("xinyonghu", xList.get(0));
		}
		/**
		 * 获取满减优惠
		 */
		List<Strategy> mList = strategyService.listStrategyByType(merchant.getId(), Constants.STRATEGY_MANJIAN);
		if(CollectionUtils.isEmpty(mList)){
			request.setAttribute("manjian", null);
		}else{
			request.setAttribute("manjian", mList);
		}
		/**
		 * 获取折扣优惠
		 */
		List<Strategy> zList = strategyService.listStrategyByType(merchant.getId(), Constants.STRATEGY_ZHEKOU);
		if(CollectionUtils.isEmpty(zList)){
			request.setAttribute("zhekou", null);
		}else{
			request.setAttribute("zhekou", zList);
		}
		request.setAttribute("success", "修改成功");
		return "index";
	}
	
	/**
	 * 执行删除操作
	 * @return
	 */
	public String remove(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(Constants.PAGE, "strategy");
		Long strategyId = RequestUtils.getId(request);
		if(strategyId==null){
			request.setAttribute("err", "删除失败");
		}else{
			request.setAttribute("success", "删除成功");
		}
		/** 执行删除操作 **/
		strategyService.removeStrategy(strategyId);
		HttpSession session = request.getSession();
		User loginContext = (User) session.getAttribute(Constants.USER);
		Merchant merchant = merchantService.getMerchantByUserId(loginContext.getId());
		/**
		 * 获取新用户优惠
		 */
		List<Strategy> xList = strategyService.listStrategyByType(merchant.getId(),Constants.STRATEGY_XINYONGHU);
		if(CollectionUtils.isEmpty(xList)){
			request.setAttribute("xinyonghu", null);
		}else{
			request.setAttribute("xinyonghu", xList.get(0));
		}
		/**
		 * 获取满减优惠
		 */
		List<Strategy> mList = strategyService.listStrategyByType(merchant.getId(), Constants.STRATEGY_MANJIAN);
		if(CollectionUtils.isEmpty(mList)){
			request.setAttribute("manjian", null);
		}else{
			request.setAttribute("manjian", mList);
		}
		/**
		 * 获取折扣优惠
		 */
		List<Strategy> zList = strategyService.listStrategyByType(merchant.getId(), Constants.STRATEGY_ZHEKOU);
		if(CollectionUtils.isEmpty(zList)){
			request.setAttribute("zhekou", null);
		}else{
			request.setAttribute("zhekou", zList);
		}
		return "index";
	}
	
}
