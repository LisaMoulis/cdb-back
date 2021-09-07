package ui;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

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
import org.springframework.util.DigestUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import aop.AopConfig;
import dto.*;
import model.*;
import service.UserService;
//import aop.AopConfig;

@Configuration
@Import({ AopConfig.class })
@ComponentScan(basePackages = { "command","service","persistence","mapper","ui.servlets", "api","training-java-persistence.src/main/java.persistence",
		"training-java-persistence.persistence" ,"training-java.training-java-persistence.persistence","training-java.training-java-persistence.src/main/java.persistence" })
@EnableWebMvc
@EnableWebSecurity
public class ContextConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
	
	@Autowired
	private UserService userService;
	
	@Bean
	public ViewResolver internalResourceViewResolver() {
	    InternalResourceViewResolver bean = new InternalResourceViewResolver();
	    bean.setViewClass(JstlView.class);
	    bean.setPrefix("/WEB-INF/static/views/");
	    bean.setSuffix(".jsp");
	    return bean;
	}
	
	@Bean
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
	}
	
	
	@Bean
	public PasswordEncoder encoder() {
		return  NoOpPasswordEncoder.getInstance();
	}
	
	/*@Bean
	public ResourceBundleMessageSource messageSource() {

		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	    source.setBasenames("/messages");
	    //source.setUseCodeAsDefaultMessage(true);
	       //source.setDefaultEncoding("UTF-8");
	    return source;
	}*/
	
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
	    	userService.registerUser(new User("user",new String(DigestUtils.md5DigestAsHex("user:Please authenticate:digestsecret".getBytes()))));
	    }
	    if (userService.loadUserByUsername("admin") == null)
	    {
	    	userService.registerUser(new User("admin",new String(DigestUtils.md5DigestAsHex("admin:Please authenticate:adminsecret".getBytes()))));
	    }
	    result.setUserDetailsService(userService);
	    result.setAuthenticationEntryPoint(entryPoint());
	    result.setPasswordAlreadyEncoded(true);
	    return result;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {

	    http
	      .csrf().disable().cors().and()
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
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("**").allowedOrigins("*");
			}
		};
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Collections.singletonList("*"));
	    configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
	    configuration.setExposedHeaders(Arrays.asList("Authorization", "content-type"));
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
	
}
//Inversion de dépendance :  la classe est la mère, le nom est la classe fille
