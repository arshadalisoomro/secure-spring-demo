package pk.edu.suk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pk.edu.suk.service.util.SecurityService;

/**
 * Created by Arshay on 10/27/2018.
 */

@Service
public class SecurityServiceImpl implements SecurityService {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean login(String userName, String userPassword) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, userPassword, userDetails.getAuthorities());

        authenticationManager.authenticate(token);

        boolean result = token.isAuthenticated();
        if (result){
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        return result;
    }
}
