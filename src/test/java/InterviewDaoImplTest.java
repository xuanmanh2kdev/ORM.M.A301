import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import training.dao.InterviewDao;
import training.dao.impl.InterviewDaoImpl;
import training.entities.Interview;
import training.enums.ResultType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class InterviewDaoImplTest {

    static InterviewDao interviewDao;

    @BeforeAll
    static void beforeAll() {
        interviewDao = new InterviewDaoImpl();
    }

    @Test
    void get_Interview_By_Id_Success_Test() {
        assertNotNull(interviewDao.getInterviewById(1));
    }

    @Test
    void get_Interview_By_Id_Fail_Test() {
        assertNull(interviewDao.getInterviewById(100));
    }

    @Test
    void get_All_Interview_Success_Test() {
        assertTrue(interviewDao.getAllInterview().size() > 0);
    }

    @Test
    void get_All_Interview_Fail_Test() {
        assertTrue(interviewDao.getAllInterview().size() == 0);
    }

    @Test
    void update_Interview_By_Id_Success_Test(){
        Interview interview = new Interview();
        interview.setInterviewId(1);
        interview.setTime("2020-01-01");
        interview.setDate(LocalDate.parse("2020-01-01"));
        interview.setInterviewer("Interviewer 2");
        interview.setComments("Comments 2");
        interview.setInterviewResult(ResultType.PASS);
        interview.setRemark("Remark 2");

        assertNotNull(interviewDao.updateInterviewById(interview));
    }

    @Test
    void update_Interview_By_Id_Fail_Test(){
        Interview interview = new Interview();
        interview.setInterviewId(100);
        interview.setTime("2020-01-01");
        interview.setDate(LocalDate.parse("2020-01-01"));
        interview.setInterviewer("Interviewer 2");
        interview.setComments("Comments 2");
        interview.setInterviewResult(ResultType.PASS);
        interview.setRemark("Remark 2");

        assertNull(interviewDao.updateInterviewById(interview));
    }

    @Test
    void delete_Interview_By_Id_Success_Test(){
        assertNotNull(interviewDao.deleteInterviewById(1));
    }

    @Test
    void delete_Interview_By_Id_Fail_Test(){
        assertNull(interviewDao.deleteInterviewById(100));
    }
}
