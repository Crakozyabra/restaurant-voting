package com.restaurant.voting.service;

import com.restaurant.voting.model.Vote;
import com.restaurant.voting.util.DtoUtil;
import com.restaurant.voting.dto.vote.VoteDto;
import com.restaurant.voting.dto.vote.VotesDto;
import com.restaurant.voting.model.Restaurant;
import com.restaurant.voting.model.User;
import com.restaurant.voting.repository.RestaurantRepository;
import com.restaurant.voting.repository.UserRepository;
import com.restaurant.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class VoteService {

    private final static LocalTime TIME_LIMIT_FOR_VOTING = LocalTime.of(11, 0, 0);

    private VoteRepository voteRepository;

    private RestaurantRepository restaurantRepository;

    private UserRepository userRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public VoteDto create(VoteDto voteDto) {
        Restaurant restaurant = restaurantRepository.getReferenceById(voteDto.getRestaurantId());
        User user = userRepository.getReferenceById(voteDto.getUserId());
        Vote vote = voteRepository.save(new Vote(null, restaurant, user, null, null));
        voteDto.setId(vote.getId());
        return voteDto;
    }

    public List<VotesDto> getAllResultsToday(LocalDate votingDay) {
        List<Vote> votes = voteRepository.findVoteByVotingDateIs(votingDay);
        return DtoUtil.votesToVotesDto(votes);
    }

    @Transactional
    public VoteDto update(VoteDto voteDto) {
        Vote vote = voteRepository.findVoteByUser_IdAndVotingDateIs(voteDto.getUserId(), LocalDate.now());
        Restaurant restaurant = restaurantRepository.getReferenceById(voteDto.getRestaurantId());
        if (vote.getVotingTime().isBefore(TIME_LIMIT_FOR_VOTING)) {
            vote.setRestaurant(restaurant);
            voteRepository.save(vote);
            return voteDto;
        }
        return new VoteDto();
    }
}
