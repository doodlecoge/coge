package com.szwx.yht.common;

import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * hibernate数据库查询
 * @author zhangyj
 * @date Mar 20, 2012
 */
@Repository("commonDAO")
public class CommonDao extends HibernateDaoSupport {
	@Resource(name = "sessionFactory")
	public void setSessionFactoryIN(SessionFactory sessionFactory){
		 super.setSessionFactory(sessionFactory);
	}
	/**
	 * 保存对象的方法
	 * @param obj 要保存的对象
	 * @return 该对象的序列化主键
	 */
	public Object save(Object obj)throws Exception{
		return getHibernateTemplate().save(obj);
	}
	
	/**
	 * 修改对象
	 * @param obj 修改后的对象
	 */
	public void update(Object obj)throws Exception{
		getHibernateTemplate().update(obj);
	}
	
	public Object merge(Object obj)throws Exception{
		return getHibernateTemplate().merge(obj);
	}
	
	public void saveOrUpdate(Object obj)throws Exception{
		 getHibernateTemplate().saveOrUpdate(obj);
	}
	public void saveOrUpdate(Collection obj)throws Exception{
		 getHibernateTemplate().saveOrUpdateAll(obj);
	}
	/**
	 * 删除对象 
	 * @param obj 要删除的对象
	 */
	public void delete(Object obj)throws Exception{
		getHibernateTemplate().delete(obj);
	}
	
	/**
	 * 根据主键获取对象
	 * @param cls 获取对象的Class
	 * @param id 获取对象的主键
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public Object get(Class cls,Serializable id)throws Exception{
		return  getHibernateTemplate().get(cls, id);
	}
	
	/**
	 * 根据主键加载实例
	 * @param cls 要加载对象的Class
	 * @param id 要加载对象的主键
	 * @return 加载的对象
	 */
	@SuppressWarnings("unchecked")
	public Object load(Class cls,Serializable id)throws Exception{
		return getHibernateTemplate().load(cls, id);
	}
	
	/**
	 * 根据hql查询 所有对象的集合
	 * @param hql hql语句
	 * @return 所有对象的集合
	 */
	@SuppressWarnings("unchecked")
	public List queryList(String hql)throws Exception{
		return getHibernateTemplate().find(hql);
	}
	
	/**
	 * 根据SQL查询
	 * @author zhangyj
	 * @date May 21, 2012
	 * @param sql
	 * @param cache
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List queryListBySql(Class c ,String sql,boolean cache)throws Exception{
		
		return this.getSession().createSQLQuery(sql).addEntity(c).setCacheable(false).list();
		
	}
	public List queryListBySql(String sql)throws Exception{
		
		return this.getSession().createSQLQuery(sql).list();
		
	}
	/**
	 * 根据 Class 查询 所有对象的集合
	 * @param cls  Class
	 * @return 所有对象的集合
	 */
	@SuppressWarnings("unchecked")
	public List queryList(final Class cls,final boolean cache)throws Exception{
		return (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(cls).setCacheable(false).list();
			}
		});
	}
	
	
	/**
	 * 根据hql 语句 查询 信息
	 * @param hql 语句
	 * @param pageIndex 当前页
	 * @param pageSize 每页显示条目
	 * @return 当前页下的信息结合
	 */
	@SuppressWarnings("unchecked")
	public List queryList(final String hql,final int pageIndex,final int pageSize)throws Exception{
		return (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult((pageIndex-1)*pageSize);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List queryList(final String hql,final int pageIndex,final int pageSize,final boolean cache)throws Exception{
		return (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql).setCacheable(cache);
				query.setFirstResult((pageIndex-1)*pageSize);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}
	
	/**
	 * 根据sql 语句 查询 信息
	 * @param sql 语句
	 * @param pageIndex 当前页
	 * @param pageSize 每页显示条目
	 * @return 当前页下的信息结合
	 */
	@SuppressWarnings("unchecked")
	public List queryListBySql(final String sql,final int pageIndex,final int pageSize)throws Exception{
		return (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				query.setFirstResult((pageIndex-1)*pageSize);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}
	
	/**
	 * 根据hql 语句 查询 信息 缓存
	 * @param hql 语句
	 * @return 所有对象的集合
	 */
	@SuppressWarnings("unchecked")
	public List queryListCache(final String hql)throws Exception{
		return (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				Query query = session.createQuery(hql).setCacheable(true);
				return query.list();
			}
		});
	}
	
	/**
	 * criteria 的分页查询
	 * @param criteria DetachedCriteria 对象
	 * @param pageIndex 当前页
	 * @param pageSize 每页显示条目
	 * @return 当前页的集合
	 */
	@SuppressWarnings("unchecked")
	public List queryList(final DetachedCriteria criteria,final int pageIndex,final int pageSize)throws Exception{
		return (List)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria c = criteria.getExecutableCriteria(session);
				c.setFirstResult((pageIndex-1)*pageSize);
				c.setMaxResults(pageSize);
				return c.list();
			}	
		});
	}
	/**
	 * 模糊查询
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryList(final DetachedCriteria criteria,final boolean cache)throws Exception{
		return (List)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria c = null;
					c = criteria.getExecutableCriteria(session).setCacheable(false);
				return c.list();
			}	
		});
	}

	/**
	 * 根据hql 返回唯一记录的的方法
	 * @param hql hql语句
	 * @return 唯一记录
	 */
	public Object unique(final String hql)throws Exception{
		return getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(hql).uniqueResult();
			}
		});
	}
	
	

	/**
	 * 根据 DetachedCriteria 查询唯一结果的方法
	 * @param criteria DetachedCriteria
	 * @return 查询唯一结果
	 */
	public Object unique(final DetachedCriteria criteria)throws Exception{
		return getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return criteria.getExecutableCriteria(session).uniqueResult();
			}
		});
	}

	
	public Object uniqueClass(final DetachedCriteria criteria)throws Exception{
		return getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return criteria.getExecutableCriteria(session).setMaxResults(1).uniqueResult();
			}
		});
	}
	//查询总行数
	public int getCount(final DetachedCriteria criteria)throws Exception
	{

        Number number = (Number) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				return criteria.getExecutableCriteria(s).setProjection(Projections.rowCount()).uniqueResult();
			}
		});

        return number.intValue();
	}

}
