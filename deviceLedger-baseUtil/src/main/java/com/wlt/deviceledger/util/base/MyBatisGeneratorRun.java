package com.wlt.deviceledger.util.base;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月6日 下午3:40:44 
* @Description 类说明 ： 自动生成实体类  执行main
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public class MyBatisGeneratorRun {
	 public static void main(String[] args) throws URISyntaxException {
	        try {
	            List<String> warnings = new ArrayList<String>();
	            boolean overwrite = true;
	            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	            InputStream is = classloader.getResourceAsStream("generatorConfig-Java.xml");
	            ConfigurationParser cp = new ConfigurationParser(warnings);
	            Configuration config = cp.parseConfiguration(is);
	            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
	            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
	            myBatisGenerator.generate(null);
	            System.out.println("------实体类已经生成----");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } catch (InvalidConfigurationException e) {
	            e.printStackTrace();
	        } catch (XMLParserException e) {
	            e.printStackTrace();
	        }
	    }
}
 