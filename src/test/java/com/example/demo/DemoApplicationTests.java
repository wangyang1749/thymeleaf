package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	TemplateEngine templateEngine;


	@Test
	void contextLoads() {
	}

	@Test
	public void test(){
		Context context = new Context();  //thymeleaf包下的
		Map map = new HashMap();
		map.put("name","zhangsan");
		// 1.1 存入数据
		context.setVariables(map);
		// 2 输出流
		File file = new File("a.html");
		try(PrintWriter writer = new PrintWriter(file,"UTF-8")){ //流在小括号里面会被自动的释放
			//生成HTML
			templateEngine.process("index2",context,writer);
		}catch (Exception e){
			System.out.println("静态页方法异常"+e);
		}

	}

}
