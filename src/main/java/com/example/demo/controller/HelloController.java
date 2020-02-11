package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafView;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView();
        InternalResourceView internalResourceView = new InternalResourceView("index.jsp");
        mv.setView(internalResourceView);
        return  mv;
    }

    @RequestMapping("/hello2")
    public ModelAndView helloThymeleaf(){
        ModelAndView mv = new ModelAndView();
//        ThymeleafView thymeleafView = new ThymeleafView("index2.html");
//        mv.setView(thymeleafView);
        mv.addObject("name","zhangsan");
        mv.setViewName("index2");
        return mv;
    }

    @GetMapping(value = "/hello3", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String index(Model model) {
//        model.addAttribute("title", "thymeleaf");
        model.addAttribute("name", "Hello Thymeleaf");
        Context context = new Context();
        context.setVariables(model.asMap());
        String result = templateEngine.process("index2", context);
        System.out.println(result);
        return result;
    }

    @RequestMapping("/hello4")
    @ResponseBody
    public String testIndex(String name){
        Context context = new Context();  //thymeleaf包下的
        Map map = new HashMap();
        map.put("name",name);
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
        return "index2";
    }
}
