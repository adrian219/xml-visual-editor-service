package pl.adrianwieczorek.xmlvisualeditorservice.importer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ImportDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.parser.InputXMLParser;

@Slf4j
@Service
public class ImportXmlService {

  @Autowired
  private InputXMLParser inputXMLParser;

  public ImportDTO importXML(String xml){
    log.info("IMPORT XML [xml={}]", xml);

    ImportDTO importDTO = new ImportDTO();
    importDTO.setNode(inputXMLParser.toNode(xml));

    log.info("RESULT [node={}]", importDTO.getNode().toString());
    return importDTO;
  }
}
