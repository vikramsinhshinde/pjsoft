package com.example.SuperAdmin.Module;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FeesManagementAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adminname;
    private String emailaddress;
    private Long phonenumber;
    private String password;
    private String confirmpassword;
    private LocalDateTime createdAt;
    private String status;
}
