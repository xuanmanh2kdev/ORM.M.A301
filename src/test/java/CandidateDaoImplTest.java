import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import training.dao.CandidateDao;
import training.dao.impl.CandidateDaoImpl;
import training.entities.Candidate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CandidateDaoImplTest {

    static CandidateDao candidateDao;

    @BeforeAll
    static void beforeAll() {
        candidateDao = new CandidateDaoImpl();
    }

    @Test
    void get_Candidate_By_Id_Success_Test() {
        assertNotNull(candidateDao.getCandidateById(1));
    }

    @Test
    void get_Candidate_By_Id_Fail_Test() {
        assertNull(candidateDao.getCandidateById(100));
    }

    @Test
    void get_All_Candidate_Success_Test() {
        assertTrue(candidateDao.getAllCandidate().size() > 0);
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
        candidate.setCandidateId(100);
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

    @Test
    void find_All_By_Skill_And_SkillLevel_Success_Test(){
        assertTrue(candidateDao.findAllBySkillAndSkillLevel("Java", 3).size() > 0);
    }

    @Test
    void find_All_By_Skill_And_SkillLevel_Fail_Test(){
        assertTrue(candidateDao.findAllBySkillAndSkillLevel("Java Java", 4).size() == 0);
    }

    @Test
    void find_All_By_Foreign_Language_And_Skill_Success_Test(){
        assertTrue(candidateDao.findAllByForeignLanguageAndSkill("Japanese", "Python/ML").size() > 0);
    }

    @Test
    void find_All_By_Foreign_Language_And_Skill_Fail_Test(){
        assertTrue(candidateDao.findAllByForeignLanguageAndSkill("English english", "Java").size() == 0);
    }

    @Test
    void find_All_By_Skill_And_DateTest_And_Pass_Success_Test(){
        assertTrue(candidateDao.findAllBySkillAndDateTestAndPass("Java", LocalDate.parse("2020-10-01")).size() > 0);
    }
    @Test
    void find_All_By_Date_Interview_And_Pass(){
        assertTrue(candidateDao.findAllByDateInterviewAndPass(LocalDate.parse("2020-10-01")).size() > 0);
    }

    @Test
    void find_All_By_Skill_And_DateTest_And_Pass_Fail_Test(){
        assertTrue(candidateDao.findAllBySkillAndDateTestAndPass("Java java", LocalDate.parse("2021-01-01")).size() > 0);
    }

    @Test
    void update_Remark_Not_Phone_Email_And_CV(){
        candidateDao.updateRemarkNotPhoneEmailAndCV();
    }

    @Test
    void paging_For_Candidate(){
        assertTrue(candidateDao.pagingForCandidate(1, 10).size() > 0);
    }

}
