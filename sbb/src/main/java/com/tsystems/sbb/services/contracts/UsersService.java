package com.tsystems.sbb.services.contracts;

import com.tsystems.sbb.entities.User;

/**
 * Created by rdaumbae on 29.07.2015.
 */
public interface UsersService {
    User findUserByName(String userName);
}
