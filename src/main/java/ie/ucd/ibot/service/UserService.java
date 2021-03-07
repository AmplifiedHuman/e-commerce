package ie.ucd.ibot.service;

import ie.ucd.ibot.configuration.SecurityConfig;
import ie.ucd.ibot.entity.Cart;
import ie.ucd.ibot.entity.CartItem;
import ie.ucd.ibot.entity.User;
import ie.ucd.ibot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final SecurityConfig securityConfig;

    @Autowired
    public UserService(UserRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} does not exist.", email));
        }
    }

    public boolean isEmailInUse(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public void loadUserCart(User user, Cart sessionCart) {
        // new user replaces their cart with session cart to save previous progress
        if (user.getCart() == null) {
            Cart userCart = new Cart();
            userCart.setCartItems(new ArrayList<>());
            for (CartItem item : sessionCart.getCartItems()) {
                userCart.addItem(item);
            }
            sessionCart.setCartItems(new ArrayList<>());
            user.setCart(userCart);
            userRepository.save(user);
        }
    }

    @Transactional
    public void signUp(User user) {
        final String encryptedPassword = securityConfig.getPasswordEncoder().encode(user.getTextPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }
}
