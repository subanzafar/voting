package com.octans.smartelec.Controllers;

import java.sql.Date;
import java.util.List;

import com.octans.smartelec.ApiResponse;
import com.octans.smartelec.DataServices.ElectionDataService;
import com.octans.smartelec.DataServices.FinishedElectionDataService;
import org.springframework.http.MediaType;
import com.octans.smartelec.Models.Election;
import com.octans.smartelec.Models.User;
import com.octans.smartelec.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController
@RequestMapping(path = "/election/")
public class ElectionController {

    @Autowired
    ElectionDataService electionDataService;

    @Autowired
    FinishedElectionDataService finishedElectionDataService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "felections/{domain}")
    public ApiResponse allFinishedElections(@PathVariable String domain) {
        return finishedElectionDataService.findAll(domain);
    }

    @GetMapping(path = "elections/{domain}")
    public ApiResponse allElections(@PathVariable String domain) {
        return electionDataService.allElection(domain);
    }

    @GetMapping(path = "electionbyid/{id}")
    public ApiResponse electionById(@PathVariable Integer id) {
        return electionDataService.electionById(id);
    }

    // @PostMapping(path =
    // "newElection/{startDate}/{endDate}/{domain}/{seatName}/{description}/{ownerId}/{usersIds}")
    // public ApiResponse crearteElection(@PathVariable String startDate,
    // @PathVariable String endDate,
    // @PathVariable String domain, @PathVariable String seatName, @PathVariable
    // String description,
    // @PathVariable Integer ownerId, @PathVariable String usersIds) {

    @PostMapping(path = "newElection", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE })
    public ApiResponse crearteElection(@RequestBody Election election) {
        // Election el = election;
        // el.getUsers().clear();
        // election.getUsers().forEach(u -> {
        // User user = userRepository.findById(u.getOid()).get();
        // el.addUser(user);
        // });
        return electionDataService.createElection(election);
    }

    @GetMapping(path = "assignElection/{userId}/{elecId}")
    public void assignElection(@PathVariable Integer userId, @PathVariable Integer elecId) {
        electionDataService.assignElection(userId, elecId);
    }

    @GetMapping(path = "votedetail/{userId}/{elecId}")
    public ApiResponse saveVoteDetail(@PathVariable Integer userId, @PathVariable Integer elecId) {
        return electionDataService.saveVoteDetail(userId, elecId);
    }

    @GetMapping(path = "getdetail/{userId}/{elecId}")
    public ApiResponse getVoteDetail(@PathVariable Integer userId, @PathVariable Integer elecId) {
        return electionDataService.getVoteDetail(userId, elecId);
    }

    @GetMapping(path = "castvote/{userId}")
    public ApiResponse casteVote(@PathVariable Integer userId) {
        return electionDataService.casteVote(userId);
    }
}
