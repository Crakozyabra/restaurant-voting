package com.restaurant.voting.controller;

import com.restaurant.voting.dto.vote.VotesDto;
import com.restaurant.voting.service.VoteService;
import com.restaurant.voting.dto.vote.VoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    public static final String REST_URL = "/user/votes";

    private VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<VoteDto> create(@RequestBody VoteDto voteDto) {
        Assert.isNull(voteDto.getId(),"must be null");
        VoteDto created = voteService.create(voteDto);
        URI uriOfCreatedResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfCreatedResource).body(created);
    }

    @GetMapping
    public List<VotesDto> getAllResultsToday() {
        List<VotesDto> resultsToday = voteService.getAllResultsToday(LocalDate.now());
        resultsToday.forEach(System.out::println);
        return resultsToday;
    }

    @PutMapping
    public VoteDto update(@RequestBody VoteDto voteDto) {
        Assert.notNull(voteDto.getId(),"must be null");
        return voteService.update(voteDto);
    }
}
