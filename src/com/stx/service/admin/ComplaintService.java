package com.stx.service.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.dao.ComplaintDao;
import com.stx.entity.admin.Complaint;
import com.stx.util.StringUtils;

@Service
public class ComplaintService {

	@Autowired
	private ComplaintDao complaintDao;

	/**
	 *  展示投诉信息
	 *  
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Complaint> listComplaint(String startDateStr, String endDateStr) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = null;
			Date endDate = null;
			if(StringUtils.isNotEmpty(startDateStr)){
				startDate = sdf.parse(startDateStr);
			}
			if(StringUtils.isNotEmpty(endDateStr)){
				endDate = sdf.parse(endDateStr);
			}
			return complaintDao.listComplaintTimeDesc(startDate,endDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
