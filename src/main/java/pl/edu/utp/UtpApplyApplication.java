package pl.edu.utp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@EnableOAuth2Sso
//@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@RestController
public class UtpApplyApplication extends WebSecurityConfigurerAdapter {

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(UtpApplyApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.antMatcher("/**")
//				.authorizeRequests()
//				.antMatchers("/", "/login**")
//				.permitAll()
//				.anyRequest()
//				.authenticated();

		http.exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
				.and()
				.authorizeRequests()
				.antMatchers("/**").permitAll()
				.and()
				.csrf().disable();

	}
}
