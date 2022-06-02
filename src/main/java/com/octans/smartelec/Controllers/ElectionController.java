package com.octans.smartelec.Controllers;

import com.octans.smartelec.ApiResponse;
import com.octans.smartelec.DataServices.ElectionDataService;
import com.octans.smartelec.DataServices.FinishedElectionDataService;
import org.springframework.http.MediaType;
import com.octans.smartelec.Models.Election;
import com.octans.smartelec.Models.FinishedElection;
import com.octans.smartelec.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(path = "ownerelections/{domain}/{id}")
    public ApiResponse ownerElections(@PathVariable String domain, @PathVariable String id) {
        return electionDataService.allElectionOfOwner(id, domain);
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

    @GetMapping(path = "newElection/{seat}/{des}/{sDate}/{eDate}/{domain}/{ownerid}")
    public ApiResponse crearteElection(@PathVariable String seat, @PathVariable String des, @PathVariable String sDate,
            @PathVariable String eDate, @PathVariable String domain, @PathVariable String ownerid) {
        Election el = new Election();
        el.setSeatName(seat);
        el.setDescription(des);
        el.setEmailDomain(domain);
        el.setEndDate(eDate);
        el.setOwnerId(ownerid);
        el.setStartDate(sDate);
        return electionDataService.createElection(el);
    }

    @GetMapping(path = "editElection/{seat}/{des}/{eDate}/{id}")
    public ApiResponse updateteElection(@PathVariable String seat, @PathVariable String des,
            @PathVariable String eDate, @PathVariable Integer id) {
        return electionDataService.updateElection(id, seat, des, eDate);
    }

    @PostMapping(path = "newFinishElection", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse crearteFinishedElection(@RequestBody FinishedElection election) {
        // Election el = election;
        // el.getUsers().clear();
        // election.getUsers().forEach(u -> {
        // User user = userRepository.findById(u.getOid()).get();
        // el.addUser(user);
        // });
        return finishedElectionDataService.saveNew(election);
    }

    @GetMapping(path = "assignElection/{userId}/{elecId}")
    public void assignElection(@PathVariable String userId, @PathVariable Integer elecId) {
        electionDataService.assignElection(userId, elecId);
    }

    @GetMapping(path = "deleteElection/{elecId}")
    public void deleteElection(@PathVariable Integer elecId) {
        electionDataService.deleteElection(elecId);
    }

    @GetMapping(path = "removeElection/{userId}")
    public void removeElection(@PathVariable String userId) {
        electionDataService.removeElection(userId);
    }

    @GetMapping(path = "votedetail/{userId}/{elecId}")
    public ApiResponse saveVoteDetail(@PathVariable String userId, @PathVariable Integer elecId) {
        return electionDataService.saveVoteDetail(userId, elecId);
    }

    @GetMapping(path = "getdetail/{userId}/{elecId}")
    public ApiResponse getVoteDetail(@PathVariable String userId, @PathVariable Integer elecId) {
        return electionDataService.getVoteDetail(userId, elecId);
    }

    @GetMapping(path = "castvote/{userId}")
    public ApiResponse casteVote(@PathVariable String userId) {
        return electionDataService.casteVote(userId);
    }
}
