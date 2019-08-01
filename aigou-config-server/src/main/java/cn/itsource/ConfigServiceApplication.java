package cn.itsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer     //开启配置服务端
public class ConfigServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ConfigServiceApplication.class,args);
    }

}
