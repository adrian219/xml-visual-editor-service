package pl.adrianwieczorek.xmlvisualeditorservice.authorization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.User;
import pl.adrianwieczorek.xmlvisualeditorservice.own.xml.OwnXMLRepository;
import pl.adrianwieczorek.xmlvisualeditorservice.user.UserRepository;

@Slf4j
@Service
public class SecurityOwnerXmlService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OwnXMLRepository ownXMLRepository;

  public Boolean checkIfOwnerXml(Long ownXmlId, String username) {
    User user = userRepository.findByUsername(username);

    return ownXMLRepository.existsByIdAndUser(ownXmlId, user);
  }
}
