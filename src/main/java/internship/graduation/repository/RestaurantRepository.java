package internship.graduation.repository;

import internship.graduation.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.menus m WHERE m.isVisible=true")
    List<Restaurant> getAllWithVisibleMenu();

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.menus m WHERE r.id=:id")
    Restaurant getWithMenu(@Param("id") int id);
}
