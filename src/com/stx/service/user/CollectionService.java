package com.stx.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.dao.CollectionDao;
import com.stx.entity.user.Collection;
import com.stx.util.CollectionUtils;

/**
 * 收藏关系管理
 * 
 * @author gzzdsg 2016年3月22日
 */
@Service
public class CollectionService {

	@Autowired
	private CollectionDao collectionDao;

	/**
	 * 查询用户的所有收藏
	 * @param userId
	 * @return
	 */
	public List<Collection> findCollectionByUserId(Long userId){
		return collectionDao.findCollectionByUserId(userId);
	}
	
	/**
	 * 查询收藏关系是否存在
	 * 
	 * @param userId
	 * @param merchantId
	 * @return
	 */
	public Boolean findCollectionRelation(Long userId, Long merchantId) {
		List<Collection> collection = collectionDao.findCollectionByTwoId(userId,
				merchantId);
		if (collection == null) {
			return false;
		}
		return true;
	}

	/**
	 * 切换收藏
	 * @param userId
	 * @param merchantId
	 * @return
	 */
	public void changeCollectionRelation( Long userId, Long merchantId) {
		List<Collection> collections = collectionDao.findCollectionByTwoId(userId,
				merchantId);
		if(CollectionUtils.isEmpty(collections)){
			Collection collection = new Collection();
			collection.setMerchantId(merchantId);
			collection.setUserId(userId);
			collectionDao.saveCollection(collection);
		}else{
			for(Collection collection : collections){
				collectionDao.deleteCollection(collection);
			}
		}
	}

}
