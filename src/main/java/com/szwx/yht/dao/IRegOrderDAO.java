package com.szwx.yht.dao;

import com.szwx.yht.bean.Hospital;
import com.szwx.yht.bean.RegOrder;
import com.szwx.yht.bean.RegPeople;
import com.szwx.yht.exception.DaoException;
import com.szwx.yht.util.Page;

import java.util.Date;
import java.util.List;

public interface IRegOrderDAO {
    /**
     * 条件查询挂号记录
     *
     * @param hospital
     * @param departName
     * @param doctorName
     * @param userName
     * @param registerName
     * @param beginTime
     * @param endTime
     * @param status
     * @param page
     * @return
     * @throws DaoException
     * @Title: queryRegOrders
     * @author:gaowm
     * @date:Jul 20, 2012 5:48:34 PM
     */
    List<RegOrder> queryRegOrders(Hospital hospital, String departName,
                                  String doctorName, String userName, String registerName,
                                  Date beginTime, Date endTime, Integer status, Page page, String telPhone, Date regStratTime, Date regEndTime) throws DaoException;


    List<RegOrder> queryRegOrders(String id, String name) throws DaoException;

    /**
     * 查询黑名单
     *
     * @param telPhone
     * @param registerName
     * @param page
     * @return
     * @throws DaoException
     * @Title: queryBlackPeople
     * @author:gaowm
     * @date:Jul 20, 2012 5:48:48 PM
     */
    List<RegPeople> queryBlackPeople(String telPhone, String registerName,
                                     Page page, Integer isBlack) throws DaoException;

    /**
     * 更新黑民单
     *
     * @throws DaoException
     * @Title: updateBlackPeople
     * @author:gaowm
     * @date:Jul 25, 2012 5:07:17 PM
     */
    void updateBlackPeople();

    List<RegOrder> getRegOrdersForBlack(RegPeople regPeople) throws DaoException;

    int queryDaoQuitNum(Long code) throws DaoException;

    public int queryDaoQuitNum(String pid) throws DaoException;


}
