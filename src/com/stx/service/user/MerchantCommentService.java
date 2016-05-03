package com.stx.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.dao.MerchantCommentDao;
import com.stx.entity.user.MerchantComment;
import com.stx.entity.vo.CommentVo;
import com.stx.service.UserService;
import com.stx.util.CollectionUtils;

/**
 * 商户评论管理
 * @author gzzdsg
 * 2016年3月22日
 */
@Service
public class MerchantCommentService {

	@Autowired
	private MerchantCommentDao merchantCommentDao;
	@Autowired
	private UserService userService;
	
	/**
	 * 根据商户id查询评论信息
	 * @param merchantId
	 * @return
	 */
	public List<CommentVo> queryMerchantCommentByMerchantId(Long merchantId){
		List<MerchantComment> commentList = merchantCommentDao.findCommentByMerchantId(merchantId);
		if(CollectionUtils.isEmpty(commentList)){
			return null;
		}
		List<CommentVo> commentVos = new ArrayList<CommentVo>();
		CommentVo commentVo = null;
		for(MerchantComment comment : commentList){
			commentVo = new CommentVo();
			commentVo.setId(comment.getId());
			commentVo.setCommentContent(comment.getCommentContent());
			commentVo.setCreateName(comment.getCreateName());
			commentVo.setCreateTime(comment.getCreateTime());
			commentVo.setHeadPath(userService.queryHeadById(comment.getCreateBy()));
			commentVos.add(commentVo);
		}
		return commentVos;
	}
	
	/**
	 * 保存一条评论
	 * @param comment
	 * @return
	 */
	public Long saveMerchantComment(MerchantComment comment){
		return merchantCommentDao.saveComment(comment);
	}
	
}
