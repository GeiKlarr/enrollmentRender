package Enrollment.Enrollment.security;

import Enrollment.Enrollment.models.Role;
import Enrollment.Enrollment.models.UserEntity;
import Enrollment.Enrollment.repository.RoleRepository;
import Enrollment.Enrollment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    private RoleRepository roleRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findFirstByUsername(username);
        if (user != null){
            List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map((role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))).collect(Collectors.toList());
            User authUser = new User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map((role)-> new SimpleGrantedAuthority("ROLE_" +role.getName()))
                            .collect(Collectors.toList())
            );

            String defaultUrl;

            if(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
                defaultUrl = "/admin/dashboard";
            } else if(authorities.stream().anyMatch(a-> a.getAuthority().equals("ROLE_USER"))){
                defaultUrl = "//springapplication-render.onrender.com/login";
            }else {
                defaultUrl = "/login";
            }

            return new CustomUserDetails(authUser, defaultUrl);
        }else {
            throw new UsernameNotFoundException("invalid user name or password");
        }



    }



}
