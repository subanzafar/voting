package com.octans.smartelec.DataServices;

import java.util.ArrayList;
import java.util.List;

import com.octans.smartelec.ApiResponse;
import com.octans.smartelec.Models.FinishedElection;
import com.octans.smartelec.Repositories.FinishedElectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinishedElectionDataService {

    @Autowired
    FinishedElectionRepository repo;

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
}
