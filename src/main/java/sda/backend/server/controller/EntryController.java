package sda.backend.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sda.backend.server.dto.DTOEntry;
import sda.backend.server.service.EntryService;

@CrossOrigin(origins = "http://localhost:4200")
@Transactional
@Validated
@RestController
@RequestMapping("/api")
public class EntryController {
    private final EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping("/addEntry")
    public ResponseEntity addEntry(@RequestBody DTOEntry entry) {
        DTOEntry dtoEntry = entryService.saveEntry(entry);
        return new ResponseEntity<>(dtoEntry, HttpStatus.CREATED);
    }

    @GetMapping("/entrys/{id}")
    public ResponseEntity getEntryById(Long id) {
        return entryService.getEntryById(id);
    }
}
