package sda.backend.server.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sda.backend.server.dto.DTOEntry;
import sda.backend.server.model.Entry;
import sda.backend.server.repository.EntryRepository;

import java.time.LocalDateTime;

@Service
public class EntryService {

    public final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    private DTOEntry entryToDTOEntry(Entry entry) {
        DTOEntry dtoEntry = DTOEntry.builder()
                .entryId(entry.getEntryId())
                .content(entry.getContent())
                .link(entry.getLink())
                .imagePath(entry.getImagePath())
                .createdDate(entry.getCreatedDate())
                .status(entry.getStatus())
                .type(entry.getType())
                .account(entry.getAccount())
                .tags(entry.getTags())
                .comments(entry.getComments())
                .likes(entry.getLikes())
                .sharedEntries(entry.getSharedEntries())
                .tagEntries(entry.getTagEntries())
                .build();
        return dtoEntry;
    }

    private Entry DTOEntryToEntry(DTOEntry dtoEntry) {
        Entry entry = Entry.builder()
                .entryId(dtoEntry.getEntryId())
                .content(dtoEntry.getContent())
                .link(dtoEntry.getLink())
                .imagePath(dtoEntry.getImagePath())
                .createdDate(dtoEntry.getCreatedDate())
                .status(dtoEntry.getStatus())
                .type(dtoEntry.getType())
                .account(dtoEntry.getAccount())
                .tags(dtoEntry.getTags())
                .comments(dtoEntry.getComments())
                .likes(dtoEntry.getLikes())
                .sharedEntries(dtoEntry.getSharedEntries())
                .tagEntries(dtoEntry.getTagEntries())
                .build();
        return entry;
    }

    public DTOEntry saveEntry(DTOEntry entry) {
        Entry newEntry = DTOEntryToEntry(entry);
        newEntry.setCreatedDate(LocalDateTime.now());
        entryRepository.save(newEntry);
        return entryToDTOEntry(entryRepository.findByContent(entry.getContent()).get());
    }

    public ResponseEntity getEntryById(Long id) {
        if (idExists(id)) {
            DTOEntry entry = entryToDTOEntry(entryRepository.findById(id).get());
            return new ResponseEntity<>(entry, HttpStatus.ACCEPTED);
        }
        return ResponseEntity.notFound().build();
    }

    private boolean idExists(Long id) {
        return entryRepository.existsById(id);
    }
}
