package com.stx.entity.order;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单进程表
 * @author gzzdsg
 * 2016年3月14日
 */
public class OrderProcess implements Serializable{

	private static final long serialVersionUID = -5449021878744483195L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 订单唯一标识
	 */
	private Long orderId;
	
	/**
	 * 流程时间
	 */
	private Date processTime;
	
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	
	/**
	 * 订单流程状态
	 */
	private String processStatus;
	
	/**
	 * 流程描述
	 */
	private String processDesc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getProcessDesc() {
		return processDesc;
	}

	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}

	@Override
	public String toString() {
		return "OrderProcess [id=" + id + ", orderId=" + orderId
				+ ", processTime=" + processTime + ", orderStatus="
				+ orderStatus + ", processStatus=" + processStatus
				+ ", processDesc=" + processDesc + "]";
	}

}
