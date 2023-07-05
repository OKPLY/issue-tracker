package issue_tracker.service.impl;

import issue_tracker.domain.Issue;
import issue_tracker.dto.issue.CreateIssueDto;
import issue_tracker.dto.issue.UpdateIssueDto;
import issue_tracker.repository.IssueRepo;
import issue_tracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepo issueRepo;

    private final ModelMapper modelMapper;

    @Override
    public List<Issue> findAll() {
        return issueRepo.findAll();
    }

    @Override
    public Issue findById(Long id) {
        var res = issueRepo.findById(id);

        if (res.isPresent())
            return res.get();

        throw new NoSuchElementException("Issue Not Found.");
    }

    @Override
    public Issue create(CreateIssueDto issueDto) {
        Issue issue = modelMapper.map(issueDto, Issue.class);
        return issueRepo.save(issue);
    }

    @Override
    public Issue update(UpdateIssueDto issueDto) {
        Issue issue = findById(issueDto.getId());
        issue.setTags(null);
        issue.setType(null);

        modelMapper.map(issueDto, issue);

        return issueRepo.save(issue);
    }

    @Override
    public Issue delete(Long id) {
        Issue issue = findById(id);
        issue.setDeleted(true);

        return issueRepo.save(issue);
    }
}
