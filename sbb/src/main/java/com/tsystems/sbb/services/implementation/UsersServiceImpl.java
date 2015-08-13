package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.UsersRepository;
import com.tsystems.sbb.entities.User;
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

    @Autowired
    private UsersRepository usersRepository;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User details = usersRepository.findUserByLoginName(s);
        if(details == null){
            throw new UsernameNotFoundException(s);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        authorities.add(adminAuthority);

        return new org.springframework.security.core.userdetails.User(details.getLoginName(),
                details.getPassword(), true, true, true, true, authorities);
    }
}
