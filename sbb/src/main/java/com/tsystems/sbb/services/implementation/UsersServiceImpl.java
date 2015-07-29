package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.UsersRepository;
import com.tsystems.sbb.entities.User;
import com.tsystems.sbb.services.contracts.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by rdaumbae on 29.07.2015.
 */
@Transactional
@Service("usersService")
public class UsersServiceImpl implements UsersService {

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
}
