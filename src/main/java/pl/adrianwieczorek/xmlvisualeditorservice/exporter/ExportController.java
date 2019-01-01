package pl.adrianwieczorek.xmlvisualeditorservice.exporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestAPIConstants;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ExportDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ExportRequestDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping(RestAPIConstants.EXPORT)
public class ExportController {

  @Autowired
  private ExportXmlService exportXmlService;

  @PostMapping
  public ExportDTO exporter(@RequestBody ExportRequestDTO exportRequestDTO){
    if(exportRequestDTO.getNode() == null) {
      return null;
    }

    return exportXmlService.exportXML(exportRequestDTO.getNode());
  }

  @GetMapping(RestAPIConstants.XML + "/{id}")
  public void exportToFile(@PathVariable Long id, HttpServletResponse response) throws IOException {
    exportXmlService.exportToFile(id, response);
  }
}
