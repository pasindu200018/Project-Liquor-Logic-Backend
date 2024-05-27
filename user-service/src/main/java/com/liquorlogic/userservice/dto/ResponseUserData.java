package com.liquorlogic.userservice.dto;

import com.liquorlogic.userservice.enums.Role;
import com.liquorlogic.userservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ResponseUserData {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String heading;
    private String about;
    private String contact;
    private Date create;
    private Date update;
    private Status status;
    private Role type;
    private String username;
    private String password;
}
