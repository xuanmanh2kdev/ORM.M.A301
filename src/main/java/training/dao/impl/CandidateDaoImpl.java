package training.dao.impl;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import training.dao.CandidateDao;
import training.entities.Candidate;
import training.enums.ResultType;
import utils.XmlConnectionConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CandidateDaoImpl implements CandidateDao {
    @Override
    public void createCandidate(Candidate candidate) {
        try(Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            session.persist(candidate);
            session.getTransaction().commit();
        }
    }
    @Override
    public Optional<Candidate> getCandidateById(Integer candidateID) {
        try(Session session = XmlConnectionConfig.getSession()) {
            Candidate candidate = session.find(Candidate.class, candidateID);
            return Optional.ofNullable(candidate);
        }
    }
    @Override
    public List<Candidate> getAllCandidate() {
        try(Session session = XmlConnectionConfig.getSession()) {
            return session.createQuery("SELECT c FROM Candidate c", Candidate.class).getResultList();
        }
    }
    @Override
    public Optional<Candidate> updateCandidateById(Candidate candidate) {
        try(Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            Candidate candidate2 = session.find(Candidate.class, candidate.getCandidateId());
            if (candidate2 == null) {
                return null;
            }
            candidate2.setGraduationYear(candidate.getGraduationYear());
            candidate2.setSkill(candidate.getSkill());
            candidate2.setCv(candidate.getCv());
            candidate2.setRemark(candidate.getRemark());
            candidate2.setEmail(candidate.getEmail());
            candidate2.setGender(candidate.getGender());
            candidate2.setLevel(candidate.getLevel());
            candidate2.setPhone(candidate.getPhone());
            candidate2.setFullName(candidate.getFullName());
            candidate2.setAllocationStatus(candidate.getAllocationStatus());
            candidate2.setDateOfBirth(candidate.getDateOfBirth());
            candidate2.setForeignLanguage(candidate.getForeignLanguage());
            session.merge(candidate2);
            session.getTransaction().commit();

            return Optional.of(candidate);
        }
    }
    @Override
    public Optional<Candidate> deleteCandidateById(Integer candidateID) {
        try(Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            Candidate candidate = session.find(Candidate.class, candidateID);
            if (candidate == null) {
                return null;
            }
            session.remove(candidate);
            session.getTransaction().commit();

            return Optional.of(candidate);
        }
    }
    @Override
    public List<Candidate> findAllBySkillAndSkillLevel(String skill, Integer level){
        try(Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            TypedQuery<Candidate> query = session.createQuery("FROM Candidate c WHERE c.skill = :skill AND c.level = :level", Candidate.class);
            query.setParameter("skill", skill);
            query.setParameter("level", level);

            return query.getResultList();
        }
    }
    @Override
    public List<Candidate> findAllByForeignLanguageAndSkill(String foreignLanguage, String skill){
        try(Session session = XmlConnectionConfig.getSession()) {
            TypedQuery<Candidate> query = session.createQuery("FROM Candidate c WHERE c.foreignLanguage = :foreignLanguage AND c.skill = :skill", Candidate.class);
            query.setParameter("foreignLanguage", foreignLanguage);
            query.setParameter("skill", skill);

            return query.getResultList();
        }
    }
    @Override
    public List<Candidate> findAllBySkillAndDateTestAndPass(String skill, LocalDate date){
        try(Session session = XmlConnectionConfig.getSession()) {
            TypedQuery<Candidate> query = session.createQuery("SELECT c FROM Candidate c JOIN FETCH c.entryTests e WHERE c.skill = :skill AND e.result = :result AND e.date = :date", Candidate.class);
            query.setParameter("skill", skill);
            query.setParameter("result", ResultType.PASS);
            query.setParameter("date", date);

            return query.getResultList();
        }
    }
    @Override
    public List<Candidate> findAllByDateInterviewAndPass(LocalDate date){
        try(Session session = XmlConnectionConfig.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Candidate> criteria = builder.createQuery(Candidate.class);
            Root<Candidate> root = criteria.from(Candidate.class);
            criteria.select(root);

            Predicate predicateDate = builder.equal(root.get("date"), date);
            Predicate predicateResult = builder.equal(root.get("interview_result"), ResultType.PASS);
            Predicate fullPredicate = builder.and(predicateDate, predicateResult);
            criteria.where(fullPredicate);

            return session.createQuery(criteria).getResultList();
        }
    }
    @Override
    public void updateRemarkNotPhoneEmailAndCV(){
        try(Session session = XmlConnectionConfig.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Candidate> criteria = builder.createQuery(Candidate.class);
            Root<Candidate> root = criteria.from(Candidate.class);
            criteria.select(root);

            Predicate predicatePhone = builder.isNull(root.get("phone"));
            Predicate predicateEmail = builder.isNull(root.get("email"));
            Predicate predicateCV = builder.isNull(root.get("cv"));
            Predicate fullPredicate = builder.or(predicatePhone, predicateEmail, predicateCV);
            criteria.where(fullPredicate);

            List<Candidate> candidates = session.createQuery(criteria).getResultList();
            for (Candidate candidate : candidates) {
                candidate.setRemark("inactive");
                session.merge(candidate);
            }
            session.getTransaction().commit();
        }
    }
    @Override
    public List<Candidate> pagingForCandidate(Integer pageNumber, Integer pageSize){
        try(Session session = XmlConnectionConfig.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Candidate> criteria = builder.createQuery(Candidate.class);
            Root<Candidate> root = criteria.from(Candidate.class);
            criteria.select(root);

            Query query = session.createQuery(criteria);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.getResultList();
        }
    }
}
