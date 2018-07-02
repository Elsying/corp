package com.test;

import java.io.IOException;
import java.sql.SQLException;

import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;

import com.yuansheng.resultful.util.plug.QRCode;

import redis.clients.jedis.Jedis;

public class Main {
	 public static void main(String[] args) {
//	        //连接本地的 Redis 服务
//	        Jedis jedis = new Jedis("localhost");
//	        System.out.println("连接成功");
//	        //查看服务是否运行
//	        System.out.println("服务正在运行: "+jedis.ping());
		 QRCode qr=new QRCode();
		 qr.createQRcode("D:/test/code.png");
	    }

}
