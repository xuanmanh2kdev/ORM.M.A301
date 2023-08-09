package training.dao;

import training.entities.EntryTest;

import java.util.List;
import java.util.Optional;

public interface EntryTestDao {
    void createEntryTest(EntryTest entryTest);
    Optional<EntryTest> getEntryTestById(Integer entryTestID);
    List<EntryTest> getAllEntryTest();
    Optional<EntryTest> updateEntryTestById(EntryTest entryTest);
    Optional<EntryTest> deleteEntryTestById(Integer entryTestID);
}
