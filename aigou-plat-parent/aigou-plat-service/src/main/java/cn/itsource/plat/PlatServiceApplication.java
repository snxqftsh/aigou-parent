package cn.itsource.plat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableEurekaClient  //标识是客户端
@EnableSwagger2  //开启Swagger接口文档的支持
public class PlatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatServiceApplication.class,args);
    }

}
