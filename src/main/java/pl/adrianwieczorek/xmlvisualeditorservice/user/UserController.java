package pl.adrianwieczorek.xmlvisualeditorservice.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestAPIConstants;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.User;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.SignupDTO;

@RestController
@RequestMapping(RestAPIConstants.USER)
@Slf4j
public class UserController {

  @Autowired
  private UserServiceImpl userService;

//  //@Secured({"ROLE_ADMIN", "ROLE_USER"})
//  @PreAuthorize("hasRole('ADMIN')")
//  @GetMapping("/users")
//  public List<User> listUser(){
//    return userService.findAll();
//  }

  //@Secured("ROLE_USER")
  //@PreAuthorize("hasRole('USER')")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @GetMapping("/{id}")
  public User getOne(@PathVariable Long id) {
    return userService.findById(id);
  }

  @PostMapping(RestAPIConstants.SIGN_UP)
  public User create(@RequestBody SignupDTO user) {
    log.info(user.toString());
    return userService.save(user);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public User deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return new User(id);
  }
}
