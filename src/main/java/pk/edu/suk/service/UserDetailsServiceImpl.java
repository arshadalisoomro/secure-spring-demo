package pk.edu.suk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pk.edu.suk.model.User;

/**
 * Created by Arshay on 10/27/2018.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByUserEmail(s);
        if (user == null){
            throw new UsernameNotFoundException("User with " + s + " not found!");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUserEmail(), user.getUserPassword(), user.getRoles());
    }
}
