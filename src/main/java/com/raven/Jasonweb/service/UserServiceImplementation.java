package com.raven.Jasonweb.service;

import com.raven.Jasonweb.model.Role;
import com.raven.Jasonweb.model.Usuario;
import com.raven.Jasonweb.repository.RoleRepo;
import com.raven.Jasonweb.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service@RequiredArgsConstructor @Transactional@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {

    //both of these interfaces are communiqating with jpa
    //Jpa creates object with those interfaces for us to use
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        log.info("Guardando Usuario en la base de datos");
        return userRepo.save(usuario);
    }

    @Override
    public Role saveRol(Role role) {
        log.info("Adding role {}",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {

        log.info("Adding rol {} to user{}",rolename,username);

        Usuario usuario= userRepo.findByUsername(username);
        Role role = roleRepo.findByName(rolename);

        //We add a role to the user
        usuario.getRoles().add(role);
    }


    @Override
    public Usuario getUser(String username) {

        log.info("Getting User {}",username);

        return userRepo.findByUsername(username);
    }

    @Override
    public List<Usuario> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }



    //this is the method Spring uses to load from the database
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user= userRepo.findByUsername(username);
        if (user.equals(null)){
            log.error("Crap.. its bugged. I mean, USER NOT FOUND IN DATABASE SIR !");
                throw new UsernameNotFoundException("Crap.. its bugged. I mean, USER NOT FOUND IN DATABASE SIR !");
        }else {
            log.info("Usuario encontrado en la base de datos :{}",username);
        }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();

        //Basically, we get an array of all our roles
        user.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });


        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
