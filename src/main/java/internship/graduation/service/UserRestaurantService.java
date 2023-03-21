package internship.graduation.service;

import internship.graduation.dto.DtoUtil;
import internship.graduation.dto.RestaurantDto;
import internship.graduation.dto.VoteDto;
import internship.graduation.model.Restaurant;
import internship.graduation.model.Vote;
import internship.graduation.repository.RestaurantRepository;
import internship.graduation.repository.UserRepository;
import internship.graduation.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class UserRestaurantService {

    RestaurantRepository restaurantRepository;

    VoteRepository voteRepository;

    UserRepository userRepository;

    @Autowired
    public UserRestaurantService(RestaurantRepository restaurantRepository, VoteRepository voteRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    public RestaurantDto getRestaurantWithMenu(int restaurantId) {
        Restaurant restaurant = restaurantRepository.getWithMenu(restaurantId);
        return DtoUtil.restaurantToRestaurantDto(restaurant);
    }

    public List<RestaurantDto> getRestaurantsWithVisibleMenu() {
        List<Restaurant> restaurants = restaurantRepository.getAllWithVisibleMenu();
        List<RestaurantDto> restaurantsDto = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantsDto.add(DtoUtil.restaurantToRestaurantDto(restaurant));
        }
        return restaurantsDto;
    }

    public List<VoteDto> getVotes(LocalDate votingDay) {
        Assert.notNull(votingDay, "voting day must not be null");
        List<Vote> votes = voteRepository.findVoteByVotingDateIs(votingDay);
        return DtoUtil.voteToVoteDto(votes);
    }

    public Vote createVote(Vote vote, int userId) {
       /* Vote vote = voteRepository.findVoteByUserId(userId);
        if (Objects.nonNull(vote)) {
            vote.
        }*/


        return null;
    }







   /* public Integer getVoteCountByRestaurantIdAndVotingTimeAfter(Integer restaurantId) {
        return voteRepository.countAllByRestaurantIdAndVotingDateAfter(restaurantId, LocalDate.now());
    }



    public Vote findVoteByRestaurantIdAndUserIdAndVotingDateIsAfter(Integer restaurantId, Integer userId, LocalDateTime votingAfter) {
        return voteRepository.findVoteByRestaurantIdAndUserIdAndVotingDateIsAfter(restaurantId, userId, votingAfter);
    }


    public Vote createVote(Vote vote, Integer restaurantId, Integer userId) {
        Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
        User user = userRepository.getReferenceById(userId);
        return voteRepository.save(Vote.builder().restaurant(restaurant).user(user).build());
    }*/

    /*public static void main(String[] args) {
        System.out.println(ZoneId.systemDefault());
        ZoneId.getAvailableZoneIds().forEach(System.out::println);
        System.out.println(ZonedDateTime.now(ZoneId.of("UTC")));
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(ZonedDateTime.of(localDateTime.toLocalDate(), localDateTime.toLocalTime(), ZoneId.of("UTC")));
    }*/

}
