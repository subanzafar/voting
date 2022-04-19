package com.octans.smartelec.DataServices;

import java.util.ArrayList;
import java.util.List;

import com.octans.smartelec.ApiResponse;
import com.octans.smartelec.Models.Election;
import com.octans.smartelec.Models.FinishedElection;
import com.octans.smartelec.Models.User;
import com.octans.smartelec.Models.VoteDetail;
import com.octans.smartelec.Repositories.ElectionRepository;
import com.octans.smartelec.Repositories.FinishedElectionRepository;
import com.octans.smartelec.Repositories.UserRepository;
import com.octans.smartelec.Repositories.VoteDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectionDataService {

    @Autowired
    ElectionRepository electionRepo;

    @Autowired
    FinishedElectionRepository felectionRepo;

    @Autowired
    VoteDetailRepository vRepository;

    @Autowired
    UserRepository userRepository;

    public ApiResponse allElection(String domain) {
        ApiResponse response = new ApiResponse();
        try {
            List<Election> elections = electionRepo.findAllByEmailDomain(domain);
            elections.forEach(elc -> {
                elc.setUsers(null);
                elc.setOwner(null);
            });

            response.setStatusCode(200);
            response.setMessage("Elections Found!");
            response.setData(elections);
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public void assignElection(Integer userId, Integer electionId) {
        Election election = electionRepo.findById(electionId).get();
        userRepository.findById(userId).map(u -> {
            u.setEnrolledElection(election);
            return userRepository.save(u);
        });
    }

    public ApiResponse casteVote(Integer userId) {
        ApiResponse response = new ApiResponse();
        try {
            userRepository.findById(userId).map(u -> {
                u.setVotes(u.getVotes() + 1);
                response.setData(u.getVotes());
                return userRepository.save(u);
            });
            response.setStatusCode(200);
            response.setMessage("Vote casted!");
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ApiResponse saveVoteDetail(Integer userId, Integer elecId) {
        ApiResponse response = new ApiResponse();
        VoteDetail voteDetail = new VoteDetail();
        try {
            voteDetail.setElectionId(elecId);
            voteDetail.setUserId(userId);
            voteDetail = vRepository.save(voteDetail);
            response.setStatusCode(200);
            response.setMessage("Detail saved!");
            response.setData(voteDetail);
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ApiResponse getVoteDetail(Integer userId, Integer elecId) {
        ApiResponse response = new ApiResponse();
        try {
            List<VoteDetail> voteDetail = vRepository.findAllByUserIdAndElectionId(userId, elecId);
            response.setStatusCode(200);
            response.setMessage("Detail got!");
            if (!voteDetail.isEmpty()) {
                response.setData(true);
            } else {
                response.setData(false);
            }
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ApiResponse createElection(Election election) {
        ApiResponse response = new ApiResponse();
        try {

            Election el = electionRepo.save(election);
            if (el != null) {
                System.out.println("savedddddd");
            }
            response.setStatusCode(200);
            response.setMessage("Election Created!");
            response.setData(election.getOid());
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ApiResponse electionById(Integer id) {
        ApiResponse response = new ApiResponse();
        try {
            Election el = electionRepo.findById(id).get();
            response.setStatusCode(200);
            response.setMessage("Election Created!");
            response.setData(el);
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
