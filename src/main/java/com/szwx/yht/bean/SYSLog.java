package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统操作日志实体BEAN
 * @author zhangyj
 * @date Jun 18, 2012
 */
@Entity
@Table(name="YHT_SYS_LOG")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
public class SYSLog implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4115279257041563421L;
	@Id
	@SequenceGenerator(name="SEQ_ID_GEN",allocationSize=1,sequenceName="SEQ_SYS_LOG_YHT")
	@GeneratedValue(generator="SEQ_ID_GEN",strategy= GenerationType.SEQUENCE)
	private long id;				//ID
	@Column(name="OPEATE_CLASS")
	private int opeateClass;		//操作类型 1：增加 2：修改 3：删除 4：查询
	@Column(name="MODULE_TYPE")
	private int moduleType;			//日志类别 1：登陆 2：知识库 3：通话保持 4：通话恢复 5：。。。
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTimee;		//生成时间
	@Column(name="OPEATER_DESC",nullable=false)
	private String opeaterDesc;		//操作描述
	@ManyToOne
	@JoinColumn(name="OPEATER_USER",nullable=true)
	private User opeaterUser;		//操作者 null:系统

	
	////////////////////////GET  SET//////////////////////////
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOpeateClass() {
		return opeateClass;
	}

	public void setOpeateClass(int opeateClass) {
		this.opeateClass = opeateClass;
	}

	public int getModuleType() {
		return moduleType;
	}

	public void setModuleType(int moduleType) {
		this.moduleType = moduleType;
	}

	public Date getCreateTimee() {
		return createTimee;
	}

	public void setCreateTimee(Date createTimee) {
		this.createTimee = createTimee;
	}

	public String getOpeaterDesc() {
		return opeaterDesc;
	}

	public void setOpeaterDesc(String opeaterDesc) {
		this.opeaterDesc = opeaterDesc;
	}

	public User getOpeaterUser() {
		return opeaterUser;
	}

	public void setOpeaterUser(User opeaterUser) {
		this.opeaterUser = opeaterUser;
	}
}
