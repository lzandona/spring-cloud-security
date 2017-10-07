package io.zandona;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@SpringBootApplication
public class CustomAuthorizationServerSecureServiceClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CustomAuthorizationServerSecureServiceClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting client app..");
		
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
		resourceDetails.setAccessTokenUri("http://localhost:9000/oauth/token");
		resourceDetails.setScope(Arrays.asList("service_read"));
		resourceDetails.setClientId("myorg");
		resourceDetails.setClientSecret("myorg_secret");
		
		resourceDetails.setUsername("Jane_Doe");
		resourceDetails.setPassword("secret");
		
		System.out.println("Retrieving valid token...");
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
		String token = restTemplate.getAccessToken().toString();
		
		System.out.println("Received Token: " + token);
		
		
		System.out.println("Calling Secure Hello Service...");
		String response = restTemplate.getForObject("http://localhost:9001/hello", String.class);
		
		System.out.println(response);
	}
}
