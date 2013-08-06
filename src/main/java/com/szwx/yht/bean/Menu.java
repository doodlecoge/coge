package com.szwx.yht.bean;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限菜单实体BEAN
 * @author zhangyj
 * @date Jun 14, 2012
 */
@Entity
@Table(name="YHT_MENU")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Menu implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7862947151159023339L;
	@Id
	@Column(name="ID")
	@SequenceGenerator(allocationSize=1,name="SEQ_ID_GEN",sequenceName="SEQ_MENU_YHT")
	@GeneratedValue(generator="SEQ_ID_GEN",strategy= GenerationType.SEQUENCE)
	private int id;				//ID，唯一标示
	@ManyToOne
	@JoinColumn(name="PARENT")	//上级菜单
	private Menu parent;
	@Column(name="MENU_NAME",length=15)	//菜单名称
	private String menuName;
	@Column(name="LINK_URL",length=50)	//跳转链接
	private String linkUrl;
	@Column(name="ORDER_CODE")	//排序编码
	private int orderCode;
	@Column(name="STATE")
	private int state 	;		//状态 0：不可删除的 1:临时 2：有效 
	
	//////////////////GET  SET////////////////////////
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
