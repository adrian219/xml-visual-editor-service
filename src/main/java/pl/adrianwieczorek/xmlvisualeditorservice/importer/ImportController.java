package pl.adrianwieczorek.xmlvisualeditorservice.importer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestAPIConstants;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ImportDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ImportFileDTO;

import java.io.IOException;

@RestController
@RequestMapping(RestAPIConstants.IMPORT)
public class ImportController {

  @Autowired
  private ImportXmlService importXmlService;

  @PostMapping
  public ImportDTO importer(@RequestBody String xml){
    return importXmlService.importXML(xml);
  }

  @PostMapping(RestAPIConstants.XML + "/{id}")
  public ImportFileDTO importXmlFromFile(@PathVariable Long id, @RequestPart("file") MultipartFile file) throws IOException {
    return importXmlService.importXMLFromFile(id, file);
  }
}
