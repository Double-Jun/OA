package cn.xinguan.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.xinguan.oa.domain.Department;

public class DepartmentUtils {

	public static List<Department> getAllDepartments(List<Department> topList) {
		List<Department> list = new ArrayList<Department>();
		walkDepartmentTreeList(topList, "┡ ", list);
		return list;
	}

	/**
	 * 遍历部门树，把信息存放在list数组中
	 * 
	 * @param topList
	 *            顶点部门集合
	 * @param prefix
	 *            前缀
	 * @param list
	 *            存放集合
	 */
	private static void walkDepartmentTreeList(Collection<Department> topList, String prefix, List<Department> list) {
		for (Department top : topList) {
			// 顶点
			// 这里千万要注意不要直接对top直接操作，下面两行代码实际上执行了update语句，改变了数据库中数据
			// top.setName(prefix + top.getName());
			// list.add(top);
			// 子节点

			Department copy = new Department();
			copy.setId(top.getId());
			copy.setName(prefix + top.getName());
			list.add(copy);
			walkDepartmentTreeList(top.getChildren(), "　" + prefix, list);
		}

	}
}
