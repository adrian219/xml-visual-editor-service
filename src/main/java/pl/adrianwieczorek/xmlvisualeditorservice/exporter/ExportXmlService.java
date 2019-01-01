package pl.adrianwieczorek.xmlvisualeditorservice.exporter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.OwnXml;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ExportDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.XmlNodeDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.own.xml.OwnXMLRepository;
import pl.adrianwieczorek.xmlvisualeditorservice.parser.OutputXMLParser;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Service
public class ExportXmlService {
  private static final Integer EXPORT_PRETTY_STRING_FACTOR = 4;

  @Autowired
  private OutputXMLParser outputXMLParser;

  @Autowired
  private OwnXMLRepository ownXMLRepository;

  public ExportDTO exportXML(XmlNodeDTO xmlNodeDTO){
    log.info("EXPORT XML [xmlNodeDTO={}]", xmlNodeDTO);

    ExportDTO exportDTO = new ExportDTO();
    exportDTO.setXml(outputXMLParser.format(outputXMLParser.toXML(xmlNodeDTO), EXPORT_PRETTY_STRING_FACTOR));

    log.info("RESULT [xml={}]", exportDTO.getXml());

    return exportDTO;
  }

  public void exportToFile(Long id, HttpServletResponse response) throws IOException {
    MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;

    OwnXml ownXml = ownXMLRepository.getOne(id);

    // Content-Type
    response.setContentType(mediaType.getType());

    // Content-Disposition
    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + createFilename(ownXml));

    BufferedInputStream inStream = new BufferedInputStream(new ByteArrayInputStream(ownXml.getXml().getBytes()));
    BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

    byte[] buffer = new byte[1024];
    int bytesRead;
    while ((bytesRead = inStream.read(buffer)) != -1) {
      outStream.write(buffer, 0, bytesRead);
    }
    outStream.flush();
    inStream.close();
  }

  private String createFilename(OwnXml ownXml) {
    return ownXml.getName().replace(" ", "_").concat(".xml");
  }
}
