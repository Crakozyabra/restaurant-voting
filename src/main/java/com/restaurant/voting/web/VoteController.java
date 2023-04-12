package com.restaurant.voting.web;

import com.restaurant.voting.to.vote.VotesDto;
import com.restaurant.voting.service.VoteService;
import com.restaurant.voting.to.vote.VoteDto;
import com.restaurant.voting.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<String> create(@Valid @RequestBody VoteDto voteDto, BindingResult bindingResult) {
        ValidationUtil.checkNew(voteDto);
        ResponseEntity<String> response = ValidationUtil.bindError(bindingResult);
        if (Objects.nonNull(response)) {
            return response;
        }
        VoteDto created = voteService.create(voteDto);
        URI uriOfCreatedResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfCreatedResource).build();
    }

    @GetMapping
    public List<VotesDto> getAllResultsToday() {
        List<VotesDto> resultsToday = voteService.getAllResultsToday(LocalDate.now());
        resultsToday.forEach(System.out::println);
        return resultsToday;
    }

    @PutMapping
    public ResponseEntity<String> update(@Valid @RequestBody VoteDto voteDto, BindingResult bindingResult) {
        Assert.isTrue(!voteDto.isNew(), "id must be not null");
        ResponseEntity<String> response = ValidationUtil.bindError(bindingResult);
        if (Objects.nonNull(response)) {
            return response;
        }
        voteService.update(voteDto);
        return ResponseEntity.ok().build();
    }
}
