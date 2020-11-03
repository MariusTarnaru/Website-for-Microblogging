package sda.backend.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sda.backend.server.model.Entry;

import java.util.Optional;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
public Optional<Entry> findByContent(String content);
}
