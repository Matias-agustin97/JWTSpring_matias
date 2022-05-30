package com.raven.Jasonweb.controller;

import com.raven.Jasonweb.model.Role;
import com.raven.Jasonweb.model.Usuario;
import com.raven.Jasonweb.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    //

    private final UserService userService;

    //Response entity lets us responde the request

    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<Usuario>saveUser(@RequestBody Usuario user){

        //201 created()
        //200 ok()

        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveUsuario(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveUser(@RequestBody Role role){

        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveRol(role));
    }


    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){

        //Just for simplicity, we added an inner class to better help us mapp the jason object the request gets
        //then we call the addRoleToUser method, and pass the values


        userService.addRoleToUser(form.getUsername(),form.getRolename());
        return ResponseEntity.ok().build();
    }




}

@Data
class RoleToUserForm{
    private String username;
    private String rolename;
}