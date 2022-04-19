package com.octans.smartelec.Repositories;

import java.util.List;

import com.octans.smartelec.Models.FinishedElection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinishedElectionRepository extends JpaRepository<FinishedElection, Integer> {

    List<FinishedElection> findAllByDomain(String domain);
}
