package com.dream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author dream
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class OaApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(OaApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
             "  _____                           \n" +
                " |  __ \\                          \n" +
                " | |  | |_ __ ___  __ _ _ __ ___  \n" +
                " | |  | | '__/ _ \\/ _` | '_ ` _ \\ \n" +
                " | |__| | | |  __/ (_| | | | | | |\n" +
                " |_____/|_|  \\___|\\__,_|_| |_| |_|\n" +
                "                                  \n" +
                "                                  ");
    }
}
