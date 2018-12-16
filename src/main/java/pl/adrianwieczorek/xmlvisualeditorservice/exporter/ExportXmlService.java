package pl.adrianwieczorek.xmlvisualeditorservice.exporter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ExportDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.XmlNodeDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.parser.OutputXMLParser;

@Slf4j
@Service
public class ExportXmlService {

  @Autowired
  private OutputXMLParser outputXMLParser;

  public ExportDTO exportXML(XmlNodeDTO xmlNodeDTO){
    log.info("EXPORT XML [xmlNodeDTO={}]", xmlNodeDTO.toString());

    ExportDTO exportDTO = new ExportDTO();
    exportDTO.setXml(outputXMLParser.format(outputXMLParser.toXML(xmlNodeDTO), 4));

    log.info("RESULT [xml={}]", exportDTO.getXml());

    return exportDTO;
  }
}