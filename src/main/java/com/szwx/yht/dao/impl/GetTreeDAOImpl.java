package com.szwx.yht.dao.impl;

import com.szwx.yht.bean.KnowLedgeMoudle;
import com.szwx.yht.bean.Menu;
import com.szwx.yht.bean.Role;
import com.szwx.yht.common.CommonDao;
import com.szwx.yht.dao.IGetTreeDAO;
import com.szwx.yht.exception.DaoException;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 系统递归出树
 * @author zhangyj
 * @date Jun 20, 2012
 */
@Repository("getTreeDAO")
public class GetTreeDAOImpl extends CommonDao implements IGetTreeDAO{

	public String getMenuTree(Collection<Menu> menus, StringBuffer buf,
			Role role) throws DaoException {
		if(buf==null){
			buf=new StringBuffer();
		}
		for(Menu module:menus){
			
			String sql=" select rt.* from YHT_MENU rt,YHT_ROLE_MENU sr where rt.PARENT ='"+module.getId()+"' " +
			"	and rt.Id=sr.MENU_ID and sr.ROLE_ID = "+role.getId()+" and rt.STATE=2 order by rt.ORDER_CODE asc ";
			
			SQLQuery query=this.getSession().createSQLQuery(sql);
			query.addEntity(Menu.class).setCacheable(true);
			List<Menu> list=query.list();
			buf.append(" <item text='"+module.getMenuName()+"' id='"+module.getId()+"' open=\"1\" ");
			//复选框选中状态
		/*
		 	if(role.getMenus().contains(module)){
				buf.append("checked=\"1\"");
			}
		*/
			buf.append(">");
			
			if(list.size()==0){
				buf.append(" <userdata name=\"myUrl\">"+module.getLinkUrl()+"</userdata>");
			}
			
			getMenuTree(list,buf,role);//递归出所有的功能模块
			
			buf.append("</item>");
		}
			
		/*	if(null!=list&&!list.isEmpty()){
				buf.append("<item text='");
				
				buf.append(module.getMenuName()+"' id='"+module.getId()+"' open=\"0\" ");
				
				if(!StringUtil.isNull(module.getLinkUrl())){
					buf.append(" url='"+module.getLinkUrl()+"' ");
				}
				
				buf.append(">");
				getMenuTree(list,buf,role);//递归出所有的功能模块
				
				buf.append("</item>");
			}else {
				buf.append("<userdata name=\"myUrl\">");
				
				buf.append(module.getMenuName()+"' ");
				
				if(!StringUtil.isNull(module.getLinkUrl())){
					buf.append(" url='"+module.getLinkUrl()+"' ");
				}
				
				buf.append("</userdata>");
			}*/
		
			
		return buf.toString();
	}

	public String getMenuTree(Collection<Menu> menus, StringBuffer buf)
			throws DaoException {
		if(buf==null){
			buf=new StringBuffer();
		}
		for(Menu module:menus){
			
			String sql=" select rt.* from YHT_MENU rt where rt.PARENT ='"+module.getId()+"' " +
			"  and rt.STATE<>1 order by rt.ORDER_CODE asc ";
			
			SQLQuery query=this.getSession().createSQLQuery(sql);
			query.addEntity(Menu.class).setCacheable(false);
			List<Menu> list=query.list();
			buf.append(" <item text='"+module.getMenuName()+"' id='"+module.getId()+"' open=\"1\" ");
			//复选框选中状态
			//buf.append("checked=\"1\"");
			buf.append(">");
			
			if(list.size()==0){
				buf.append(" <userdata name=\"myUrl\">"+module.getLinkUrl()+"</userdata>");
			}
			
			getMenuTree(list,buf);//递归出所有的功能模块
			
			buf.append("</item>");
		}
			
			
		return buf.toString();
	}

	public String getMenuTreeRole(Collection<Menu> menus, StringBuffer buf,
			Role role) throws DaoException {
		if(buf==null){
			buf=new StringBuffer();
		}
		for(Menu module:menus){
			
			String sql=" select rt.* from YHT_MENU rt where rt.PARENT ='"+module.getId()+"' " +
			"  and rt.STATE<>1 order by rt.ORDER_CODE asc ";
			
			SQLQuery query=this.getSession().createSQLQuery(sql);
			query.addEntity(Menu.class).setCacheable(false);
			List<Menu> list=query.list();
			buf.append(" <item text='"+module.getMenuName()+"' id='"+module.getId()+"' open=\"1\" ");
			//复选框选中状态
			if(role.getMenus().contains(module)){
				buf.append("checked=\"1\"");
			}
			buf.append(">");
			
			if(list.size()==0){
				buf.append(" <userdata name=\"myUrl\">"+module.getLinkUrl()+"</userdata>");
			}
			
			getMenuTreeRole(list,buf,role);//递归出所有的功能模块
			
			buf.append("</item>");
		}
			
			
		return buf.toString();
	}

	public String getKnowTree(Collection<KnowLedgeMoudle> knows,StringBuffer buf)
			throws DaoException {
		if(buf==null){
			buf=new StringBuffer();
		}
		for(KnowLedgeMoudle module:knows){
			
			String sql=" select rt.* from YHT_KNOWLEDGE_MOUDLE rt where rt.PARENT ='"+module.getId()+"' " +
			"  order by rt.ORDER_NO asc ";
			
			SQLQuery query=this.getSession().createSQLQuery(sql);
			query.addEntity(KnowLedgeMoudle.class).setCacheable(false);
			List<KnowLedgeMoudle> list=query.list();
			buf.append(" <item text='"+module.getMoudleName()+"' id='"+module.getId()+"' ");
			//复选框选中状态
			//buf.append("checked=\"1\"");
			buf.append(">");
			getKnowTree(list,buf);//递归出所有的功能模块
			
			buf.append("</item>");
		}
			
			
		return buf.toString();
	}
	
}
