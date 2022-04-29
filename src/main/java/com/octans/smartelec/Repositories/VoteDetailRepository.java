package com.octans.smartelec.Repositories;

import java.util.List;

import com.octans.smartelec.Models.VoteDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteDetailRepository extends JpaRepository<VoteDetail, Integer> {

    List<VoteDetail> findAllByUserIdAndElectionId(Integer userId, Integer electionId);

    // @Query(value = "select * from user_elec where user_id = ?2 and election_id =
    // ?1", nativeQuery = true)
    // List<VoteDetail> getVoteDetail(Integer oid_elec, Integer oid_user);
}
