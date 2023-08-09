package training.dao;

import training.entities.Candidate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CandidateDao {

    void createCandidate(Candidate candidate);
    Optional<Candidate> getCandidateById(Integer candidateID);
    List<Candidate> getAllCandidate();
    Optional<Candidate> updateCandidateById(Candidate candidate);
    Optional<Candidate> deleteCandidateById(Integer candidateID);
    List<Candidate> findAllBySkillAndSkillLevel(String skill, Integer level);
    List<Candidate> findAllByForeignLanguageAndSkill(String foreignLanguage, String skill);
    List<Candidate> findAllBySkillAndDateTestAndPass(String skill, LocalDate date);
    List<Candidate> findAllByDateInterviewAndPass(LocalDate date);
    void updateRemarkNotPhoneEmailAndCV();
    List<Candidate> pagingForCandidate(Integer pageNumber, Integer pageSize);
}
