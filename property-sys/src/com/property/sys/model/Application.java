package com.property.sys.model;

import java.io.Serializable;
/**
 * 住户申请表
 */
public class Application implements Serializable{
	private static final long serialVersionUID = -4379479268216645724L;
	
	public static final String TYPE_CHECK_IN="入住申请";
	public static final String TYPE_REPAIR="报修申请";
	public static final String TYPE_FITMENT="装修申请";
	
	public static final String STATUS_APPLYING="申请中";
	public static final String STATUS_COMPLETE="已完成";
	
	private Integer id;
	private String type;//申请类型
	private String applyTime;//申请时间
	private String completeTime;//完成时间
	private String content;//申请内容
	private String status;//状态
	private String reply;//回复内容
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
}