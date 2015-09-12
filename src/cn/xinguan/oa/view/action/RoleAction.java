package cn.xinguan.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.xinguan.oa.base.BaseAction;
import cn.xinguan.oa.domain.Privilege;
import cn.xinguan.oa.domain.Role;

import com.opensymphony.xwork2.ActionContext;

/**
 * 岗位Action业务类
 * 
 * @author MingJun Chen
 * 
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private Long[] privilegeIds; // 权限id

	/** 列表 */
	public String list() throws Exception {
		List<Role> roleList = roleService.getAll();
		// 将查找的实体存放到Action内容里
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// // 1.根据提交的岗位名称和描述初始化一个Role
		// Role role = new Role(model.getName(), model.getDescription());
		// // 2.保存Role
		roleService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1.根据id查找要修改的实体，
		Role role = roleService.getById(model.getId());
		// 2.准备回显修改页面的数据
		ActionContext.getContext().getValueStack().push(role);// 将数据放在栈顶
		// this.name = role.getName();
		// this.description = role.getDescription();
		// 3.跳转到修改页面
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1.根据id找到所要修改的实体
		Role role = roleService.getById(model.getId());
		// 2.修改该实体中德属性值
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		System.out.println(role.getName() + "-----" + model.getId());
		// 3.更新到数据库
		roleService.update(role);
		// 4.返回现实页面
		return "toList";
	}

	/** 设置权限页面 */
	public String setPrivilegeUI() throws Exception {
		// 1.根据id查找要修改的实体，
		Role role = roleService.getById(model.getId());
		// 2.准备回显修改页面的数据
		ActionContext.getContext().getValueStack().push(role);// 将数据放在栈顶

		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for (Privilege priv : role.getPrivileges()) {
				privilegeIds[index++] = priv.getId();
			}
		}
		// 准备数据(所有权限)
		List<Privilege> privilegeList = privilegeService.getAll();
		ActionContext.getContext().put("privilegeList", privilegeList);

		// 3.跳转到修改页面
		return "setPrivilegeUI";
	}

	/** 设置权限 */
	public String setPrivilege() throws Exception {
		// 1.根据id找到所要修改的实体
		Role role = roleService.getById(model.getId());

		// 2.修改该实体中的属性值
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));

		// 3.更新到数据库
		roleService.update(role);
		// 4.返回现实页面
		return "toList";
	}

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
