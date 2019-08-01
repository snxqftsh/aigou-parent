package cn.itsource.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy  //标识 开启网关
@EnableEurekaClient //标识的客户端、（可以忽略）
public class ZuulClientApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ZuulClientApplication.class,args);
    }
}
