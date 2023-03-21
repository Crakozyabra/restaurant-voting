package internship.graduation.dto;

import internship.graduation.model.Menu;
import internship.graduation.model.Restaurant;
import internship.graduation.model.Vote;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DtoUtil {

    private static MenuDto menuToMenuDto(Menu menu) {
        return MenuDto.builder().name(menu.getName()).price(menu.getPrice()).build();
    }

    public static List<MenuDto> menusToMenusDto(List<Menu> menus) {
        List<MenuDto> menusDto = new ArrayList<>();
        for (Menu menu : menus) {
            menusDto.add(menuToMenuDto(menu));
        }
        return menusDto;
    }

    public static RestaurantDto restaurantToRestaurantDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .name(restaurant.getName())
                .menus(DtoUtil.menusToMenusDto(restaurant.getMenus()))
                .build();
    }

    public static List<VoteDto> voteToVoteDto(List<Vote> votes) {
        Map<String, Integer> voting = new LinkedHashMap<>();
        for (Vote vote : votes) {
            voting.merge(vote.getRestaurant().getName(), 1, (oldValue, newValue) -> oldValue + 1);
        }
        List<VoteDto> votesDto = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : voting.entrySet()) {
            votesDto.add(VoteDto.builder().restaurantName(entry.getKey()).countOfvoice(entry.getValue()).build());
        }
        return votesDto;
    }

}
