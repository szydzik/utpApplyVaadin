package pl.edu.utp.security;

import com.vaadin.server.VaadinSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.context.SecurityContextImpl;
import pl.edu.utp.model.security.AnonymousUser;

import java.util.List;

/**
 * A custom {@link SecurityContextHolderStrategy} that stores the {@link SecurityContext} in thes
 * Vaadin Session.
 */
public class VaadinSessionSecurityContextHolderStrategy implements SecurityContextHolderStrategy {

	private final static String ANONYMOUS_USERNAME = "anonymousUser";
	private final static String ANONYMOUS_TOKEN_KEY = "anonymousUser";
	private final static List<GrantedAuthority> ANONYMOUS_ROLES = AuthorityUtils.createAuthorityList( "ROLE_ANONYMOUS");

	@Override
	public void clearContext() {
		getSession().setAttribute(SecurityContext.class, null);
	}



	@Override
	public SecurityContext getContext() {
		VaadinSession session = getSession();
		SecurityContext context = session.getAttribute(SecurityContext.class);
		if (context == null) {
			context = createEmptyContext();//new SecurityContextImpl();
			SecurityContextHolder.setContext(context);
			session.setAttribute(SecurityContext.class, context);
		}
		return context;
	}

	@Override
	public void setContext(SecurityContext context) {
		getSession().setAttribute(SecurityContext.class, context);
	}

	@Override
	public SecurityContext createEmptyContext() {
		SecurityContext context = new SecurityContextImpl();
		AnonymousUser anonymousUser = new AnonymousUser();
		Authentication authentication = new AnonymousAuthenticationToken(ANONYMOUS_TOKEN_KEY, anonymousUser , ANONYMOUS_ROLES);
		authentication.setAuthenticated(false);
		context.setAuthentication(authentication);
		return context;
	}
	
	private static VaadinSession getSession() {
		VaadinSession session = VaadinSession.getCurrent();
		if (session == null) {
			throw new IllegalStateException("No VaadinSession bound to current thread");
		}
		return session;
	}


}
