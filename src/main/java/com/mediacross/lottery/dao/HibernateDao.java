package com.mediacross.lottery.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mediacross.lottery.utils.ReflectUtils;

/**
 * 扩展HibernateDaoSupport的泛型基类
 * @author longlob
 * @param <T> 实体类型
 */
@Repository
public class HibernateDao<T> extends HibernateDaoSupport{

	protected Class<T> entityClass;

	/**
	 * 在构造函数中利用反射机制获得参数T的具体类
	 */
	public HibernateDao() {
		entityClass = ReflectUtils.getClassGenricType(getClass());
	}
	
	/**
	 * 根据实体类与ID获得对象
	 * @param clazz 实体类
	 * @param id 主键ID
	 */
    public <X> X get(final Class<X> clazz, final Serializable id) {
		return (X) getSession().get(clazz, id);
	}
    
    /**
     * 根据id获得对象
     * @param id 主键ID
     */
    public T get(Serializable id){
        return get(entityClass, id);
    }
    
    /**
     * 删除对象
     * @param entity 实体类
     */
	public void delete(final Object entity) {
		getSession().delete(entity);
	}

	/**
	 * 根据ID删除对象
	 * @param id 主键ID
	 */
	public void delete(final Serializable id) {
		delete(get(id));
	}
	
	/**
	 * 根据实体类与ID删除对象
	 * @param clazz 实体类
	 * @param id 主键ID
	 */
	public void delete(final Class clazz, final Serializable id){
		delete(get(clazz,id));
	}
    
    /**
	 * 保存对象
	 * @param entity 保存的实体对象
	 */
	public void save(final Object entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * 获取所有数据
	 * @param entityClass 参数T的反射类型
	 */
	public <X> List<X> getAll(final Class<X> entityClass) {
		return createCriteria(entityClass).list();
	}
	
	/**
	 * 获取所有数据
	 */
	public List<T> getAll() {
		return query();
	}
	
	/**
	 * 根据条件获取数据
	 * @param criterions 数量可变的Criterion
	 */
	public List<T> query(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}
	
	/**
	 * HQL方式查询
	 * @param hql 符合HQL语法的查询语句
	 * @param values 数量可变的条件值,按顺序绑定
	 */
	public Query createQuery(final String hql, final Object... values){
        Query query = getSession().createQuery(hql);
        int j = values.length;
        for(int i = 0; i < j; i++)
            query.setParameter(i, values[i]);
        return query;
    }
	/**
	 * HQL方式查询
	 * @param hql 符合HQL语法的查询语句
	 */
	public Query createQuery(final String hql){
        Query query = getSession().createQuery(hql);
        return query;
    }
	/**
	 * HQL方式查询
	 * @param hql 符合HQL语法的查询语句
	 * @功能：用于提取总记录数或一个字段一条记录的情况下使用select count(*) from members.....
	 */
	public String getOne(final String hql){        
        return getSession().createQuery(hql).list().get(0).toString();
    }
	/**
	 * SQL方式查询
	 * @param sql 符合SQL语法的查询语句
	 * @param values 数量可变的条件值,按顺序绑定
	 */
    public SQLQuery createSQLQuery(final String sql,final Object... values){
        SQLQuery query = getSession().createSQLQuery(sql);
        if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
        return query;
    }
    /**
	 * SQL方式查询
	 * @param sql 符合SQL语法的查询语句
	 */
    public SQLQuery createSQLQuery(final String sql){
        SQLQuery query = getSession().createSQLQuery(sql);
        
        return query;
    }
    /**
     * 根据类型创建查询对象
     * @param clazz 类型
     */
    public Criteria createCriteria(final Class clazz) {
		return getSession().createCriteria(clazz);
	}
    
    /**
     * 对象化查询
     * @param entityClass 参数T的反射类型
     * @param criterions 数量可变的Criterion
     */
    public Criteria createCriteria(final Class clazz, final Criterion... criterions){
        Criteria criteria = getSession().createCriteria(clazz);
        for (Criterion c : criterions) {
			criteria.add(c);
		}
        return criteria;
    }
    
    /**
     * 对象化查询
     * @param criterions 数量可变的Criterion
     */
    public Criteria createCriteria(final Criterion... criterions){
    	return createCriteria(entityClass, criterions);
    }
}