package com.raven.Jasonweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

//Entity lets us map our model Class to a database table
//@NoaArgsConstructor and @AllArgsConstructor are part of lombok, they generate for us a constructor with no arguements an one with all of them
//both of these are needeed in order to work with jpa
//@Data just generates getters and setters for our class attributes
@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Usuario {


    //@id is used on top of our property in which we want to use to diffenciete our Users in the database
    //GeneratedValue is for the strategy of how we want to achieve this uniqueness

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;

    //We want to achieve authorization, for that we need the roles of our user ASAP
    //When we load this object, we want to know the roles first
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles= new ArrayList<>();
}
