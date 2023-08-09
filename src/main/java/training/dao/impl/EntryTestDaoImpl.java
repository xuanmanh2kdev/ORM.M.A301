package training.dao.impl;

import org.hibernate.Session;
import training.dao.EntryTestDao;
import training.entities.EntryTest;
import utils.XmlConnectionConfig;

import java.util.List;
import java.util.Optional;

public class EntryTestDaoImpl implements EntryTestDao {

    @Override
    public void createEntryTest(EntryTest entryTest) {
        try(Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            session.persist(entryTest);
            session.getTransaction().commit();
        }

    }
    @Override
    public Optional<EntryTest> getEntryTestById(Integer entryTestID) {
        try(Session session = XmlConnectionConfig.getSession()) {
            EntryTest entryTest = session.find(EntryTest.class, entryTestID);
            return Optional.ofNullable(entryTest);
        }
    }
    @Override
    public List<EntryTest> getAllEntryTest() {
        try(Session session = XmlConnectionConfig.getSession()) {
            return session.createQuery("SELECT e FROM EntryTest e", EntryTest.class).getResultList();
        }
    }
    @Override
    public Optional<EntryTest> updateEntryTestById(EntryTest entryTest) {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            EntryTest entryTest2 = session.find(EntryTest.class, entryTest.getTestId());
            if (entryTest2 == null) {
                return null;
            }
            entryTest2.setEntryTestSkill(entryTest.getEntryTestSkill());
            entryTest2.setCandidate(entryTest.getCandidate());
            entryTest2.setDate(entryTest.getDate());
            entryTest2.setRemark(entryTest.getRemark());
            entryTest2.setResult(entryTest.getResult());
            entryTest2.setTime(entryTest.getTime());
            entryTest2.setLanguageResult(entryTest.getLanguageResult());
            entryTest2.setTechnicalResult(entryTest.getTechnicalResult());
            entryTest2.setLanguageValuator(entryTest.getLanguageValuator());
            entryTest2.setTechnicalValuator(entryTest.getTechnicalValuator());
            session.merge(entryTest2);
            session.getTransaction().commit();
            return Optional.of(entryTest);
        }
    }
    @Override
    public Optional<EntryTest> deleteEntryTestById(Integer entryTestID) {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            EntryTest entryTest = session.find(EntryTest.class, entryTestID);
            if (entryTest == null) {
                return null;
            }
            session.delete(entryTest);
            session.getTransaction().commit();
            return Optional.of(entryTest);
        }
    }


}
