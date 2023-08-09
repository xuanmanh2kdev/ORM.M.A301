package training.dao;

import training.entities.Interview;

import java.util.List;
import java.util.Optional;

public interface InterviewDao {

    void createInterview(Interview interview);
    Optional<Interview> getInterviewById(Integer interviewID);
    List<Interview> getAllInterview();
    Optional<Interview> updateInterviewById(Interview interview);
    Optional<Interview> deleteInterviewById(Integer interviewID);
}
