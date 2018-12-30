package pl.adrianwieczorek.xmlvisualeditorservice.check.taken;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.User;
import pl.adrianwieczorek.xmlvisualeditorservice.user.UserRepository;

@Service
@Slf4j
public class CheckTakenService {
  @Autowired
  private UserRepository userRepository;

  public CheckTakenResponseDTO checkEmail(CheckTakenDTO checkTakenDTO) {
    log.info("CHECK TAKEN EMAIL [email={}]", checkTakenDTO.getChecking());
    User user = userRepository.findByEmail(checkTakenDTO.getChecking());

    CheckTakenResponseDTO response = new CheckTakenResponseDTO();
    response.setCheck(user == null);

    log.info("EMAIL IS EXISTS? {}", response.getCheck());

    return response;
  }

  public CheckTakenResponseDTO checkUsername(CheckTakenDTO checkTakenDTO) {
    log.info("CHECK TAKEN USERNAME [username={}]", checkTakenDTO.getChecking());
    User user = userRepository.findByUsername(checkTakenDTO.getChecking());

    CheckTakenResponseDTO response = new CheckTakenResponseDTO();
    response.setCheck(user == null);

    log.info("USERNAME IS EXISTS? {}", response.getCheck());

    return response;
  }
}
