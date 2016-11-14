package cn.hankchan.stu.shiro.test;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@RequiresRoles("admin")
	@RequestMapping("/index.html")
	public ModelAndView getIndex() throws Exception {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}
}
