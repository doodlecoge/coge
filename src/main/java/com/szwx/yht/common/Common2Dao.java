package com.szwx.yht.common;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("common2DAO")
public class Common2Dao extends HibernateDaoSupport {
	
	@Resource(name = "sessionFactory")
	public void setSessionFactoryIN(SessionFactory sessionFactory){
		 super.setSessionFactory(sessionFactory);
	}

	public String queryScwxId()throws Exception{
		String sql="select max(counter) as maxid from tmpMax";
		this.getSession().createSQLQuery("update tmpMax set counter = counter+1").executeUpdate();
		String result=this.getSession().createSQLQuery(sql).list().get(0).toString();
		return result;
		
	}

	public int uploadRegOrder(String sql) {
		try {
			Object o=this.getSession().createSQLQuery(sql).executeUpdate();
//			System.out.println(o.toString());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;//系统出错
		}
	}
	public List querySql(String sql)throws Exception{
		try {
			return (List<String[]>)this.getSession().createSQLQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
