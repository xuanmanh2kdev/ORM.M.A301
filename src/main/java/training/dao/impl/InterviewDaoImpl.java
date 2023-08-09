package training.dao.impl;

import org.hibernate.Session;
import training.dao.InterviewDao;
import training.entities.Interview;
import utils.XmlConnectionConfig;

import java.util.List;
import java.util.Optional;

public class InterviewDaoImpl implements InterviewDao {

    @Override
    public void createInterview(Interview interview) {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            session.persist(interview);
            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<Interview> getInterviewById(Integer interviewID) {
        try (Session session = XmlConnectionConfig.getSession()) {
            Interview interview = session.find(Interview.class, interviewID);
            return Optional.ofNullable(interview);
        }
    }

    @Override
    public List<Interview> getAllInterview() {
        try (Session session = XmlConnectionConfig.getSession()) {
            return session.createQuery("SELECT i FROM Interview i", Interview.class).getResultList();
        }
    }

    @Override
    public Optional<Interview> updateInterviewById(Interview interview) {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            Interview interview2 = session.find(Interview.class, interview.getInterviewId());
            if (interview2 == null) {
                return null;
            }
            interview2.setInterviewer(interview.getInterviewer());
            interview2.setInterviewResult(interview.getInterviewResult());
            interview2.setDate(interview.getDate());
            interview2.setTime(interview.getTime());
            interview2.setComments(interview.getComments());
            interview2.setRemark(interview.getRemark());
            session.merge(interview2);
            session.getTransaction().commit();

            return Optional.of(interview);
        }
    }

    @Override
    public Optional<Interview> deleteInterviewById(Integer interviewID){
        try(Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            Interview interview = session.find(Interview.class, interviewID);
            if (interview == null) {
                return null;
            }
            session.delete(interview);
            session.getTransaction().commit();

            return Optional.of(interview);
        }
    }
}
