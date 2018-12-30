package pl.adrianwieczorek.xmlvisualeditorservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}