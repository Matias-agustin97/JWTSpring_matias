package com.raven.Jasonweb.repository;

import com.raven.Jasonweb.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {

    //We have name attribute in our Role Class, so we name this method findsbyName()
    Role findByName(String name);
}
