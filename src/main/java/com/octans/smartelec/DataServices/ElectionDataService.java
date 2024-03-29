package com.octans.smartelec.DataServices;

import java.util.List;
import java.util.Optional;

import com.octans.smartelec.ApiResponse;
import com.octans.smartelec.Models.Election;
import com.octans.smartelec.Models.FinishedDetail;
import com.octans.smartelec.Models.User;
import com.octans.smartelec.Models.VoteDetail;
import com.octans.smartelec.Repositories.ElectionRepository;
import com.octans.smartelec.Repositories.FinishedDetailRepository;
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

    @Autowired
    FinishedDetailRepository fDetailRepository;

    public ApiResponse updateName(String userId, String name) {
        ApiResponse response = new ApiResponse();
        try {
            Optional<User> user = userRepository.findByUserId(userId);
            if (user != null) {
                user.map(u -> {
                    u.setName(name);
                    return userRepository.save(u);
                });
            }
            Optional<FinishedDetail> fDetail = fDetailRepository.findByUserId(userId);
            if (fDetail != null) {
                fDetail.map(u -> {
                    u.setName(name);
                    return fDetailRepository.save(u);
                });
            }
            response.setStatusCode(200);
            response.setMessage("Name updated!");
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ApiResponse updateImage(String userId, String imageUrl) {
        ApiResponse response = new ApiResponse();
        try {
            Optional<User> user = userRepository.findByUserId(userId);
            if (user != null) {
                user.map(u -> {
                    u.setImageUrl(imageUrl);
                    return userRepository.save(u);
                });
            }
            Optional<FinishedDetail> fDetail = fDetailRepository.findByUserId(userId);
            if (fDetail != null) {
                fDetail.map(u -> {
                    u.setImageUrl(imageUrl);
                    return fDetailRepository.save(u);
                });
            }
            response.setStatusCode(200);
            response.setMessage("Image updated!");
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ApiResponse allElection(String domain) {
        ApiResponse response = new ApiResponse();
        try {
            response.setStatusCode(200);
            response.setMessage("Elections Found!");
            response.setData(electionRepo.findAllByEmailDomain(domain));
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ApiResponse allFinishDetail(Integer finishId) {
        ApiResponse response = new ApiResponse();
        try {
            response.setStatusCode(200);
            response.setMessage("Elections Found!");
            response.setData(fDetailRepository.findAllByFinishId(finishId));
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ApiResponse updateElection(Integer id, String name, String des, String endDate) {
        ApiResponse response = new ApiResponse();
        try {
            response.setData(electionRepo.findById(id).map(e -> {
                e.setDescription(des);
                e.setEndDate(endDate);
                e.setSeatName(name);
                return electionRepo.save(e);
            }));
            response.setStatusCode(200);
            response.setMessage("Election updated!");
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ApiResponse allElectionOfOwner(String id, String domain) {
        ApiResponse response = new ApiResponse();
        try {
            List<Election> elections = electionRepo.findAllByOwnerIdAndEmailDomain(id, domain);
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

    public void assignElection(String userId, Integer electionId) {
        userRepository.findByUserId(userId).map(u -> {
            u.setElectionId(electionId);
            return userRepository.save(u);
        });
    }

    public void deleteElection(Integer electionId) {
        electionRepo.deleteById(electionId);
    }

    public void removeElection(String userId, Integer finishId) {
        userRepository.findByUserId(userId).map(u -> {
            FinishedDetail fDetail = new FinishedDetail();
            fDetail.setUserId(u.getUserId());
            fDetail.setFinishId(finishId);
            fDetail.setImageUrl(u.getImageUrl());
            fDetail.setName(u.getName());
            fDetail.setVotes(u.getVotes());
            fDetailRepository.save(fDetail);
            u.setElectionId(0);
            u.setVotes(0);
            return userRepository.save(u);
        });
    }

    public ApiResponse casteVote(String userId) {
        ApiResponse response = new ApiResponse();
        try {
            userRepository.findByUserId(userId).map(u -> {
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

    public ApiResponse saveVoteDetail(String userId, Integer elecId) {
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

    public ApiResponse getVoteDetail(String userId, Integer elecId) {
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
            response.setStatusCode(200);
            response.setMessage("Election Created!");
            response.setData(el.getOid());
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
