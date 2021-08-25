package com.baizhi.util;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQ {
    private static ConnectionFactory factory;

    static {
        factory = new ConnectionFactory();
        //设置rabbitmq服务器IP地址
        factory.setHost("192.168.87.139");
        //设置rabbitmq服务器连接端口
        factory.setPort(5672);
        //设置rabbitmq服务器虚拟主机
        factory.setVirtualHost("2101");
        //设置rabbitmq服务器用户名
        factory.setUsername("zhang");
        //设置rabbitmq服务器密码
        factory.setPassword("zhang");
        //设置ip解析过程中的超时时间
        factory.setHandshakeTimeout(30000);
    }

    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = factory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Channel channel,Connection connection){
        try {
            if (channel!=null)channel.close();
            if(connection!=null)connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
