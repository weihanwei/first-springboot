package com.example.demo;

import java.io.File;
import java.util.Properties;

import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
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
	
	//@Value("${pythonFileUrl}")
	private String pythonFileUrl;
	
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
	/**
	 * http://localhost:9394/springbootDemo/test/testPy
	 * @return
	 */
	@RequestMapping("/testPy")
	public String testPy() {
		 Runtime rt = Runtime.getRuntime();

		
		
	        try {
	        	/*
	        	File path = new File(ResourceUtils.getURL("classpath:").getPath());
	    		if(!path.exists()) path = new File("");
	    		System.out.println("path:"+path.getAbsolutePath());
	    		*/
		        Process proc = rt.exec(pythonFileUrl+"/test.py");
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	
		return name;
	}
	
	
	public static void main(String[] args) {
		Properties props = new Properties();
        props.put("python.home", "path to the Lib folder");
        props.put("python.console.encoding", "UTF-8");

        props.put("python.security.respectJavaAccessibility", "false");

        props.put("python.import.site", "false");

        Properties preprops = System.getProperties();

		
		PythonInterpreter itp = new PythonInterpreter();
		itp.exec("days=('Mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");
		itp.exec("print days[1];");
		itp.execfile("/tmp/test.py");
		itp.exec("print 'created by tengxing on 2017.3'");
	}
}
