package io.zandona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import io.zandona.security.CustomUserInfoTokenService;

@SpringBootApplication
@EnableResourceServer
public class CustomAuthorizationServerSecureServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomAuthorizationServerSecureServiceApplication.class, args);
	}
	

	@Autowired
	private ResourceServerProperties sso;
	
	@Bean
	public ResourceServerTokenServices resourceServerTokenServices() {
		return new CustomUserInfoTokenService(sso.getUserInfoUri(), sso.getClientId());
	}
}
