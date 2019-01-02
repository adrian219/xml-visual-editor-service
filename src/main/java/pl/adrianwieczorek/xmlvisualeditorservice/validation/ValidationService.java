package pl.adrianwieczorek.xmlvisualeditorservice.validation;

import com.google.common.base.Strings;
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
    Boolean check;

    if(Strings.isNullOrEmpty(xml)) {
      check = true;
    } else {
      //checking validation
      try {
        check = validatorXml.check(validationDTO.getXml());
      }catch(ValidationException e) {
        validationDTO.setErrorMessage(e.getMessage());
        check = false;
      }
    }

    validationDTO.setValidate(check);

    log.info("VALIDATED XML: {} ==> {}", validationDTO.getXml(), validationDTO.getValidate());

    return validationDTO;
  }
}
