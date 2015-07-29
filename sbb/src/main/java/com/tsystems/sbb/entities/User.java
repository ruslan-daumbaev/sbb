package com.tsystems.sbb.entities;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
public class User extends EntityBase {
    private String firstName;
    private String lastName;
    private String loginName;
    private boolean isLocked;
    private Date lastLogin;
}
