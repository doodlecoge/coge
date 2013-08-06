package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 知识库实体BEAN
 * @author zhangyj
 * @date Jun 15, 2012
 */
@Entity
@Table(name="YHT_KNOWLEDGE_BASE")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(dynamicUpdate=true,selectBeforeUpdate=true,dynamicInsert=true)
public class KnowLedgeBase implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7871904622192048775L;
	@Id
	@SequenceGenerator(name="SEQ_ID_GEN",sequenceName="SEQ_KNOWLEDGEBASE_YHT",allocationSize=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_ID_GEN")
	private long id;				//ID
	@ManyToOne
	@JoinColumn(name="MODULE",nullable=false)
	private KnowLedgeMoudle moudle;		//所属模块
	@Column(name="QUESTION",length=100)
	private String question;		//问题
	@Column(name="ASKWER")
	private String askwer;			//答案描述
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ADD_TIME")
	private Date addTime;			//知识库添加时间	
	@ManyToOne
	@JoinColumn(name="LAST_USER",nullable=true)
	private User lastUser;			//该知识内容的最后操作者  null:系统导入
	@Column(name="CODE",length=16)
	private String code;			//编码
	
	///////////////////////GET SET//////////////////////////
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAskwer() {
		return askwer;
	}
	public void setAskwer(String askwer) {
		this.askwer = askwer;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public User getLastUser() {
		return lastUser;
	}
	public void setLastUser(User lastUser) {
		this.lastUser = lastUser;
	}
	public KnowLedgeMoudle getMoudle() {
		return moudle;
	}
	public void setMoudle(KnowLedgeMoudle moudle) {
		this.moudle = moudle;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
