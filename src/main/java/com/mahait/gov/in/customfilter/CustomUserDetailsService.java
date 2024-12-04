package com.mahait.gov.in.customfilter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService  {

    UserDetails loadUserByUsernameAndDomain(String username, int appCode) throws UsernameNotFoundException;
    
    UserDetails loadUserByUsername(String username);

}
