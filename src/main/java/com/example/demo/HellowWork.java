package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class HellowWork {
	
	//注入配置文件中的参数
	@Value("${name}")
	private String name;
	@Value("${age}")
	private Integer age;
	
	
	@RequestMapping("/index/{name}")
    @ResponseBody
    public String index(@PathVariable String name){
      
        if(null==name){
            name="boy";
        }
        
        return "hello world" +name;
	}
	/**
	 * 
	 * 从配置文件里面读取数据
	 * @return
	 */
	@RequestMapping("/propertiesData")
	public String propertiesData() {
		return name+age;
	}
	
	@RequestMapping("/getName")
	public String getName(String name) {
		return name;
	}
}
