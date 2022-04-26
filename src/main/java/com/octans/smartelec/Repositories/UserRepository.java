package com.octans.smartelec.Repositories;

import java.util.List;

import com.octans.smartelec.Models.Election;
import com.octans.smartelec.Models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    List<User> findAllByEnrolledElection(Election enrolledElection);

    List<User> findByEnrolledElectionIsNull();

}
