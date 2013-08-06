package com.szwx.yht.dao;

import com.szwx.yht.bean.KnowLedgeBase;
import com.szwx.yht.exception.DaoException;
import com.szwx.yht.util.Page;

import java.util.List;


public interface GConsultationDAO {
	/**
	 * 查出所有数据中节点对应的知识 并实现分页,按关键字key搜索
	 *@Title: getListByPageAndMoudle
	 *@author:gaowm
	 *@date:Jul 7, 2012 8:22:10 AM
	 *@param page
	 *@param ids
	 *@return
	 *@throws DaoException
	 */
	List<KnowLedgeBase> getListByPageAndMoudle(Page page,
                                               Object[] ids, String[] keys)throws DaoException;

	/**
	 * 递归出某节点所有的子节点
	 *@Title: getKnowMoudleIdList
	 *@author:gaowm
	 *@date:Jul 7, 2012 8:22:13 AM
	 *@param no
	 *@return
	 *@throws DaoException
	 */
	String getKnowMoudleIdList(int no)throws DaoException;
	



}
