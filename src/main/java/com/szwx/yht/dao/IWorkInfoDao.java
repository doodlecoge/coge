package com.szwx.yht.dao;

import java.util.List;

public interface IWorkInfoDao {
	public List findDtWork(String dtId, String dpId, String workDate,
                           int workType);

	public List findDpWork(String dpId, String workDate, int workType);
	
	public List findRegOrderByCode(long RegCode);
	
	public List findRegIdByWork(String dtId, String dpId, String workDate,
                                int workType);
}
