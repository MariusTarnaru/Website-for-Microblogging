package sda.backend.server.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sda.backend.server.dto.DTOEntry;
import sda.backend.server.exception.Message;
import sda.backend.server.model.Account;
import sda.backend.server.model.Entry;
import sda.backend.server.repository.AccountRepository;
import sda.backend.server.repository.EntryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntryService {

    public final EntryRepository entryRepository;
    public final AccountRepository accountRepository;

    public EntryService(EntryRepository entryRepository, AccountRepository accountRepository) {
        this.entryRepository = entryRepository;
        this.accountRepository = accountRepository;
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
                .build();
        return entry;
    }

    public ResponseEntity saveEntry(DTOEntry entry) {
        Entry newEntry = DTOEntryToEntry(entry);
        newEntry.setCreatedDate(LocalDateTime.now());
        entryRepository.save(newEntry);
        DTOEntry savedEntry = entryToDTOEntry(entryRepository.findByContent(entry.getContent()).get());
        return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
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

    public ResponseEntity getEntriesByUsername(String username) {
        if (accountRepository.existsByUsername(username)) {
            Account account = accountRepository.findByUsername(username).get();
            List<Long> followedList = new ArrayList<>();
            List<Entry> entryList =  new ArrayList<>();
            List<DTOEntry> dtoEntryList = new ArrayList<>();
            followedList.add(account.getAccountId());
            account.getFollowers().stream().forEach(follower -> followedList.add(follower.getAccountId()));
            followedList.stream().forEach(id -> entryList.addAll(accountRepository.findById(id).get().getEntries()));
            entryList.stream().forEach(entry -> dtoEntryList.add(entryToDTOEntry(entry)));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(dtoEntryList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("The username not exist!"));
    }
}
