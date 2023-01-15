package com.sda.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    private Integer age;
    private String email;

}
