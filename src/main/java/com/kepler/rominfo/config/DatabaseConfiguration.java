package com.kepler.rominfo.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@MapperScan("com.kepler.rominfo.dao")
public class DatabaseConfiguration {

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/databaseTest");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        return dataSource;
    }

    /*@Bean(name = "dataSource")
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }*/

    @Bean
    public DataSourceTransactionManager transactionManager() throws URISyntaxException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("com.kepler.rominfo.domain");
        sessionFactory.setMapperLocations(new Resource[]{
                  new ClassPathResource("com/kepler/rominfo/dao/AuthorizationDao.xml"),
                new ClassPathResource("com/kepler/rominfo/dao/CategoryDao.xml"),
                new ClassPathResource("com/kepler/rominfo/dao/CourseDao.xml"),
                new ClassPathResource("com/kepler/rominfo/dao/EnrollmentDao.xml"),
                new ClassPathResource("com/kepler/rominfo/dao/LectureDao.xml"),
                new ClassPathResource("com/kepler/rominfo/dao/ResourceDao.xml"),
                new ClassPathResource("com/kepler/rominfo/dao/RoleDao.xml"),
                new ClassPathResource("com/kepler/rominfo/dao/UserDao.xml")
        });
        return sessionFactory.getObject();
    }
}