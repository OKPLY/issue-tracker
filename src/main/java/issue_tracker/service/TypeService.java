package issue_tracker.service;

import issue_tracker.domain.Type;
import issue_tracker.dto.aggregation.TypeCountAggregation;
import issue_tracker.dto.type.CreateTypeDto;
import issue_tracker.dto.type.UpdateTypeDto;

import java.util.List;

public interface TypeService {
    List<Type> findAll();

    Type findById(Long id);

    Type create(CreateTypeDto typeDto);

    Type update(UpdateTypeDto typeDto);

    Type delete(Long id);

    List<TypeCountAggregation> topTypes(Integer limit);
}
