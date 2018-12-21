package pl.adrianwieczorek.xmlvisualeditorservice.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.ValidationDTO;

@Service
@Slf4j
public class ValidationService {

  @Autowired
  private ValidatorXml validatorXml;

  public ValidationDTO validate(String xml) {
    log.info("VALIDATE XML: {}", xml);
    ValidationDTO validationDTO = new ValidationDTO();
    validationDTO.setXml(xml);
    validationDTO.setValidate(validatorXml.check(validationDTO.getXml()));

    log.info("VALIDATED XML: {} ==> {}", validationDTO.getXml(), validationDTO.getValidate());

    return validationDTO;
  }
}
