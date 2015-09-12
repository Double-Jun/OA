package cn.xinguan.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.xinguan.oa.base.BaseAction;
import cn.xinguan.oa.domain.Department;
import cn.xinguan.oa.domain.Role;
import cn.xinguan.oa.domain.User;
import cn.xinguan.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private Long departmentId; // 所属部门id
	private Long[] roleIds; // 岗位id

	/** 列表 */
	public String list() throws Exception {
		List<User> userList = userService.getAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据， departmentList
		List<Department> topList = departmentService.findTopList(); // 得到所有顶点部门
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备数据， roleList
		List<Role> roleList = roleService.getAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 封装到对象中（当model是实体类型时，也可以使用model，但要设置未封装的属性）
		// >> 设置所属部门
		model.setDepartment(departmentService.getById(departmentId));
		// >> 设置关联岗位
		List<Role> rolelist = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(rolelist));
		// >> 设置默认密码为1234
		model.setPassword("1234");
		// 保存到数据库
		userService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备数据， departmentList
		List<Department> topList = departmentService.findTopList(); // 得到所有顶点部门
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据， roleList
		List<Role> roleList = roleService.getAll();
		ActionContext.getContext().put("roleList", roleList);

		// 准备回显数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);

		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles() != null) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}

		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1. 从数据库中找到要修改的对象准备回显
		User user = userService.getById(model.getId());

		// 2. 设置要修改的属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// 设置关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		// 设置所属部门
		user.setDepartment(departmentService.getById(departmentId));

		// 3. 更新到数据库
		userService.update(user);

		return "toList";
	}

	/** 初始化密码为1234 */
	public String initPassword() throws Exception {
		// 这里和修改步骤一样, 1. 从数据库拿出要修改的对象
		User user = userService.getById(model.getId());

		// 2. 设置要修改的属性
		user.setPassword("1234");

		// 3. 保存修改好的对象
		userService.update(user);

		return "toList";
	}

	/** 登陆页面 */
	public String loginUI() throws Exception {
		return "loginUI";
	}

	/** 登陆 */
	public String login() throws Exception {
		User user = userService.getByLoginNameAndPassword(model.getLoginName(), model.getPassword());
		if (user == null) {
			// 登陆失败，提示错误信息
			addFieldError("login", "用户名或密码不正确！");
			return "loginUI";
		} else {
			// 登陆成功，保存Session
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
	}

	/** 注销 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}

	// --------------------------------

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}
