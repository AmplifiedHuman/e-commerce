package ie.ucd.ibot.configuration;

import ie.ucd.ibot.entity.Cart;
import ie.ucd.ibot.entity.User;
import ie.ucd.ibot.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SessionScope
@Service
@NoArgsConstructor
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private Cart sessionCart;
    private UserService userService;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    public AuthSuccessHandler(Cart cart, UserService userService) {
        this.sessionCart = cart;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        userService.loadUserCart(user, sessionCart);
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
    }
}
