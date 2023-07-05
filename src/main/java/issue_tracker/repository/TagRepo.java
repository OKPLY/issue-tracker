package issue_tracker.repository;

import issue_tracker.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {

    List<Tag> findAllByDeletedIs(boolean deleted);
}
