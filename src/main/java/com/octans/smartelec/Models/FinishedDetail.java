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
@Table(name = "finished_detail")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FinishedDetail {
    @Id
    @Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;
    private String userId;
    private String name;
    private String imageUrl;
    private Integer votes;
    private Integer finishId;

}
