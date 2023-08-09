import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import training.dao.CandidateDao;
import training.dao.impl.CandidateDaoImpl;
import training.entities.Candidate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CandidateDaoTest {

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

        candidateDao.createCandidate(candidate);
    }

    @Test
    void create_Candidate_Fail_Test() {
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
//        candidate.setAllocationStatus(0);

        candidateDao.createCandidate(candidate);
    }

    @Test
    void get_Candidate_By_Id_Success_Test() {
        assertNotNull(candidateDao.getCandidateById(1));
    }

    @Test
    void get_Candidate_By_Id_Fail_Test() {
        assertNull(candidateDao.getCandidateById(1));
    }

    @Test
    void get_All_Candidate_Success_Test() {
        assertNotNull(candidateDao.getAllCandidate());
    }

    @Test
    void get_All_Candidate_Fail_Test() {
        assertNull(candidateDao.getAllCandidate());
    }

    @Test
    void update_Candidate_By_Id_Success_Test(){
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

        assertNotNull(candidateDao.updateCandidateById(candidate));
    }

    @Test
    void update_Candidate_By_Id_Fail_Test(){
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
//        candidate.setRemark("RemarkA");

        assertNull(candidateDao.updateCandidateById(candidate));
    }

    @Test
    void delete_Candidate_By_Id_Success_Test(){
        assertNotNull(candidateDao.deleteCandidateById(1));
    }

    @Test
    void delete_Candidate_By_Id_Fail_Test(){
        assertNull(candidateDao.deleteCandidateById(100));
    }

}
