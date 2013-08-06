package com.szwx.yht.dao;

import com.szwx.yht.bean.KnowLedgeMoudle;
import com.szwx.yht.bean.Menu;
import com.szwx.yht.bean.Role;
import com.szwx.yht.exception.DaoException;

import java.util.Collection;

public interface IGetTreeDAO {
	/**
	 * 
	 * 获取菜单树
	 * @author zhangyj
	 * @date Jun 20, 2012
	 * @param menus 菜单集
	 * @param buf	储存字符串树
	 * @param role	角色
	 * @return
	 * @throws DaoException
	 */
	public String getMenuTree(Collection<Menu> menus, StringBuffer buf, Role role)throws DaoException;
	/**
	 * 
	 * 获取系统总菜单树
	 * @author zhangyj
	 * @date Jun 20, 2012
	 * @param menus 菜单集
	 * @param buf	储存字符串树
	 * @return
	 * @throws DaoException
	 */
	public String getMenuTree(Collection<Menu> menus, StringBuffer buf)throws DaoException;
	/**
	 * 获取角色对应的选中的系统菜单树
	 * @author zhangyj
	 * @date Jun 27, 2012
	 * @param menus
	 * @param buf
	 * @param role
	 * @return
	 * @throws DaoException
	 */
	public String getMenuTreeRole(Collection<Menu> menus, StringBuffer buf, Role role)throws DaoException;
	
	
	/**
	 * 获取总知识库树
	 *@Title: getKnowTree
	 *@author:gaowm
	 *@date:Jul 6, 2012 11:33:07 AM
	 *@param knows
	 *@param object
	 *@return
	 *@throws DaoException
	 */
	public String getKnowTree(Collection<KnowLedgeMoudle> knows, StringBuffer buf)throws DaoException;
}
