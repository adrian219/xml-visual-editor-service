package pl.adrianwieczorek.xmlvisualeditorservice.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestAPIConstants;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.User;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.UserDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.user.UserRepository;

@RestController
@RequestMapping(RestAPIConstants.TOKEN)
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenProvider jwtTokenUtil;

  @Autowired
  private UserRepository userRepository;

  @PostMapping(RestAPIConstants.GENERATE)
  public ResponseEntity<?> register(@RequestBody UserDTO loginUser) throws AuthenticationException {

    final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginUser.getUsername(),
                    loginUser.getPassword()
            )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    final String token = jwtTokenUtil.generateToken(authentication);
    User user = userRepository.findByUsername(loginUser.getUsername());
    return ResponseEntity.ok(new AuthToken(user.getId(), user.getUsername(), null, user.getFirstname(), user.getLastname(), token));
  }
}