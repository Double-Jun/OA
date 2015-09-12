package cn.xinguan.oa.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

	@Test
	public void testBean() {
		TestAction testAction = (TestAction) ac.getBean("testAction");
		System.out.println(testAction);
	}

	// 测试SessionFactory
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sf);
	}

	@Test
	// 测试事务管理
	public void testTransaction() throws Exception {
		TestService testService = (TestService) ac.getBean("testService");
		testService.saveTwoUser();
	}

}
