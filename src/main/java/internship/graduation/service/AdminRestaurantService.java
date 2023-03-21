package internship.graduation.service;

import internship.graduation.model.Restaurant;
import internship.graduation.repository.RestaurantRepository;
import internship.graduation.repository.UserRepository;
import internship.graduation.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminRestaurantService {

    RestaurantRepository restaurantRepository;

    VoteRepository voteRepository;

    UserRepository userRepository;

    @Autowired
    public AdminRestaurantService(RestaurantRepository restaurantRepository, VoteRepository voteRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Integer restaurantId) {
        restaurantRepository.delete(restaurantId);
    }


}
