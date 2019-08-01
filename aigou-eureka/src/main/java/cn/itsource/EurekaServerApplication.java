package cn.itsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //标识是服务端
public class EurekaServerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(EurekaServerApplication.class,args);
    }
}
