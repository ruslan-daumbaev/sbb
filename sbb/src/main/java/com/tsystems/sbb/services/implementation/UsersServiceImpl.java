package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.UsersRepository;
import com.tsystems.sbb.entities.User;
import com.tsystems.sbb.services.contracts.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by rdaumbae on 29.07.2015.
 */

@Service("usersService")
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private UsersRepository usersRepository;

    public User findUserByName(String userName) {
        return getUsersRepository().findUserByName(userName);
    }

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User details = usersRepository.findUserByName(s);
        if(details == null){
            throw new UsernameNotFoundException(s);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(
                "ROLE_ADMIN");
        authorities.add(adminAuthority);

        UserDetails user = new org.springframework.security.core.userdetails.User(details.getLoginName(),
                details.getPassword(), true, true, true, true, authorities);
        return user;
    }
}
