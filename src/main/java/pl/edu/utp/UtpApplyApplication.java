package pl.edu.utp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.security.VaadinSessionSecurityContextHolderStrategy;

import java.security.Principal;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
public class UtpApplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtpApplyApplication.class, args);
	}

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		System.out.println(principal);
		return principal;
	}

	@Configuration
	@EnableGlobalMethodSecurity(securedEnabled = true)
	public static class SecurityConfiguration extends GlobalMethodSecurityConfiguration {

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth	.inMemoryAuthentication()
					.withUser("admin").password("p").roles("ADMIN", "USER")
					.and()
					.withUser("user").password("p").roles("USER");
		}

		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return authenticationManager();
		}

		static {
			SecurityContextHolder.setStrategyName(VaadinSessionSecurityContextHolderStrategy.class.getName());
		}

	}

}
