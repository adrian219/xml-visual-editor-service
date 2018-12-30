package pl.adrianwieczorek.xmlvisualeditorservice.own.xml;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.OwnXml;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.User;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.OwnXmlDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OwnXMLService {

  @Autowired
  private OwnXMLRepository ownXMLRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<OwnXmlDTO> findAllByUsername(String username) {
    log.info("FIND ALL BY USER [username={}]", username);
    return ownXMLRepository.findAllByUserOrderByLastModifiedDateDesc(userRepository.findByUsername(username)).stream()
            .map(entity -> modelMapper.map(entity, OwnXmlDTO.class))
            .collect(Collectors.toList());
  }

  public OwnXmlDTO get(Long id) {
    log.info("GET [id={}]", id);
    return modelMapper.map(ownXMLRepository.getOne(id), OwnXmlDTO.class);
  }

  public OwnXmlDTO create(OwnXmlDTO ownXmlDTO, String ownerUsername) {
    log.info("CREATE [name={}]", ownXmlDTO.getName());
    OwnXml own = modelMapper.map(ownXmlDTO, OwnXml.class);
    User ownerUser = userRepository.findByUsername(ownerUsername);
    own.setUser(ownerUser);
    own.setXml("");
    return modelMapper.map(ownXMLRepository.save(own), OwnXmlDTO.class);
  }

  public OwnXmlDTO save(OwnXmlDTO ownXML) {
    log.info("SAVE [id={}]", ownXML.getId());
    OwnXml own = ownXMLRepository.getOne(ownXML.getId());
    own.setXml(ownXML.getXml());
    return modelMapper.map(ownXMLRepository.save(own), OwnXmlDTO.class);
  }

  public void delete(Long id) {
    log.info("DELETE [id={}]", id);
    ownXMLRepository.deleteById(id);
  }
}
