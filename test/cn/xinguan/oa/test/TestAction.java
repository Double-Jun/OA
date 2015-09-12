package cn.xinguan.oa.test;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller("testAction")
@Scope("prototype")
public class TestAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private TestService testService;

	@Override
	public String execute() throws Exception {
		testService.saveTwoUser();
		System.out.println("------>TestAction.execute()");
		return "success";
	}
}
