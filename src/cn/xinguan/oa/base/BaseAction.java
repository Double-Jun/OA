package cn.xinguan.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.xinguan.oa.service.DepartmentService;
import cn.xinguan.oa.service.PrivilegeService;
import cn.xinguan.oa.service.RoleService;
import cn.xinguan.oa.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	// =================ModelDriven的支持===============
	protected T model;

	public BaseAction() {
		try {
			// 通过反射获得T的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T getModel() {
		return model;
	}

	/** =============各种 Service 声明========== */
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected RoleService roleService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;
}
