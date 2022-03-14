package com.sovadeveloper.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.sovadeveloper.repositories")
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan("com.sovadeveloper")
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
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

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    //THYMELEAF

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver() {
        SpringResourceTemplateResolver templateResolver
                = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setContentType("text/html");
        viewResolver.setForceContentType(true);
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/js/**")
                .addResourceLocations("/WEB-INF/static/js/").setCachePeriod(31556926);
    }

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
        sf.setPackagesToScan("com.sovadeveloper");
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

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.sovadeveloper");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
}
