import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import training.dao.CandidateDao;
import training.dao.EntryTestDao;
import training.dao.InterviewDao;
import training.dao.impl.CandidateDaoImpl;
import training.dao.impl.EntryTestDaoImpl;
import training.dao.impl.InterviewDaoImpl;
import training.entities.Candidate;
import training.entities.EntryTest;
import training.entities.Interview;
import training.enums.ResultType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class TestAll {

    static CandidateDao candidateDao;
    static EntryTestDao entryTestDao;
    static InterviewDao interviewDao;

    @BeforeAll
    static void beforeAll() {
        candidateDao = new CandidateDaoImpl();
        entryTestDao = new EntryTestDaoImpl();
        interviewDao = new InterviewDaoImpl();
    }

    @Test
    void create_DB_Test() {
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

        Set<Interview> interviews = new HashSet<>();
        Interview interview;
        for(int i = 0; i < 3; i++){
           interview = new Interview();
           interview.setTime("10:00:00");
           interview.setDate(LocalDate.parse("2007-12-03"));
           interview.setInterviewer("Interviewer");
           interview.setComments("Comments");
           interview.setInterviewResult("A");
           interview.setRemark("Remark");
           interviews.add(interview);
        }

        candidate.setInterviews(interviews);

        Set<EntryTest> entryTests = new HashSet<>();
        EntryTest entryTest = new EntryTest();
        for(int i = 0; i < 4; i++){
            entryTest.setTime("2020-01-01");
            entryTest.setDate(LocalDate.parse("2020-01-01"));
            entryTest.setLanguageValuator("Language Valuator");
            entryTest.setTechnicalResult(8.25);
            entryTest.setResult(ResultType.PASS);
            entryTest.setRemark("Remark");
            entryTests.add(entryTest);
        }

        candidate.setEntryTests(entryTests);

        candidateDao.createCandidate(candidate);
    }

    @Test
    void get_Candidate_By_Id_Test() {
        System.out.println(candidateDao.getCandidateById(1));
    }

    @Test
    void get_All_Candidate_Test() {
        System.out.println(candidateDao.getAllCandidate());
    }

    @Test
    void update_Candidate_By_Id_Test(){
        Candidate candidate = new Candidate();
        candidate.setCandidateId(1);
        candidate.setFullName("Nguyen Van B");
        candidate.setDateOfBirth(LocalDate.parse("1999-01-01"));
        candidate.setGender(0);
        candidate.setGraduationYear(LocalDate.parse("2020-01-01"));
        candidate.setPhone("0123456789");
        candidate.setEmail("emailabc@gmail.com");
        candidate.setSkill("C++");
        candidate.setForeignLanguage("English");
        candidate.setLevel(3);
        candidate.setCv("CV");
        candidate.setAllocationStatus(0);
        candidate.setRemark("RemarkA");

        candidateDao.updateCandidateById(candidate);
    }

    @Test
    void delete_Candidate_By_Id_Test(){
        candidateDao.deleteCandidateById(1);
    }

    @Test
    void get_EntryTest_By_Id_Test() {
        System.out.println(entryTestDao.getEntryTestById(1));
    }

    @Test
    void get_All_EntryTest_Test() {
        System.out.println(entryTestDao.getAllEntryTest());
    }

    @Test
    void update_EntryTest_By_Id_Test(){
        EntryTest entryTest = new EntryTest();
        entryTest.setTestId(1);
        entryTest.setTime("2020-01-01");
        entryTest.setDate(LocalDate.parse("2020-01-01"));
        entryTest.setLanguageValuator("Language Valuator 2");
        entryTest.setTechnicalResult(8.25);
        entryTest.setResult(ResultType.PASS);
        entryTest.setRemark("Remark 2");

        entryTestDao.updateEntryTestById(entryTest);
    }

    @Test
    void delete_EntryTest_By_Id_Test(){
        entryTestDao.deleteEntryTestById(1);
    }

    @Test
    void get_Interview_By_Id_Test() {
        System.out.println(interviewDao.getInterviewById(1));
    }

    @Test
    void get_All_Interview_Test() {
        System.out.println(interviewDao.getAllInterview());
    }

    @Test
    void update_Interview_By_Id_Test(){
        Interview interview = new Interview();
        interview.setInterviewId(1);
        interview.setTime("2020-01-01");
        interview.setDate(LocalDate.parse("2020-01-01"));
        interview.setInterviewer("Interviewer 2");
        interview.setComments("Comments 2");
        interview.setInterviewResult("A");
        interview.setRemark("Remark 2");

        interviewDao.updateInterviewById(interview);
    }

    @Test
    void delete_Interview_By_Id_Test(){
        interviewDao.deleteInterviewById(1);
    }
}
