package com.octans.smartelec.Models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode.Include;

@Entity
@Table(name = "election")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Election {
    @Id
    @Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;
    private String seatName;
    private String startDate;
    private String endDate;
    private String emailDomain;
    private String description;

    @OneToMany(mappedBy = "enrolledElection")
    List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "oid_owner", referencedColumnName = "oid")
    User owner;

    public User addUser(User user) {
        user.setEnrolledElection(this);
        getUsers().add(user);
        return user;
    }

    public User removeUser(User user) {
        user.setEnrolledElection(null);
        getUsers().remove(user);
        return user;
    }

}
