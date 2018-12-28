package pl.adrianwieczorek.xmlvisualeditorservice.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestAPIConstants;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ValidationDTO;

@RestController
@RequestMapping(RestAPIConstants.VALIDATION)
public class ValidationXmlController {

  @Autowired
  private ValidationService validationService;

  @PostMapping
  public ValidationDTO check(@RequestBody String xml){
    return validationService.validate(xml);
  }
}
