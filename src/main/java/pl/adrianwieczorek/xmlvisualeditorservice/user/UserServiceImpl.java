package pl.adrianwieczorek.xmlvisualeditorservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.UserRole;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.Role;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.User;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.SignupDTO;

import java.util.*;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private BCryptPasswordEncoder bcryptEncoder;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
  }

  private Set<SimpleGrantedAuthority> getAuthority(User user) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    user.getRoles().forEach(role -> {
      //authorities.add(new SimpleGrantedAuthority(role.getName()));
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
    });
    return authorities;
    //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
  }

  public List<User> findAll() {
    List<User> list = new ArrayList<>();
    userRepository.findAll().iterator().forEachRemaining(list::add);
    return list;
  }

  public void delete(long id) {
    userRepository.deleteById(id);
  }

  public User findOne(String username) {
    return userRepository.findByUsername(username);
  }

  public User findById(Long id) {
    return userRepository.findById(id).get();
  }

  public User save(SignupDTO signUp) {
    User user = new User();
    user.setUsername(signUp.getUsername());
    user.setPassword(bcryptEncoder.encode(signUp.getPassword()));
    user.setEmail(signUp.getEmail());
    user.setFirstname(signUp.getFirstName());
    user.setLastname(signUp.getLastName());

    user = userRepository.save(user);

    Set<Role> roles = new HashSet<>();
    roles.add(roleRepository.findByName(UserRole.USER.name()));
    user.setRoles(roles);

    return userRepository.save(user);
  }
}