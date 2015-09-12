package cn.xinguan.oa.service;

import cn.xinguan.oa.base.DaoSupport;
import cn.xinguan.oa.domain.User;

public interface UserService extends DaoSupport<User> {

	/**
	 * 根据用户名和密码验证用户
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	User getByLoginNameAndPassword(String loginName, String password);

}
