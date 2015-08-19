package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.UsersRepository;
import com.tsystems.sbb.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("usersService")
public class UsersServiceImpl implements UserDetailsService {

    private static Logger log = LogManager.getLogger(UsersServiceImpl.class);

    @Autowired
    private UsersRepository usersRepository;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("Retrieve user's details");
        User details = usersRepository.findUserByLoginName(s);
        if (details == null) {
            throw new UsernameNotFoundException(s);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        authorities.add(adminAuthority);

        return new org.springframework.security.core.userdetails.User(details.getLoginName(),
                details.getPassword(), true, true, true, true, authorities);
    }
}
