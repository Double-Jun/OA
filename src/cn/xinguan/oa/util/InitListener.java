package cn.xinguan.oa.util;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.xinguan.oa.domain.Privilege;
import cn.xinguan.oa.service.PrivilegeService;

public class InitListener implements ServletContextListener {

	private PrivilegeService privilegeService;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 获得容器和相关Service对象
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");

		// 准备数据:topPrivilegeList
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		sce.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("------------> 已准备数据topPrivilegeList <------------");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}
}
