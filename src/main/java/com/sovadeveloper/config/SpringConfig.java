package com.sovadeveloper.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.sovadeveloper")
@PropertySource("classpath:application.properties")
public class SpringConfig {
    @Value("${spring.datasource.driverClassName}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.jpa.database-platform}")
    private String dialect;

    @Value("${spring.jpa.show-sql}")
    private String flagShowSql;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String flagDdl;

    @Value("${hibernate.format_sql}")
    private String frmtSql;

//    @Bean("petTypeDaoImpl")
//    public PetTypeDaoImpl petTypeDaoImpl(){
//        return new PetTypeDaoImpl(hibernateTemplate());
//    }
//
//    @Bean("petTypeService")
//    public PetTypeService petTypeService(){
//        return new PetTypeService(petTypeDaoImpl());
//    }

    //DB CONFIG

    @Bean("dataSource")
    public BasicDataSource dataSource(){
        BasicDataSource bs = new BasicDataSource();
        bs.setDriverClassName(driver);
        bs.setUrl(url);
        bs.setUsername(user);
        bs.setPassword(password);
        bs.setInitialSize(1);
        bs.setMaxTotal(2);
        bs.setMinIdle(1);
        bs.setMaxIdle(1);
        return bs;
    }

    @Bean("sessionFactory")
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource());
        sf.setPackagesToScan("java");
        sf.setHibernateProperties(props());
        return sf;
    }

    public Properties props(){
        Properties p = new Properties();
        p.put("hibernate.dialect", dialect);
        p.put("hibernate.show_sql", flagShowSql);
        p.put("hibernate.format_sql", frmtSql);
        p.put("hibernate.hbm2ddl.auto", flagDdl);
        return p;
    }

    @Bean("hibernateTemplate")
    public HibernateTemplate hibernateTemplate(){
        HibernateTemplate ht = new HibernateTemplate();
        ht.setSessionFactory(sessionFactory().getObject());
        return ht;
    }

    @Bean("transactionManager")
    public HibernateTransactionManager transactionManager()
    {
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(sessionFactory().getObject());
        return htm;
    }
}
