package pl.adrianwieczorek.xmlvisualeditorservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
