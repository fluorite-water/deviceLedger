package com.wlt.deviceledger.controller.user;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wlt.deviceledger.bean.user.UserBaseMapperDempBean;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.service.user.IUserService;


/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年9月15日 下午3:32:34 
* @since JDK 1.8.0_181
*/
@Controller
@RequestMapping("/DemoController")
public class DemoController {
	
	private static final  Logger log = LogManager.getLogger(DemoController.class);
	@Autowired
	private IUserService userService;
	@RequestMapping("/demo")
	@ResponseBody
	public List<UserBean> demo() {
		List<UserBean> list = userService.findUserList();
		log.error("执行完成--error---------------------");
		return list;
	}
	@RequestMapping("/baseMapperDemo")
	@ResponseBody
	public List<UserBaseMapperDempBean> baseMapperDemo() {
		log.debug("debug---执行完成------");
		log.info("info----执行完成-----------------");
		
		List<UserBaseMapperDempBean> list = userService.baseMapperDemo();
		return list;
	}
	@RequestMapping("/baseMapperDemo2")
	@ResponseBody
	public List<UserBaseMapperDempBean> baseMapperDemo2() {
		log.debug("debug---执行完成------");
		log.info("info----执行完成-----------------");
		
		List<UserBaseMapperDempBean> list = userService.baseMapperDemo();
		return list;
	}
}
 