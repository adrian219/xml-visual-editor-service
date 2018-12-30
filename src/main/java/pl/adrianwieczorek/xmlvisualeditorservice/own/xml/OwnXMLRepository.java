package pl.adrianwieczorek.xmlvisualeditorservice.own.xml;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.OwnXml;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.User;

import java.util.List;

@Repository
public interface OwnXMLRepository extends JpaRepository<OwnXml, Long> {
  List<OwnXml> findAllByUser(User user);
  List<OwnXml> findAllByUserOrderByLastModifiedDateDesc(User user);

}
