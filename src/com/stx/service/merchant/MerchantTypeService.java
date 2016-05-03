package com.stx.service.merchant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.dao.MerchantTypeDao;
import com.stx.entity.merchant.MerchantType;

/**
 * 商户类型管理
 * @author gzzdsg
 * 2016年3月12日
 */
@Service
public class MerchantTypeService {

	@Autowired
	private MerchantTypeDao merchantTypeDao;
	
	/**
	 * 查询全部类型
	 * @return
	 */
	public List<MerchantType> queryAllMerchantType(){
		List<MerchantType> list = merchantTypeDao.findMerchantType();
		if(list == null){
			return new ArrayList<MerchantType>();
		}
		return list;
	}
	
	/**
	 * 查询前三个类型
	 * @return
	 */
	public List<MerchantType> query3MerchantType(){
		List<MerchantType> list = merchantTypeDao.find3MerchantType();
		if(list == null){
			return new ArrayList<MerchantType>();
		}
		return list;
	}
	
	/**
	 * 保存商户类型
	 * 
	 * @param merchantType
	 * @return
	 */
	public Long saveMerchantType (String merchantTypeName){
		MerchantType merchantType = new MerchantType();
		merchantType.setMerchantType(merchantTypeName);
		return merchantTypeDao.saveMerchantType(merchantType);
	}
	
	
}
