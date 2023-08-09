import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import training.dao.CandidateDao;
import training.dao.impl.CandidateDaoImpl;
import training.entities.Candidate;
import training.entities.EntryTest;
import training.entities.Interview;
import training.enums.ResultType;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CreateAllTest {

    static CandidateDao candidateDao;

    @BeforeAll
    static void beforeAll() {
        candidateDao = new CandidateDaoImpl();
    }

    @Test
    void create_Candidate_Success_Test() {
        Candidate candidate = new Candidate();
        candidate.setCv("CV");
        candidate.setLevel(3);
        candidate.setPhone("0123456789");
        candidate.setSkill("Java");
        candidate.setEmail("email1@gmail.com");
        candidate.setForeignLanguage("English");
        candidate.setDateOfBirth(LocalDate.parse("1999-01-01"));
        candidate.setGender(0);
        candidate.setGraduationYear(LocalDate.parse("2022-01-01"));
        candidate.setRemark("Remark");
        candidate.setFullName("Nguyen Van A");
        candidate.setAllocationStatus(0);

        Set<EntryTest> entryTests = new HashSet<>();
        for(int i = 0; i < 10; i++) {
            EntryTest entryTest = new EntryTest();
            entryTest.setTime("2020-01-01");
            entryTest.setDate(LocalDate.parse("2020-01-01"));
            entryTest.setLanguageValuator("Language Valuator");
            entryTest.setLanguageResult(8.25);
            entryTest.setTechnicalValuator("Technical Valuator 1");
            entryTest.setTechnicalResult(8.25);
            entryTest.setResult(ResultType.PASS);
            entryTest.setRemark("Remark");
            entryTest.setEntryTestSkill("Java");
            entryTest.setCandidate(candidate);
            entryTests.add(entryTest);
        }

        candidate.setEntryTests(entryTests);


        Set<Interview> interviews = new HashSet<>();
        for(int i = 0; i < 5; i++) {
            Interview interview = new Interview();
            interview.setTime("2020-01-01");
            interview.setDate(LocalDate.parse("2020-01-01"));
            interview.setInterviewer("Interviewer 2");
            interview.setComments("Comments 2");
            interview.setInterviewResult(ResultType.PASS);
            interview.setRemark("Remark 2");
            interview.setCandidate(candidate);
            interviews.add(interview);
        }

        candidate.setInterviews(interviews);

        candidateDao.createCandidate(candidate);
    }
}
