package pl.adrianwieczorek.xmlvisualeditorservice.exporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestAPIConstants;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ExportDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.XmlNodeDTO;

@RestController
@RequestMapping(RestAPIConstants.EXPORT)
public class ExportController {

  @Autowired
  private ExportXmlService exportXmlService;

  @PostMapping
  public ExportDTO exporter(@RequestBody XmlNodeDTO xmlNodeDTO){
    return exportXmlService.exportXML(xmlNodeDTO);
  }
}
