package com.szwx.yht.bean;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="YHT_COMPLAINT_REPLY")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class ComplaintReply  implements Serializable{

	/**
	 *
	 *@author:gaowm
	 *@date:2013-1-8 上午11:01:14
	 */
	private static final long serialVersionUID = -3564163801579259543L;
	
	@Id
	@SequenceGenerator(allocationSize=1,name="SEQ_ID_GEN",sequenceName="SEQ_COMPLAINT_REPLY_YHT")
	@GeneratedValue(generator="SEQ_ID_GEN",strategy= GenerationType.SEQUENCE)
	@Column(name="RID")
	private Long rid;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REPLY_TIME")
	private Date replyTime;
	
	@ManyToOne
	@JoinColumn(name="REPLY_USER",nullable=false)
	private User repUser;
	
	@Column(name="REPLY_CONTENT",nullable=false,length=4000)
	private String content;//回复内容
	@Column(name="RECOVERY_UNIT")
	private String recoveryUnit;//回复的单位
	
	@Column(name="SPARE_ONE")
	private Long spareOne;//备用1
	
	@Column(name="SPARE_TWO")
	private String spareTwo;//备用2
	
	
	@ManyToOne
	@JoinColumn(name="COMPLAINT")
	private Complaint complaint;
	
	public ComplaintReply(){
	}
	public ComplaintReply(Long rid, Date replyTime, User repUser,
			String content, String recoveryUnit, Long spareOne,
			String spareTwo, Complaint complaint) {
		super();
		this.rid = rid;
		this.replyTime = replyTime;
		this.repUser = repUser;
		this.content = content;
		this.recoveryUnit = recoveryUnit;
		this.spareOne = spareOne;
		this.spareTwo = spareTwo;
		this.complaint = complaint;
	}
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public User getRepUser() {
		return repUser;
	}
	public void setRepUser(User repUser) {
		this.repUser = repUser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRecoveryUnit() {
		return recoveryUnit;
	}
	public void setRecoveryUnit(String recoveryUnit) {
		this.recoveryUnit = recoveryUnit;
	}
	public Long getSpareOne() {
		return spareOne;
	}
	public void setSpareOne(Long spareOne) {
		this.spareOne = spareOne;
	}
	public String getSpareTwo() {
		return spareTwo;
	}
	public void setSpareTwo(String spareTwo) {
		this.spareTwo = spareTwo;
	}
	public Complaint getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	

}
