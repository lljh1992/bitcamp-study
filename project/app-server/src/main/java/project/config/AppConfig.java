package project.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import project.util.SqlSessionFactoryProxy;

@ComponentScan(basePackages = {
        "project.dao",
        "project.controller",
        "project.service"})
public class AppConfig {

    public AppConfig() {
        System.out.println("AppConfig() 호출됨");
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        System.out.println("AppConfig.sqlSessionFactory() 호출됨");
        return new SqlSessionFactoryProxy(
                new SqlSessionFactoryBuilder().build(
                        Resources.getResourceAsStream("project/config/mybatis-config.xml")));
    }
}
