package cn.aqi.chatgpt;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: $NAME
 * @Author Xu Jing
 * @Package cn.aqi
 * @Date 2024/1/3 19:16
 * @description:${Description}
 */
@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Logger logger = LoggerFactory.getLogger(Application.class);
        logger.info("Application 启动成功。。。。。。。");
    }
}