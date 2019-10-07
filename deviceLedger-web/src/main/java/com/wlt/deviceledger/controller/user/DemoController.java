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
import com.wlt.deviceledger.util.base.ResultData;
import com.wlt.deviceledger.util.common.PropertiesLoader;


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
//	@Scheduled(cron = "0/5 * * * * ?")   //定时器测试
	public ResultData demo() {
		// 返回结果集
		ResultData resultData =new ResultData();
		resultData.setCode("200");
		resultData.setMsg("请求成功");
		resultData.setSuccess(true);
		List<UserBean> list = userService.findUserList();
		resultData.setObj(list);
		log.error("执行完成--error---------------------");
		log.error("执行完成--error---------------------");
		System.out.println("async----------1");
		System.out.println("async----------2");
		return resultData;
	}
	@RequestMapping("/baseMapperDemo")
	@ResponseBody
	public List<UserBaseMapperDempBean> baseMapperDemo() {
		log.debug("debug---执行完成------");
		log.info("info----执行完成-----------------");
		
		List<UserBaseMapperDempBean> list = userService.baseMapperDemo();
		return list;
	}
	
	/**
	 * 异步执行测试：请将 异步@Async 注解打在实现类的方法上，切不可打在接口的方法上
	 * @return
	 */
	@RequestMapping("testAsync")
	@ResponseBody
	public String testAsync() {
//		for (int i = 0; i < 100000; i++) {
//			System.out.println(i);
//			userService.testAsync1("a");
//		}
		userService.testAsync1("a");
		userService.testAsync1("a");
		userService.testAsync1("a");
		userService.testAsync1("a");
		userService.testAsync1("a");
		userService.testAsync2("b");
		userService.testAsync2("b");
		userService.testAsync2("b");
		userService.testAsync2("b");
		userService.testAsync2("b");
		userService.testAsync2("b");
		System.out.println("111111111111111111111");
		System.out.println("222222222222222222222");
		log.info("info --------------------");
		return "success";
		
	}
	
	/**
	 * 事务测试方法
	 */
	@RequestMapping("testTx")
	@ResponseBody
	public String testTx() {
		UserBaseMapperDempBean bean = new UserBaseMapperDempBean();
		bean.setAge("100");
		bean.setUserId("qqqqqqq");
		bean.setUsername("事务测试");
		userService.add(bean);
		int i=10/0;
		return "success";
	}
	
	/**
	 * 读取 properties文件
	 */
	@RequestMapping("testProperties")
	@ResponseBody
	public String ReadPropertiesTest() {
		String property = new PropertiesLoader("applicationContext.properties").getProperty("textStr");
		System.out.println(property);
		return property;
	}
}
 