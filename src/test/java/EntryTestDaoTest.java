import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import training.dao.EntryTestDao;
import training.dao.impl.EntryTestDaoImpl;
import training.entities.EntryTest;
import training.enums.ResultType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EntryTestDaoTest {

    static EntryTestDao entryTestDao;

    @BeforeAll
    static void beforeAll() {
        entryTestDao = new EntryTestDaoImpl();
    }

    @Test
    void create_EntryTest_Dao_Success_Test() {
        EntryTest entryTest = new EntryTest();
        entryTest.setTime("2020-01-01");
        entryTest.setDate(LocalDate.parse("2020-01-01"));
        entryTest.setLanguageValuator("Language Valuator");
        entryTest.setTechnicalResult(8.25);
        entryTest.setResult(ResultType.PASS);
        entryTest.setRemark("Remark");

        entryTestDao.createEntryTest(entryTest);
    }

    @Test
    void create_EntryTest_Dao_Fail_Test() {
        EntryTest entryTest = new EntryTest();
        entryTest.setTime("2020-01-01");
        entryTest.setDate(LocalDate.parse("2020-01-01"));
        entryTest.setLanguageValuator("Language Valuator");
        entryTest.setTechnicalResult(8.25);
        entryTest.setResult(ResultType.PASS);
//        entryTest.setRemark("Remark");

        entryTestDao.createEntryTest(entryTest);
    }

    @Test
    void get_EntryTest_By_Id_Success_Test() {
        assertNotNull(entryTestDao.getEntryTestById(1));
    }

    @Test
    void get_EntryTest_By_Id_Fail_Test() {
        assertNull(entryTestDao.getEntryTestById(100));
    }

    @Test
    void get_All_EntryTest_Success_Test() {
        assertNotNull(entryTestDao.getAllEntryTest());
    }

    @Test
    void get_All_EntryTest_Fail_Test() {
        assertNull(entryTestDao.getAllEntryTest());
    }

    @Test
    void update_EntryTest_By_Id_Success_Test(){
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
    void update_EntryTest_By_Id_Fail_Test(){
        EntryTest entryTest = new EntryTest();
        entryTest.setTestId(1);
        entryTest.setTime("2020-01-01");
        entryTest.setDate(LocalDate.parse("2020-01-01"));
        entryTest.setLanguageValuator("Language Valuator 2");
        entryTest.setTechnicalResult(8.25);
        entryTest.setResult(ResultType.PASS);
//        entryTest.setRemark("Remark 2");

        entryTestDao.updateEntryTestById(entryTest);
    }

    @Test
    void delete_EntryTest_By_Id_Success_Test(){
        entryTestDao.deleteEntryTestById(1);
    }

    @Test
    void delete_EntryTest_By_Id_Fail_Test(){
        entryTestDao.deleteEntryTestById(100);
    }
}
