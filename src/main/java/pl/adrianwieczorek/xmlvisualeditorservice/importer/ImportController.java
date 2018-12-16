package pl.adrianwieczorek.xmlvisualeditorservice.importer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestAPIConstants;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ImportDTO;

@RestController
@RequestMapping(RestAPIConstants.IMPORT)
public class ImportController {

  @Autowired
  private ImportXmlService importXmlService;

  @PostMapping
  public ImportDTO importer(@RequestBody String xml){
    return importXmlService.importXML(xml);
  }
}
