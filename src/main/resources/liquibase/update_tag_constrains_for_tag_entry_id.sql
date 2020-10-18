ALTER TABLE tag
    ADD CONSTRAINT FOREIGN KEY (tag_entry_id) REFERENCES tag_entry (tag_entry_id);



