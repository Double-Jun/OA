package cn.xinguan.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xinguan.oa.base.DaoSupportImpl;
import cn.xinguan.oa.domain.Department;
import cn.xinguan.oa.service.DepartmentService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements DepartmentService {

	@Resource
	private SessionFactory sessionFactory;

	/** 查找所有顶级部门 */
	public List<Department> findTopList() {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d WHERE d.parent IS NULL")//
				.list();
	}

	/** 通过id查找此部门的所有下属（只查下一级）部门 */
	public List<Department> findChildren(Long parentId) {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d WHERE d.parent.id=?")//
				.setParameter(0, parentId)//
				.list();
	}

}
