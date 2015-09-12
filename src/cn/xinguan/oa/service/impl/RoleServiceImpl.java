package cn.xinguan.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xinguan.oa.base.DaoSupportImpl;
import cn.xinguan.oa.domain.Role;
import cn.xinguan.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService {

}
