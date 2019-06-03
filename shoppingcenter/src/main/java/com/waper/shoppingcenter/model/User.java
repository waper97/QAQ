package com.waper.shoppingcenter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "SHUSER")
@Data
public class User {
    @Id
    private String id;

    private String name;

    private String username;

    private String password;

    private Integer role;

    private Integer status;

    private Date birthDate;

    private String mobile;

    private String address;


}
