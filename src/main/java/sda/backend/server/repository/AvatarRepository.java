package sda.backend.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sda.backend.server.model.Avatar;

@Repository
public interface AvatarRepository extends CrudRepository<Avatar, Long> {


}
