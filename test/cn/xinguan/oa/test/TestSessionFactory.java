package cn.xinguan.oa.test;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.stereotype.Service;

@Service
public class TestSessionFactory {
	@Resource
	private SessionFactory sessionFactory;

	@Test
	public void t() {
		System.out.println(sessionFactory);
	}
}
