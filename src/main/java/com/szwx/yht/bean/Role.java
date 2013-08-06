package com.szwx.yht.bean;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色实体BEAN
 * @author zhangyj
 * @date Jun 14, 2012
 */
@Entity
@Table(name="YHT_ROLE")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 576479000819306425L;
	@Id
	@SequenceGenerator(name = "SEQ_ID_GEN", sequenceName = "SEQ_ROLE_YHT", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_GEN")
	private int id;					//ID,唯一标示
	@Column(name="ROLE_NAME",unique=true,nullable=false,length=15)
	private String roleName;		//角色名称
	@Column(name="ROLE_DESC")
	private String desc;			//角色描述
	@Temporal(TemporalType.DATE)
	@Column(name="ADD_TIME")
	private Date addTime;			//添加日期
	@ManyToMany
	@JoinTable(
			name="YHT_ROLE_MENU",
			joinColumns=@JoinColumn(name="ROLE_ID"),
			inverseJoinColumns=@JoinColumn(name="MENU_ID")
	)
	@OrderBy("id asc,orderCode asc")
	private List<Menu> menus=new ArrayList<Menu>();		//菜单集
	
	//////////////////////GET SET//////////////////////////////////////
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}
