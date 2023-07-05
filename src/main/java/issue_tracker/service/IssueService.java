package issue_tracker.service;

import issue_tracker.domain.Issue;
import issue_tracker.dto.issue.CreateIssueDto;
import issue_tracker.dto.issue.UpdateIssueDto;

import java.util.List;

public interface IssueService {
    List<Issue> findAll();

    Issue findById(Long id);

    List<Issue> findByParentIssueId(Long parentIssueId);

    Issue create(CreateIssueDto issueDto);

    Issue create(Long parentIssueId, CreateIssueDto issueDto);

    Issue update(UpdateIssueDto issueDto);

    Issue delete(Long id);
}