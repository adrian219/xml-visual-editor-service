package pl.adrianwieczorek.xmlvisualeditorservice.logging;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
