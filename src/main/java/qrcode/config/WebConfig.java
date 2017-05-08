package qrcode.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
// ComponentScan is must??
// @ComponentScan("qrcode")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public DataSource dataSource() {
	PGSimpleDataSource dataSource = new PGSimpleDataSource();
	dataSource.setDatabaseName("qrcode");
	dataSource.setUser("postgres");
	dataSource.setPassword("postgres");
	dataSource.setServerName("localhost");
	dataSource.setPortNumber(5432);
	return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
	LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
	sfb.setDataSource(dataSource);
	sfb.setPackagesToScan(new String[] { "qrcode.data" });
	Properties props = new Properties();
	props.setProperty("dialect", "org.hibernate.dialect.postgresql");	
	sfb.setHibernateProperties(props);
	return sfb;
	}
}
