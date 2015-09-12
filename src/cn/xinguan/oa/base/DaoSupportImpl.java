package cn.xinguan.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public DaoSupportImpl() {
		// 使用反射技术得到T的真实类型-----非常重要
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 返回的是一个类型数组，因为我们这里反省只有一个，所以获取第一个类型参数的真实类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	/**
	 * 获得当前可用的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void delete(Long id) {
		Object obj = getById(id);
		// 删除前必须保证所要删除的元素不为空
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public T getById(Long id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}

	@Override
	public List<T> getByIds(Long[] ids) {
		if (ids == null) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession().createQuery( //
					"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)") //
					.setParameterList("ids", ids)//
					.list();
		}
	}

	@Override
	public List<T> getAll() {
		return getSession().createQuery( //
				"FROM " + clazz.getSimpleName()) //
				.list();
	}
}
