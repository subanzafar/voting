package com.octans.smartelec.Models;

import java.sql.Date;

import javax.persistence.*;

import lombok.*;
import lombok.EqualsAndHashCode.Include;

@Entity
@Table(name = "finished_election")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FinishedElection {
    @Id
    @Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;
    private Date startDate;
    private Date endDate;
    private String seatName;
    private String description;
    private String ownerName;
    private String winnerName;
    private Integer winnerVotes;
    private String domain;
}
