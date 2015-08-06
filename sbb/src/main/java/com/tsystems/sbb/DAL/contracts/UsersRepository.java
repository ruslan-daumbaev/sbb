package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.User;

import java.util.List;

/**
 * Created by rdaumbae on 29.07.2015.
 */
public interface UsersRepository  {

    List<User> getAllUsers();

    User findUserByName(String userName);
}
