package com.twinkles.talkamout.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.twinkles.talkamout.enums.Gender;
import com.twinkles.talkamout.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorColumn(name = "user_type")
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends BaseClass{
    private String firstName;
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @Size(min = 8, max = 20)
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne
   @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;
    @OneToOne
    @JoinColumn(name = "nextOfKin_id", referencedColumnName = "id")
    private NextOfKin nextOfKin;

    @Enumerated
    @Column(name = "role", insertable = true, updatable = true)
    private Role role;

    private boolean completedProfile;
    @JsonManagedReference
    @OneToMany(mappedBy = "givenBy")
    private List<Review> reviewList = new ArrayList<>();
}
