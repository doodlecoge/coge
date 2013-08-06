package com.szwx.yht.dao.impl;

import com.szwx.yht.common.CommonDao;
import com.szwx.yht.dao.IWorkInfoDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("workInfoDao")
public class WorkInfoDaoImpl extends CommonDao implements IWorkInfoDao {

	public List findDpWork(String dpId, String workDate, int workType) {
		String hql = "from RegPipelined t2 where t2.workSchema.doctorExpert is null and t2.workSchema.depart.departCodeNo = '"
				+ dpId
				+ "' and t2.workSchema.workDate = to_date('"
				+ workDate
				+ "','yyyy-MM-dd') and t2.workType = " + workType;
		return this.getHibernateTemplate().find(hql);
	}

	public List findDtWork(String dtId, String dpId, String workDate,
			int workType) {
		String hql = "from RegPipelined t2 where t2.workSchema.doctorExpert is not null and t2.workSchema.doctorExpert.forWorkNo = '"+dtId+"' and t2.workSchema.depart.departCodeNo = '"
			+ dpId
			+ "' and t2.workSchema.workDate = to_date('"
			+ workDate
			+ "','yyyy-MM-dd') and t2.workType = " + workType;
		return this.getHibernateTemplate().find(hql);
	}
	
	public List findRegOrderByCode(long RegCode) {
		String hql = "from RegOrder t where t.yLId = "+RegCode;
		return this.getHibernateTemplate().find(hql);
	}
	
	
	public List findRegIdByWork(String dtId, String dpId, String workDate,
			int workType) {
		String hql = "";
		if (dtId == null || "".equals(dtId)) {
			hql = "select t.yLId from RegOrder t where t.regPipelined.workSchema.doctorExpert is  null and  t.regPipelined.workSchema.depart.departCodeNo = '"
				+ dpId
				+ "' and t.regPipelined.workSchema.workDate = to_date('"
				+ workDate
				+ "','yyyy-MM-dd') and t.regPipelined.workType = " + workType + " and t.state = 1";
		} else {
			hql = "select t.yLId from RegOrder t where t.regPipelined.workSchema.doctorExpert is not null and t.regPipelined.workSchema.doctorExpert.forWorkNo = '"+dtId+"' and t.regPipelined.workSchema.depart.departCodeNo = '"
			+ dpId
			+ "' and t.regPipelined.workSchema.workDate = to_date('"
			+ workDate
			+ "','yyyy-MM-dd') and t.regPipelined.workType = " + workType + " and t.state = 1";
		}
		return this.getHibernateTemplate().find(hql);
	}
	
}
