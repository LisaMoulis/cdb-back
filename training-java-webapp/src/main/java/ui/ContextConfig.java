package ui;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import dto.*;
import model.*;
import service.UserService;
//import aop.AopConfig;

@Configuration
//@Import({ AopConfig.class })
@ComponentScan(basePackages = { "command","service","persistence","mapper","ui.servlets", "api","training-java-persistence.src/main/java.persistence",
		"training-java-persistence.persistence" ,"training-java.training-java-persistence.persistence","training-java.training-java-persistence.src/main/java.persistence" })
@EnableWebMvc
@EnableWebSecurity
public class ContextConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Bean
	public HikariDataSource dataSource()
	{
		HikariConfig config = new HikariConfig("/datasource.properties");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return new HikariDataSource(config);
	}
	
	@Bean
	public ViewResolver internalResourceViewResolver() {
	    InternalResourceViewResolver bean = new InternalResourceViewResolver();
	    bean.setViewClass(JstlView.class);
	    bean.setPrefix("/WEB-INF/static/views/");
	    bean.setSuffix(".jsp");
	    return bean;
	}
	
	/*@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.US);
	    return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	}*/
	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}	
	
	@Bean
	public ResourceBundleMessageSource messageSource() {

		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	    source.setBasenames("/messages");
	    source.setUseCodeAsDefaultMessage(true);
	       //source.setDefaultEncoding("UTF-8");

	    return source;
	}
	
	@Bean
	public SessionFactory sessionFactory() 
	{	
	    return new org.hibernate.cfg.Configuration().addAnnotatedClass(CompanyDTO.class).addAnnotatedClass(ComputerDTO.class).addAnnotatedClass(UserDTO.class).buildSessionFactory();
	}
	
	@Bean
	public DigestAuthenticationEntryPoint entryPoint() {
	    DigestAuthenticationEntryPoint result = new DigestAuthenticationEntryPoint();
	    result.setRealmName("Please authenticate");
	    result.setKey("a key");
	    return result;
	}

	@Bean
	public DigestAuthenticationFilter digestAuthenticationFilter() throws Exception {
	    DigestAuthenticationFilter result = new DigestAuthenticationFilter();
	    if (userService.loadUserByUsername("user") == null)
	    {
	    	userService.registerUser(new User("user",encoder().encode("digestsecret")));
	    }
	    result.setUserDetailsService(userService);
	    result.setAuthenticationEntryPoint(entryPoint());
	    //result.setPasswordAlreadyEncoded(true);
	    return result;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {

	    http
	      .csrf().disable()
	      .authorizeRequests()
	      .antMatchers("/edit*").hasAnyAuthority("USER","ADMIN")
	      .antMatchers("/add*").hasAnyAuthority("USER","ADMIN")
	      .antMatchers("/login*").permitAll()
	      .antMatchers("/computers*").permitAll()
	      .antMatchers("/static/**").permitAll() 
	      .antMatchers("/service/**").permitAll()
	      .anyRequest().authenticated()
	      .and()
	      .formLogin()
	      //.loginPage("/login")
	      .defaultSuccessUrl("/computers")
	      .and()
	      .logout()
	      .invalidateHttpSession(true)
	      .clearAuthentication(true)
	      .logoutSuccessUrl("/logout_process")
	      .deleteCookies("JSESSIONID")
	      .and().addFilter(digestAuthenticationFilter()).exceptionHandling()
			.authenticationEntryPoint(entryPoint())
	      //.logoutSuccessHandler(logoutSuccessHandler());
	      ;
	}
	
}
//Inversion de dépendance :  la classe est la mère, le nom est la classe fille
