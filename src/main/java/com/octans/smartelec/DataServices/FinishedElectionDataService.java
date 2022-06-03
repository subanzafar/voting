package com.octans.smartelec.DataServices;

import com.octans.smartelec.ApiResponse;
import com.octans.smartelec.Models.FinishedElection;
import com.octans.smartelec.Repositories.FinishedDetailRepository;
import com.octans.smartelec.Repositories.FinishedElectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinishedElectionDataService {

    @Autowired
    FinishedElectionRepository repo;

    @Autowired
    FinishedDetailRepository frepo;

    public ApiResponse findAll(String domain) {
        ApiResponse response = new ApiResponse();
        try {
            response.setStatusCode(200);
            response.setMessage("Elections Found!");
            response.setData(repo.findAllByDomain(domain));
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured!");
            return response;
        }
    }

    public ApiResponse saveNew(FinishedElection fElection) {
        ApiResponse response = new ApiResponse();
        try {
            FinishedElection e = repo.save(fElection);
            response.setStatusCode(200);
            response.setMessage("Elections saved!");
            response.setData(e.getOid());
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    // public ApiResponse saveFinishDetail(FinishedDetail fElection) {
    // ApiResponse response = new ApiResponse();
    // try {
    // FinishedDetail e = frepo.save(fElection);
    // response.setStatusCode(200);
    // response.setMessage("Elections saved!");
    // response.setData(e);
    // return response;
    // } catch (Exception e) {
    // response.setStatusCode(500);
    // response.setMessage(e.getMessage());
    // return response;
    // }
    // }
}
