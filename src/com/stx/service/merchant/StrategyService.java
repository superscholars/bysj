package com.stx.service.merchant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.dao.StrategyDao;
import com.stx.entity.merchant.Strategy;
import com.stx.entity.vo.StrategyVo;
import com.stx.util.CollectionUtils;

/**
 * 策略管理
 * @author gzzdsg
 * 2016年3月13日
 */
@Service
public class StrategyService {

	@Autowired
	private StrategyDao strategyDao;
	
	/**
	 * 查询该商户和类型下的所有优惠策略
	 * @param merchantId
	 * @return
	 */
	public List<Strategy> listStrategyByType(Long merchantId,Integer type){
		return strategyDao.findStrategyByMerchantAndType(merchantId,type);
	}
	
	/**
	 * 获取大额度优惠
	 * @return
	 */
	public List<StrategyVo> queryHotStrategy(){
		List<Strategy> list = strategyDao.findHotStrategye();
		List<StrategyVo> reList = new ArrayList<StrategyVo>();
		StrategyVo strategyVo = null;
		for(Strategy strategy : list){
			strategyVo = new StrategyVo();
			strategyVo.setMerchantId(strategy.getMerchantId());
			switch(strategy.getStrategyType()){
				case 1:
					strategyVo.setStrategy("新用户立减"+strategy.getBalancePrice()+"元");
					reList.add(strategyVo);
					break;
				case 2:
					strategyVo.setStrategy("满"+strategy.getPremisePrice()+"元减免"+strategy.getBalancePrice()+"元");
					reList.add(strategyVo);
					break;
				case 3:
					strategyVo.setStrategy("满"+strategy.getPremisePrice()+"元立享"+strategy.getDiscount()+"折优惠");
					reList.add(strategyVo);
					break;
			}
		}
		return reList;
	}
	
	/**
	 * 查询一个商家的所有优惠
	 * @param merchantId
	 * @return
	 */
	public String getStrategyShow(Long merchantId){
		/** 获取新用户减免 **/
		List<Strategy> xinList = strategyDao.findStrategyByMerchantAndType(merchantId,1);
		String xin = "";
		if(CollectionUtils.isNotEmpty(xinList)){
			xin = "新用户立减"+xinList.get(0).getBalancePrice()+"元;";
		}
		/** 获取满减优惠 **/
		List<Strategy> manList = strategyDao.findStrategyByMerchantAndType(merchantId,2);
		String man = "";
		if(CollectionUtils.isNotEmpty(manList)){
			for(Strategy strategy : manList){
				man = man + "满"+strategy.getPremisePrice()+"元减免"+strategy.getBalancePrice()+"元;";
			}
		}
		/** 获取折扣优惠 **/
		List<Strategy> zheList = strategyDao.findStrategyByMerchantAndType(merchantId, 3);
		String zhe = "";
		if(CollectionUtils.isNotEmpty(zheList)){
			for(Strategy strategy : zheList){
				zhe = zhe + "满"+strategy.getPremisePrice()+"元立享"+strategy.getDiscount()+"折优惠;";
			}
		}
		return xin+man+zhe;
	}
	
	/**
	 *添加优惠策略
	 * @param strategy
	 * @return
	 */
	public Long saveStrategy(Strategy strategy){
		return strategyDao.saveStrategy(strategy);
	}
	
	/**
	 * 验证添加参数
	 * @param strategy
	 * @return
	 */
	public String validateStrategy(Strategy strategy){
		switch(strategy.getStrategyType()){
			case 1:
				if(strategy.getBalancePrice()==null){
					return "优惠额度不能空";
				}
				break;
			case 2:
				if(strategy.getPremisePrice()==null){
					return "优惠前提不能为空";
				}
				if(strategy.getBalancePrice()==null){
					return "优惠额度不能空";
				}
				break;
			case 3:
				if(strategy.getDiscount()==null){
					return "优惠折扣不能为空";
				}
		}
		return null;
	}
	
	/**
	 * 验证是否为可以添加新用户
	 * @param strategy
	 * @return
	 */
	public Boolean validateXinYongHu(Strategy strategy){
		List<Strategy> list = strategyDao.findStrategyByMerchantAndType(strategy.getMerchantId(), strategy.getStrategyType());
		return CollectionUtils.isEmpty(list);
	}
	
	/**
	 * 验证修改为新用户
	 * @param strategy
	 * @return
	 */
	public Boolean validateXinYongHuUpdate(Strategy strategy){
		return strategyDao.getEntity(strategy.getId()).getStrategyType()==1;
	}
	
	/**
	 * 获取一个策略实例
	 * @param strategyId
	 * @return
	 */
	public Strategy getStrategyById(Long strategyId){
		return strategyDao.getEntity(strategyId);
	}
	
	/**
	 * 执行修改操作
	 * @param strategy
	 */
	public void updateStrategy(Strategy strategy){
		strategyDao.updateStrategy(strategy);
	}
	
	/**
	 * 执行删除操作
	 * @param strategy
	 */
	public void removeStrategy(Long strategyId){
		Strategy strategy = new Strategy();
		strategy.setId(strategyId);
		strategyDao.removeStrategy(strategy);
	}
	
}
