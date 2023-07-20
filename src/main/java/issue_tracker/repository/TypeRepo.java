package issue_tracker.repository;

import issue_tracker.domain.Tag;
import issue_tracker.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepo extends JpaRepository<Type, Long> {
    List<Type> findAllByDeletedIsFalse();

    Optional<Type> findAllByIdAndDeletedIsFalse(Long id);

    Optional<Type> findAllByName(String name);
}
