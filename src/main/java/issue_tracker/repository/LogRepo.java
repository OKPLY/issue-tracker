package issue_tracker.repository;

import issue_tracker.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepo extends JpaRepository<Log, Long>{

    // List in revers order
    List<Log> findAllByOrderByIdDesc();
}
