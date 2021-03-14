package ie.ucd.ibot.entity;

import com.sun.istack.NotNull;
import ie.ucd.ibot.validation.UniqueEmail;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty(message = "First name cannot be empty.")
    private String firstName;

    @NotNull
    @NotEmpty(message = "Last name cannot be empty.")
    private String lastName;

    @NotNull
    @Email(message = "Invalid Email.")
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid Email.")
    @UniqueEmail
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Transient
    @Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters.")
    private String textPassword;

    @NotNull
    @NotEmpty(message = "Address cannot be empty.")
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Message> messages;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.ROLE_USER;

    @Builder.Default
    private Boolean locked = false;

    @Builder.Default
    private Boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public void setAddress(String address) {
        this.address = address.trim();
    }

    public void setTextPassword(String textPassword) {
        this.textPassword = textPassword;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setCart(Cart cart) {
        cart.setUser(this);
        this.cart = cart;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
