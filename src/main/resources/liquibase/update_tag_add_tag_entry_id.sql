insert into tag (tag_id, tag_entry_id)
values (1, 1),
       (2, 1),
       (3, 2) as t
on duplicate key
update tag_entry_id=t.tag_entry_id;



