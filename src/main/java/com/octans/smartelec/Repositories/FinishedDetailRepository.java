package com.octans.smartelec.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.octans.smartelec.Models.FinishedDetail;

@Repository
public interface FinishedDetailRepository extends JpaRepository<FinishedDetail, Integer> {
    List<FinishedDetail> findAllByFinishId(Integer finishId);
}
