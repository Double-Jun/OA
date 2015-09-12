package cn.xinguan.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xinguan.oa.base.DaoSupportImpl;
import cn.xinguan.oa.domain.User;
import cn.xinguan.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {

	@Override
	public User getByLoginNameAndPassword(String loginName, String password) {
		return (User) getSession().createQuery(//
				"from User u where u.loginName=? and u.password=?")//
				.setParameter(0, loginName)//
				.setParameter(1, password)//
				.uniqueResult();
	}

}
