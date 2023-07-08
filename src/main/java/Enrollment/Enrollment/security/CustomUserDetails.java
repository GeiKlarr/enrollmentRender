package Enrollment.Enrollment.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends User {
    String defaultUrl;
    public CustomUserDetails(User authUser, String defaultUrl) {
        super(authUser.getUsername(), authUser.getPassword(), authUser.getAuthorities());
        this.defaultUrl = defaultUrl;
    }

    public String getDefaultUrl(){
        return defaultUrl;
    }
}
