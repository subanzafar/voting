package com.octans.smartelec.Repositories;

import java.util.List;
import java.util.Optional;

import com.octans.smartelec.Models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // User findByEmailAndPassword(String email, String password);

    // User findByEmail(String email);

    Optional<User> findByUserId(String userId);

    List<User> findAllByElectionId(Integer electionId);

    List<User> findByElectionIdAndEmailDomain(Integer electionId, String emailDomain);

}
