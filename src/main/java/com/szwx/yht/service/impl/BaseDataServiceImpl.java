package com.szwx.yht.service.impl;

import com.szwx.yht.common.Config;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.IBaseDataService;
import com.szwx.yht.util.DBConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@Service("baseDataService")
public class BaseDataServiceImpl implements IBaseDataService {

/*	public List<String> getBaseData(String parentData) throws ServiceException {

		List<String> list=null;
		
		String hql="select b.data from BaseData b where parent.data='"+parentData+"'";
		
		try {
			list=commonDao.queryListCache(hql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("BaseDataServiceImpl.getBaseData("+parentData+")系统异常1："+e.getMessage(),e);
		}
		
		return list;
	}*/
	private Logger log= Logger.getLogger(BaseDataServiceImpl.class);
	public List<String> getBaseData(String parentData) throws ServiceException {

		List<String> list=new ArrayList<String>();
		
		String sql="select a.DATA from YHT_BASE_DATA a ,YHT_BASE_DATA b  where a.PARENT=b.ID and b.DATA='"+parentData+"'";
		Connection  con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con= DBConnection.getConnection(Config.dbService);
			con.setAutoCommit(false);
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery(sql);
				while (rs.next()) {
					list.add(rs.getString(1));
				}
			}
		catch (Exception e1) {
			e1.printStackTrace();
				log.error("BaseDataServiceImpl.getBaseData("+parentData+")系统异常1："+e1.getMessage(),e1);
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("BaseDataServiceImpl.getBaseData("+parentData+")系统异常3："+e.getMessage(),e);
			}
		}
		
		return list;
	}
	
}
