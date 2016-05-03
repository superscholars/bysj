package com.stx.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.dao.AddressDao;
import com.stx.entity.user.Address;
import com.stx.util.StringUtils;

/**
 * 地址管理
 * @author gzzdsg
 * 2016年3月23日
 */
@Service
public class AddressService {
	
	@Autowired
	private AddressDao addressDao;
	
	/**
	 * 根据用户id查询地址
	 * @param userId
	 * @return
	 */
	public List<Address> queryAddressByUserId(Long userId){
		return addressDao.findAddressesByUserId(userId);
	}
	
	/**
	 * 获取一个实例
	 * @param addressId
	 * @return
	 */
	public Address getEntity(Long addressId){
		return addressDao.getEntity(addressId);
	}
	
	/**
	 * 验证插入
	 * @param adress
	 * @return
	 */
	public String validate(Address address){
		if(address==null){
			return "提交失败。";
		}
		if(StringUtils.isEmpty(address.getAddressAttr())){
			return "地址信息为空。";
		}
		if(StringUtils.isEmpty(address.getHouseNumber())){
			return "门牌号为空。";
		}
		if(StringUtils.isEmpty(address.getMobile())){
			return "联系方式为空。";
		}
		if(StringUtils.isEmpty(address.getRealName())){
			return "真实姓名为空。";
		}
		if(StringUtils.isEmpty(address.getSex())){
			return "没选择性别。";
		}
		if(address.getUserId()==null){
			return "用户id获取失败";
		}
		return null;
	}
	
	/**
	 * 保存一个地址
	 * @param address
	 */
	public void saveAddress(Address address){
		addressDao.saveAddress(address);
	}
	
	/**
	 * 删除一个地址
	 * @param addressId
	 */
	public void removeAddress(Long addressId){
		Address address = addressDao.getEntity(addressId);
		addressDao.removeAddress(address);
	}
	
	/**
	 * 修改默认选择的状态
	 * @param userId
	 * @param addressId
	 */
	public void changeAddress(Long userId, Long addressId){
		List<Address> addressList = addressDao.findAddressesByUserId(userId);
		if(addressList==null){
			return ;
		}
		for(Address address : addressList){
			if(address.getId() == addressId){
				address.setStatus(1);
				addressDao.updateAddress(address);
			}else{
				if(address.getStatus() == 1){
					address.setStatus(2);
					addressDao.updateAddress(address);
				}
			}
		}
	}
	
	
}
