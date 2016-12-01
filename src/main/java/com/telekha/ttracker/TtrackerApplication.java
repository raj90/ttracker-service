package com.telekha.ttracker;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class TtrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TtrackerApplication.class, args);
	}
}

@Configuration
@EnableResourceServer
class ResourceServerConfiguration extends
ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		// @formatter:off
		resources
		.resourceId("ttracker-service");
		// @formatter:on
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
		//.httpBasic().disable()
        //.anonymous().disable()
		.authorizeRequests()
		.antMatchers("/swagger-ui.html").permitAll()
		.antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
		.antMatchers("/swagger-resources/**").permitAll()
		.antMatchers("/v2/**").permitAll()
		.anyRequest().authenticated();
		// @formatter:on
	}

}

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration extends
AuthorizationServerConfigurerAdapter {

	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {
		// @formatter:off
		endpoints
		.tokenStore(tokenStore)
		.authenticationManager(this.authenticationManager)
		.userDetailsService(userDetailsService);
		// @formatter:on
	}


	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// @formatter:off
		clients
		.inMemory()
		.withClient("ttrackerclient")
		.authorizedGrantTypes("password")
		.authorities("USER")
		.scopes("read", "write")
		.resourceIds("ttracker-service")
		.secret("123456")
		.accessTokenValiditySeconds(604800);
		// @formatter:on
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore);
		return tokenServices;
	}

}

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}

@Configuration
class TokenStoreConfiguration {
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	@Bean public RedisTokenStore getRedisTokenStore() {
		RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
		return redisTokenStore;
		
	}
}

@Configuration
class RedisConfiguration {
	@Value("${ttracker.redis.host}") private String redisHost; 
	@Value("${ttracker.redis.port}") private String redisPort;
	@Value("${ttracker.redis.password}") private String redisPassword;
	
	@Bean public RedisConnectionFactory redisConnectionFactory() {
		  JedisConnectionFactory cf = new JedisConnectionFactory();
		  cf.setHostName( redisHost );
		  cf.setPort( Integer.parseInt(redisPort) );
		  cf.setPassword(redisPassword);
		  cf.afterPropertiesSet();
		  return cf;
		}
}

@Configuration
class PasswordEncoderConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

@Configuration
@EnableSwagger2
class SwaggerConfig {                                    
    @Bean
    public Docket api() { 

    	
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.ant("/api/**"))
          .build()
          .securitySchemes(Arrays.asList(new ApiKey("Authorization", "Authorization", "header")))
          .securityContexts(Arrays.asList((securityContext())));                                           
    }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.any())
            .build();
      }

      List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
            = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
      }
   
}

