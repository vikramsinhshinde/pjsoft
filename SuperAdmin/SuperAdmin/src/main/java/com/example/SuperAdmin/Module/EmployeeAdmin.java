package com.example.SuperAdmin.Module;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adminname;

    @Column(unique = true)
    private String emailaddress;
    private Long phonenumber;
    private String password;
    private String confirmpassword;
    private LocalDateTime createdAt;
    private String status;
}