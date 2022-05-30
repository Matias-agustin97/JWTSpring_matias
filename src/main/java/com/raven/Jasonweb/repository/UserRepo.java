package com.raven.Jasonweb.repository;

import com.raven.Jasonweb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Usuario,Long> {

    Usuario findByUsername(String username);
}
