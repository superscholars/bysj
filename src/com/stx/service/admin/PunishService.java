package com.stx.service.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.dao.PunishDao;
import com.stx.entity.User;
import com.stx.entity.admin.Punish;
import com.stx.service.merchant.MerchantService;
import com.stx.util.DateUtils;
import com.stx.util.StringUtils;

/**
 * 处罚商户信息
 * @author gzzdsg
 */
@Service
public class PunishService {

	@Autowired
	private PunishDao punishDao;
	@Autowired
	private MerchantService merchantService;
	
	/**
	 * 处罚商户
	 * @param operator
	 * @param merchantId
	 * @param merchantName
	 * @param reason
	 * @param punishDays
	 * @return
	 */
	public String punishMerchant(User operator ,Long merchantId, String merchantName, String reason,Integer punishDays){
		if(StringUtils.isEmpty(reason)){
			return "原因不能为空";
		}
		if(punishDays==null||punishDays==0){
			return "处罚天数不能为空，或0";
		}
		merchantService.changeMerchantOpen(merchantId, 2);
		Punish punish = new Punish();
		punish.setCreateTime(new Date());
		punish.setMerchantId(merchantId);
		punish.setMerchantName(merchantName);
		punish.setPunishDays(punishDays);
		punish.setPunishReason(reason);
		punish.setUserId(operator.getId());
		punish.setUserName(operator.getNickName());
		Long id = punishDao.savePunish(punish);
		if(id == null || id == 0){
			return "处罚失败";
		}
		return null;
	}
	
	/**
	 * 验证是否在出发期间
	 * @param merchantId
	 * @return
	 */
	public Double validatePunish(Long merchantId){
		Punish punish = punishDao.getPunish(merchantId);
		if(punish==null){
			return 0d;
		}
		Date startDate = punish.getCreateTime();
		Date endDate  = DateUtils.addDate(startDate, punish.getPunishDays());
		Date now = new Date();
		if(endDate.getTime() > now.getTime()){
			return Math.ceil((endDate.getTime() - now.getTime()) / (1000 * 60 * 60d));
		}
		return 0d;
	}
	
}
