package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rdaumbae on 29.07.2015.
 */
public interface UsersRepository extends CrudRepository<User, Integer> {

    User findUserByLoginName(String loginName);
}
