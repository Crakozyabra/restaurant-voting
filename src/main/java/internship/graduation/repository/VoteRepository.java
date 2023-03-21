package internship.graduation.repository;

import internship.graduation.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @EntityGraph(value = "voteWithJoinFetchRestaurant")
    List<Vote> findVoteByVotingDateIs(LocalDate votingDate);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:id")
    Vote findVoteByUserId(@Param("id") Integer id);
}
