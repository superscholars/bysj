package com.stx.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.entity.merchant.Merchant;
import com.stx.entity.user.Address;
import com.stx.service.merchant.MerchantService;
import com.stx.service.user.AddressService;
import com.stx.util.RequestUtils;

/**
 * 个人中心
 * @author gzzdsg
 * 2016年3月23日
 */
public class PersonAction extends ActionSupport implements ModelDriven<Address>{

	private static final long serialVersionUID = 3377424296542235284L;

	private Address address = new Address();
	
	@Override
	public Address getModel() {
		// TODO Auto-generated method stub
		return address;
	}
	
	
	@Autowired
	private AddressService addressService;
	@Autowired
	private MerchantService merchantService;
	
	
	/**
	 * 打开个人中心
	 * @return
	 */
	public String info(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginContext = RequestUtils.getUser(request);
		request = infoCondition(request,loginContext);
		return "index";
	}
	
	/**
	 * 添加地址
	 * @return
	 */
	public String addAddress(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginContext = RequestUtils.getUser(request);
		/** 添加地址默认转台为非默认选中 **/
		address.setStatus(2);
		String result = addressService.validate(address);
		if(result!=null){
			request.setAttribute("err", result);
		}else{
			if("男".equals(address.getSex())){
				address.setSex("先生");
			}
			if("女".equals(address.getSex())){
				address.setSex("女士");
			}
			addressService.saveAddress(address);
		}
		request = infoCondition(request,loginContext);
		return "index";
	}
	
	/**
	 * 删除地址
	 * @return
	 */
	public String deleteAddress(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long addressId = RequestUtils.getId(request);
		User loginContext = RequestUtils.getUser(request);
		addressService.removeAddress(addressId);
		request = infoCondition(request,loginContext);
		return "index";
	}
	
	/**
	 * 修改为默认
	 * @return
	 */
	public String changeAddress(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long addressId = RequestUtils.getId(request);
		User loginContext = RequestUtils.getUser(request);
		addressService.changeAddress(loginContext.getId(), addressId);
		request = infoCondition(request,loginContext);
		return "index";
	}
	
	/**
	 * 进入个人中心携带的参数
	 * @param request
	 * @param loginContext
	 * @return
	 */
	private HttpServletRequest infoCondition(HttpServletRequest request, User loginContext){
		List<Address> addressList = addressService.queryAddressByUserId(loginContext.getId());
		request.setAttribute("addressList", addressList);
		List<Merchant> collectionMerchant = merchantService.getCollectionMerchant(loginContext.getId());
		request.setAttribute("merchantList", collectionMerchant);
		request.setAttribute("user", loginContext);
		request.setAttribute(Constants.PAGE, "person");
		return request;
	}

}
