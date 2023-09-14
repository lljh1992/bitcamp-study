package project.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import project.util.SqlSessionFactoryProxy;

import javax.sql.DataSource;

@ComponentScan(basePackages = {
        "project.dao",
        "project.controller",
        "project.service"})
@PropertySource({"classpath:project/config/ncloud/jdbc.properties"})
@MapperScan("project.dao")
public class AppConfig {

    public AppConfig() {
        System.out.println("AppConfig() 호출됨");
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx) throws Exception {
        System.out.println("AppConfig.sqlSessionFactory() 호출됨");

        org.apache.ibatis.logging.LogFactory.useLog4J2Logging();

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("project.vo");
        factoryBean.setMapperLocations(appCtx.getResources("classpath:project/dao/mysql/*Dao.xml"));

        return factoryBean.getObject();
    }

    @Bean
    public DataSource dataSource(
            @Value("${jdbc.driver}") String driver,
            @Value("${jdbc.url}") String url,
            @Value("${jdbc.username}") String username,
    )
}
