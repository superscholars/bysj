package com.stx.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.dao.UserDao;
import com.stx.entity.Constants;
import com.stx.entity.User;
import com.stx.util.StringUtils;

/**
 * 用户，管理员，商户登陆、注册同意管理
 * @author gzzdsg
 * 2016年3月8日
 */

@Service(value="userService")
public class UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 商户登陆用户注册
	 * 
	 * @param user
	 * @param password2
	 * @return	成功返回空，失败返回失败原因
	 */
	public String registMerchantUser(User user, String password2) {
		if(user.getLoginName()==null||user.getLoginName().trim().length()<1){
			return "登陆名不能为空";
		}
		if(user.getNickName()==null||user.getNickName().trim().length()<1){
			return "昵称不能为空";
		}
		if(user.getPassword()==null||user.getPassword().trim().length()<1){
			return "密码不能为空";
		}
		if(password2==null||password2.trim().length()<1){
			return "请再次输入密码";
		}
		if(!user.getPassword().trim().equals(password2)){
			return "两次密码输入不一致";
		}
		/** 设置用户类型为商家 **/
		user.setUserType(3);
		/** 设置登陆开关为商户申请中 **/
		user.setLoginSwitch(3);
		/** 超管标识 **/
		user.setAdminFlag(0);
		/** 设置注册时间 **/
		user.setCreateTime(new Date());
		
		/** 检查登陆名是否已经存在 **/
		if(userDao.existsUserByLoginName(user.getLoginName())){
			return "该登陆名已经存在";
		}
		
		/** 检查昵称是否已经存在 **/
		if(userDao.existsUserByNickName(user.getNickName())){
			return "昵称已经存在";
		}
		
		/** 保存用户信息 **/
		Long userId = userDao.saveUser(user);
		if(userId==null){
			return "注册失败";
		}
		
		return null;
	}
	
	/**
	 * 用户注册
	 * 
	 * @param user
	 * @param password2
	 * @return	成功返回空，失败返回失败原因
	 */
	public String registUser(User user, String password2) {
		if(user.getLoginName()==null||user.getLoginName().trim().length()<1){
			return "登陆名不能为空";
		}
		if(user.getNickName()==null||user.getNickName().trim().length()<1){
			return "昵称不能为空";
		}
		if(user.getPassword()==null||user.getPassword().trim().length()<1){
			return "密码不能为空";
		}
		if(password2==null||password2.trim().length()<1){
			return "请再次输入密码";
		}
		if(!user.getPassword().trim().equals(password2)){
			return "两次密码输入不一致";
		}
		/** 设置用户类型为用户 **/
		user.setUserType(2);
		/** 设置登陆开关为能够登陆**/
		user.setLoginSwitch(1);
		/** 超管标识 **/
		user.setAdminFlag(0);
		/** 设置注册时间 **/
		user.setCreateTime(new Date());
		
		/** 检查登陆名是否已经存在 **/
		if(userDao.existsUserByLoginName(user.getLoginName())){
			return "该登陆名已经存在";
		}
		/** 检查昵称是否已经存在 **/
		if(userDao.existsUserByNickName(user.getNickName())){
			return "昵称已经存在";
		}
		/** 保存用户信息 **/
		Long userId = userDao.saveUser(user);
		if(userId==null){
			return "注册失败";
		}
		return null;
	}

	/**
	 * 获取登陆用户信息
	 * 
	 * @param user
	 * @return
	 */
	public User merchantLogin(User user){
		return userDao.findUser(user);
	}
	
	/**
	 * 修改用户头像
	 * 
	 * @param user
	 */
	public String updateUserHead(User user, File head, String headPath, String headFileName){
		if (headFileName == null) {
			return "头像文件名不能为空。";
		}
		String headEnd = headFileName.substring(headFileName.length() - 4,
				headFileName.length());
		if (!headEnd.equals(".jpg") && !headEnd.equals(".png")) {
			return "头像只支持jpg或png格式。";
		}
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(head));
			OutputStream os = new BufferedOutputStream(new FileOutputStream(
					new File(headPath, user.getLoginName() + headEnd)));
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
		user.setHeadPath(user.getLoginName()+headEnd);
		userDao.updateUserInfo(user);
		return null;
	}
	
	/**
	 * 修改密码
	 * 
	 * @param user	原用户信息
	 * @param oldPassword	旧密码
	 * @param newPassword	新密码
	 * @param newPassword2	确认新密码
	 * @return
	 */
	public String updatePassword(User user, String oldPassword, String newPassword, String newPassword2){
		if(oldPassword==null||oldPassword.trim().length()<1){
			return "旧密码不能为空。";
		}
		if(!oldPassword.equals(user.getPassword())){
			return "旧密码不正确。";
		}
		if(newPassword==null||newPassword.trim().length()<1){
			return "新密码不能为空。";
		}
		if(newPassword2==null||newPassword2.trim().length()<1){
			return "确认密码不能为空。";
		}
		if(!newPassword.equals(newPassword2)){
			return "两次输入新密码不一致";
		}
		user.setPassword(newPassword);
		userDao.updateUserInfo(user);
		return null;
	}
	
	/**
	 * 根据id查询头像
	 * @param id
	 * @return
	 */
	public String queryHeadById(Long id){
		User user = userDao.findUserById(id);
		if(user==null){
			return "head.jpg";
		}
		return user.getHeadPath();
	}
	
	// ======================管理员模块
	
	/**
	 * 查出所有待审核的商户
	 * @return
	 */
	public List<User> queryNotCheckMerchant(){
		return userDao.findUserBySwitch(Constants.LOGIN_SWITCH_WAITING);
	}
	
	/**
	 * 变更状态
	 * @param loginFLag
	 */
	public void changeUserLoginFlag(Long userId, Integer loginSwitch){
		User user = userDao.findUserById(userId);
		user.setLoginSwitch(loginSwitch);
		userDao.updateUserInfo(user);
	}
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> queryAllUser(){
		return userDao.findUserByType(Constants.USER_TYPE);
	}
	
	/**
	 * 改变登陆状态
	 * @param userId
	 * @param status
	 */
	public void changeLoginSwitch(Long userId, Integer status){
		User user = userDao.findUserById(userId);
		user.setLoginSwitch(status);
		userDao.updateUserInfo(user);
	}
	
	
	/**
	 * 根据id获取用户信息
	 * @param userId
	 * @return
	 */
	public User getUserById(Long userId){
		return userDao.findUserById(userId);
	}
	
	/**
	 * 查询所有管理员
	 * @return
	 */
	public List<User> queryAdmin(){
		return userDao.findUserByType(Constants.ADMIN_TYPE);
	}
	
	/**
	 * 添加管理账户
	 * @param user
	 * @return
	 */
	public String addAdmin(User user){
		if(StringUtils.isEmpty(user.getLoginName())){
			return "用户名不能为空";
		}
		if(StringUtils.isEmpty(user.getNickName())){
			return "昵称不能为空";
		}
		if(StringUtils.isEmpty(user.getPassword())){
			return "密码不能为空";
		}
		if(userDao.existsUserByLoginName(user.getLoginName())){
			return "用户名已存在";
		}
		if(userDao.existsUserByNickName(user.getNickName())){
			return "昵称已存在";
		}
		user.setAdminFlag(0);
		user.setCreateTime(new Date());
		user.setHeadPath("head.jpg");
		user.setLoginSwitch(Constants.LOGIN_SWITCH_OPEN);
		user.setUserType(Constants.ADMIN_TYPE);
		Long id = userDao.saveUser(user);
		if(id == null || id == 0){
			return "添加失败";
		}
		return null;
	}
	
}
