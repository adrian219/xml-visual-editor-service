package pl.adrianwieczorek.xmlvisualeditorservice.check.taken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestAPIConstants;

@RestController
@RequestMapping(RestAPIConstants.CHECK_TAKEN)
public class CheckTakenController {

  @Autowired
  private CheckTakenService checkTakenService;

  @PostMapping
  @RequestMapping(RestAPIConstants.EMAIL)
  public CheckTakenResponseDTO checkEmail(@RequestBody CheckTakenDTO checkTakenDTO) {
    return checkTakenService.checkEmail(checkTakenDTO);
  }

  @PostMapping
  @RequestMapping(RestAPIConstants.USERNAME)
  public CheckTakenResponseDTO checkUsername(@RequestBody CheckTakenDTO checkTakenDTO) {
    return checkTakenService.checkUsername(checkTakenDTO);
  }
}
