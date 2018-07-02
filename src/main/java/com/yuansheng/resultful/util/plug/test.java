package com.yuansheng.resultful.util.plug;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
 * 生成ben和dao层
 * 
 */

public class test {
	
//	public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
//		List<String> warnings = new ArrayList<String>();
//		boolean overwrite = true;
//		//获取路径，如果有中文路径需要转码
//		String path=java.net.URLDecoder.decode(ClassLoader.getSystemResource("mbg.xml").getPath(),"utf-8");
//		//java.net.URLDecoder.decode(configPath,"utf-8");
//		File configFile = new File(path);
//		ConfigurationParser cp = new ConfigurationParser(warnings);
//		Configuration config = cp.parseConfiguration(configFile);
//	DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//		myBatisGenerator.generate(null);
//		
//	
//}
}
