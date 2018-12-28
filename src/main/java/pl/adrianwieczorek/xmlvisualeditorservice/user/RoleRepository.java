package pl.adrianwieczorek.xmlvisualeditorservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
