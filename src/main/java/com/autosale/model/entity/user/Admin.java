package com.autosale.model.entity.user;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    private String roles;
}
