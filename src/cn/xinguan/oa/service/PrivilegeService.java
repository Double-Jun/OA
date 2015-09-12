package cn.xinguan.oa.service;

import java.util.List;

import cn.xinguan.oa.base.DaoSupport;
import cn.xinguan.oa.domain.Privilege;

/** 权限Service借口 */
public interface PrivilegeService extends DaoSupport<Privilege> {

	List<Privilege> findTopList();

}
