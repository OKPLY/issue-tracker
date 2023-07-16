package issue_tracker.service.impl;

import issue_tracker.domain.Type;
import issue_tracker.dto.aggregation.TypeCountAggregation;
import issue_tracker.dto.type.CreateTypeDto;
import issue_tracker.dto.type.UpdateTypeDto;
import issue_tracker.repository.IssueRepo;
import issue_tracker.repository.TypeRepo;
import issue_tracker.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final TypeRepo typeRepo;
    private final IssueRepo issueRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<Type> findAll(){
        return typeRepo.findAll();
    }

    @Override
    public Type findById(Long id){
        var res = typeRepo.findById(id);

        if(res.isPresent())
            return res.get();

        throw new NoSuchElementException("Type Not Found.");
    }

    @Override
    public Type create(CreateTypeDto typeDto){
        Type type = modelMapper.map(typeDto, Type.class);

        return typeRepo.save(type);
    }

    @Override
    public Type update(UpdateTypeDto typeDto){
        var type = findById(typeDto.getId());
        modelMapper.map(typeDto, type);

        return typeRepo.save(type);
    }

    @Override
    public Type delete(Long id){
        var type = findById(id);
        type.setDeleted(true);

        return typeRepo.save(type);
    }

    @Override
    public List<TypeCountAggregation> typeCountAggregation(){
       return issueRepo.getTypeCountAggregation();
    }
}
