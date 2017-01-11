package pl.edu.utp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
//@EnableOAuth2Sso
@EnableOAuth2Client
//@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@RestController
@EnableAuthorizationServer
@Order(6)
public class UtpApplyApplication {

//	@RequestMapping("/user")
//	public Principal user(Principal principal) {
//		return principal;
//	}

	@RequestMapping({ "/user", "/me" })
	public Map<String, String> user(Principal principal) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		return map;
	}


	@RequestMapping("/userHello")
	public String home(Principal user) {
		return "Hello " + user.getName();
	}

	public static void main(String[] args) {
		SpringApplication.run(UtpApplyApplication.class, args);
	}

}
