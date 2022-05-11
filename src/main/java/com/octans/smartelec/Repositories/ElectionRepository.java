package com.octans.smartelec.Repositories;

import java.util.List;

import com.octans.smartelec.Models.Election;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Integer> {

    // @Query(value = "insert into user_elec(user_id,election_id) values (?2,?1)",
    // nativeQuery = true)
    // void saveVoteDetail(Integer oid_elec, Integer oid_user);

    List<Election> findAllByEmailDomain(String emailDomain);

    List<Election> findAllByOwnerIdAndEmailDomain(String ownerId, String emailDomain);

}
