package com.raven.Jasonweb.service;

import com.raven.Jasonweb.model.Role;
import com.raven.Jasonweb.model.Usuario;

import java.util.List;

public interface UserService {

    Usuario saveUsuario(Usuario usuario);
    Role saveRol(Role role);

    void addRoleToUser(String username,String rolename);

    Usuario getUser (String username);

    List<Usuario> getUsers();

}
