package pl.adrianwieczorek.xmlvisualeditorservice.importer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.OwnXml;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ImportDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ImportFileDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.own.xml.OwnXMLRepository;
import pl.adrianwieczorek.xmlvisualeditorservice.parser.InputXMLParser;
import pl.adrianwieczorek.xmlvisualeditorservice.util.XmlHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Service
public class ImportXmlService {

  @Autowired
  private InputXMLParser inputXMLParser;

  @Autowired
  private OwnXMLRepository ownXMLRepository;

  @Autowired
  private XmlHelper xmlHelper;

  public ImportDTO importXML(String xml){
    log.info("IMPORT XML [xml={}]", xml);

    ImportDTO importDTO = new ImportDTO();
    importDTO.setNode(inputXMLParser.toNode(xml));

    log.info("RESULT [node={}]", importDTO.getNode().toString());
    return importDTO;
  }

  public ImportFileDTO importXMLFromFile(Long id, MultipartFile file) throws IOException {
    log.info("IMPORT XML FROM FILE [ownXmlId={}]", id);
    OwnXml ownXml = ownXMLRepository.getOne(id);
    ownXml.setXml(xmlHelper.prettyFormat(storeXml(file)));
    ownXMLRepository.save(ownXml);

    ImportFileDTO importFileDTO = new ImportFileDTO();
    String xml = ownXml.getXml();
    importFileDTO.setNode(importXML(xml).getNode());
    importFileDTO.setXml(xml);

    return importFileDTO;
  }

  private String storeXml(MultipartFile file) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
    StringBuilder out = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      out.append(line);   // add everything to StringBuilder
      out.append("\n");
    }

    return out.toString();
  }
}
