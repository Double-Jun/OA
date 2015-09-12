package cn.xinguan.oa.service;

import java.util.List;

import cn.xinguan.oa.base.DaoSupport;
import cn.xinguan.oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department> {

	List<Department> findTopList();

	List<Department> findChildren(Long parentId);

}
