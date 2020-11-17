ALTER TABLE tag_entry
    ADD CONSTRAINT fk_tag_id FOREIGN KEY (tag_id) REFERENCES entry (entry_id),
    ADD CONSTRAINT fk_entry_id FOREIGN KEY (entry_id) REFERENCES tag (tag_id);



