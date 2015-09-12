package cn.xinguan.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xinguan.oa.base.DaoSupportImpl;
import cn.xinguan.oa.domain.Privilege;
import cn.xinguan.oa.service.PrivilegeService;

/** 权限Service实现类 */
@Service
@Transactional
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService {

	@Override
	public List<Privilege> findTopList() {
		return getSession().createQuery( //
				"from Privilege p where p.parent is null")//
				.list();
	}

}
