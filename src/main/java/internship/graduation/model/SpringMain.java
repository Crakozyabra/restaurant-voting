package internship.graduation.model;

import internship.graduation.service.AdminRestaurantService;
import internship.graduation.service.UserRestaurantService;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SpringMain {
    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ConfigurableEnvironment env = ctx.getEnvironment();

        ctx.load("spring/spring-db.xml");
        ctx.refresh();
        /*List.of(ctx.getBeanDefinitionNames()).forEach(System.out::println);*/
        UserRestaurantService userRestaurantService = ctx.getBean(UserRestaurantService.class);
        AdminRestaurantService adminRestaurantService = ctx.getBean(AdminRestaurantService.class);
        //restaurantService.getAll().forEach(System.out::println);

        //Restaurant restaurant = Restaurant.builder().id(1).name("Italian restaurant").build();
        //adminRestaurantService.create(restaurant);
        adminRestaurantService.deleteRestaurant(1);
        //restaurantService.create(restaurant);

        //System.out.println(userRestaurantService.get(3));
        //userRestaurantService.getAllRestaurantsWithVisibleMenu().forEach(System.out::println);
        //System.out.println(LocalDate.now().minus(1, ChronoUnit.DAYS));
        //userRestaurantService.getRestaurantVoting(LocalDate.now().minus(1, ChronoUnit.DAYS)).forEach(System.out::println);

        //System.out.println(restaurantService.getVoteCountByRestaurantId(2));

        ctx.close();


    }
}
