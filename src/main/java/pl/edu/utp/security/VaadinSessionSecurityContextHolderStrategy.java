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

import java.security.Principal;
import java.util.List;

/**
 * A custom {@link SecurityContextHolderStrategy} that stores the {@link SecurityContext} in thes
 * Vaadin Session.
 */
public class VaadinSessionSecurityContextHolderStrategy implements SecurityContextHolderStrategy {

	@Override
	public void clearContext() {
		getSession().setAttribute(SecurityContext.class, null);
	}

	private final static List<GrantedAuthority> ANONYMOUS_ROLE = AuthorityUtils.createAuthorityList( "ROLE_ANONYMOUS");

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
		Principal anonymousUser = () -> "anonymousUser";
		//Authentication authentication = new TestingAuthenticationToken("ANONYMOUS", "ANONYMOUS", "ROLE_ANONYMOUS");
		Authentication authentication = new AnonymousAuthenticationToken("ROLE_ANONYMOUS", anonymousUser , ANONYMOUS_ROLE); //<-- sprawdzić jak to działą !
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
