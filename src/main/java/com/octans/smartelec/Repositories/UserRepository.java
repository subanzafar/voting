package com.octans.smartelec.Repositories;

import java.util.List;

import com.octans.smartelec.Models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // User findByEmailAndPassword(String email, String password);

    // User findByEmail(String email);

    List<User> findAllByElectionId(Integer electionId);

    List<User> findByElectionIdAndEmailDomain(Integer electionId, String emailDomain);

}
