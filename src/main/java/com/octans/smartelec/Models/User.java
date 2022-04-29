package com.octans.smartelec.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode.Include;

@Entity
@Table(name = "usertable")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String oid;
    private String name;
    private String email;
    // private String bio;
    private String emailDomain;
    private String password;
    private String mobile;
    private String imageUrl;
    private Integer votes;
    private Integer electionId;

    // @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // List<Election> myElections = new ArrayList<>();

    // @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // FinishedElection finishedElection;

    // @OneToOne(mappedBy = "winner", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // FinishedElection finishedElection;

}