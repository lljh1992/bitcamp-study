package project.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import project.util.Bean;
import project.util.ComponentScan;
import project.util.SqlSessionFactoryProxy;

@ComponentScan(basePackages = {"project.dao", "project.handler"})
public class AppConfig {

  // Mybatis 객체 준비
  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    return new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsStream("project/config/mybatis-config.xml")));
  }
}
