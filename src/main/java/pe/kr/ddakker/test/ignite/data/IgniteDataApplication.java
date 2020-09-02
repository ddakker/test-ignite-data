package pe.kr.ddakker.test.ignite.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "pe.kr.ddakker.test.ignite.data.app.mapper")
public class IgniteDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(IgniteDataApplication.class, args);
    }

}
