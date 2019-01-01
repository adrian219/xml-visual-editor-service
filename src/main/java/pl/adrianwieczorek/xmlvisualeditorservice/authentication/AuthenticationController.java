package pl.adrianwieczorek.xmlvisualeditorservice.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestAPIConstants;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.User;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.UserDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.user.UserRepository;

@RestController
@RequestMapping(RestAPIConstants.TOKEN)
@Slf4j
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenProvider jwtTokenUtil;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bcryptEncoder;

  @PostMapping(RestAPIConstants.GENERATE)
  public ResponseEntity<?> generateToken(@RequestBody UserDTO loginUser) throws AuthenticationException {

    //todo przeniesc do jakiego serwisu
    User user = userRepository.findByUsernameOrEmail(loginUser.getUsername(), loginUser.getUsername());
    if (user == null) {
      log.warn("Invalid username/email or password [username or email={}]", loginUser.getUsername());
      throw new InvalidUsernameOrPasswordException("LOGIN.LOGIN_FAILED_MESSAGE");
    }

    if(!bcryptEncoder.matches(loginUser.getPassword(), user.getPassword())) {
      log.warn("Invalid password [username or email={}]", loginUser.getUsername());
      throw new InvalidUsernameOrPasswordException("LOGIN.LOGIN_FAILED_MESSAGE");
    }

    final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    loginUser.getPassword()
            )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    final String token = jwtTokenUtil.generateToken(authentication);

    return ResponseEntity.ok(new AuthToken(user.getId(), user.getUsername(), null, user.getFirstname(), user.getLastname(), token));
  }
}