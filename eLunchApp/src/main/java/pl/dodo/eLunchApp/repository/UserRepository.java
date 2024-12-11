package pl.dodo.eLunchApp.repository;

import org.springframework.stereotype.Repository;
import pl.dodo.eLunchApp.model.User;

@Repository
public interface UserRepository extends BaseRepository<User> {
}
