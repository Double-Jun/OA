package cn.xinguan.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.xinguan.oa.base.BaseAction;
import cn.xinguan.oa.domain.Department;
import cn.xinguan.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;

/** 部门Action业务层 */
@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	private Long parentId;

	/** 列表 */
	public String list() throws Exception {
		List<Department> departmentList = null;
		// 1.查找所有部门
		if (parentId == null) {
			departmentList = departmentService.findTopList();
		} else {
			departmentList = departmentService.findChildren(parentId);
			Department parent = (Department) departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		// 2.将部门数据存放到ActionContext中Map中
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1.根据id找到所要修改的部门,作为回显数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 2.将查找到的数据放入ActionContext栈顶
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}

		return "saveUI";
	}

	/** 修改 */

	public String edit() throws Exception {
		// 1.从数据库中取出原对象
		Department department = departmentService.getById(model.getId());
		Department parent = departmentService.getById(parentId);
		// 2.设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(parent);
		// 3.更新到数据库
		departmentService.update(department);

		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		List<Department> topList = departmentService.findTopList(); // 得到所有顶点部门
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1.根据parentId找到上级部门
		Department parent = departmentService.getById(parentId);
		// 2.设置上级部门
		model.setParent(parent);
		// 3.保存
		departmentService.save(model);
		return "toList";
	}

	// --------------数据封装
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
