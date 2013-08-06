package com.szwx.yht.dao.impl;

import com.szwx.yht.bean.KnowLedgeBase;
import com.szwx.yht.bean.KnowLedgeMoudle;
import com.szwx.yht.common.CommonDao;
import com.szwx.yht.dao.GConsultationDAO;
import com.szwx.yht.exception.DaoException;
import com.szwx.yht.util.Page;
import com.szwx.yht.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("consultationDao")
public class GConsultationDAOImpl  extends CommonDao implements GConsultationDAO {

	public List<KnowLedgeBase> getListByPageAndMoudle(Page page,
			Object[] ids,String[] keys) throws DaoException {
		String id="";
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null&&!ids[i].toString().equals("")){
				if(i!=ids.length-1){
					id+=ids[i]+",";
				}else{
					id+=ids[i];
				}
				
			}
		}
		String hql=" from KnowLedgeBase k where 1=1 ";
		if(!"".equals(id.trim())){
			hql+=" and k.moudle.id in ("+id+")";
		}
		if(keys!=null){
			for(int i=0;i<keys.length;i++){
				if(!StringUtil.isNull(keys[i])){
					hql+=" and (";
						hql+=" k.askwer like '%"+keys[i]+"%'  or k.question like '%"+keys[i]+"%' ";
						hql+=" )";
				}
			}
			
		}
		hql+=" order by length(k.askwer) asc ";
		try {
			page.setTotalCount(queryList(hql).size());
			return queryList(hql,page.getPageNum(),page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("GConsultationDAOImpl.getListByPageAndMoudle 查询异常1："+e.getMessage(),e);
		}
		
	}


	public String getKnowMoudleIdList(int no) throws DaoException {
		StringBuffer str=new StringBuffer();
		String hql=" from KnowLedgeMoudle k where k.parent.id="+no;
		List<KnowLedgeMoudle> list=this.getHibernateTemplate().find(hql);
		for (int i = 0; i < list.size(); i++) {
			str.append(getKnowMoudleIdList(list.get(i).getId()));
		}		
		str.append(no).append(",");
		return str.toString();
	}


	


}
