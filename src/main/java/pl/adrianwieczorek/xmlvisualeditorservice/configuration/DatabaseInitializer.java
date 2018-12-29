package pl.adrianwieczorek.xmlvisualeditorservice.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.UserRole;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.Role;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.User;
import pl.adrianwieczorek.xmlvisualeditorservice.user.RoleRepository;
import pl.adrianwieczorek.xmlvisualeditorservice.user.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class DatabaseInitializer {

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private UserRepository userRepository;

  @Value("${app.admin.username}")
  private String adminUsername;

  @Value("${app.admin.password}")
  private String adminPassword;

  @Value("${app.admin.email}")
  private String adminEmail;

  @Value("${app.admin.firstName}")
  private String adminFirstName;

  @Value("${app.admin.lastName}")
  private String adminLastName;

  @Bean
  InitializingBean seedDatabase() {
    return () -> {
      if(roleRepository.count() == 0) {
        log.info("ADD ROLES");
        UserRole[] roles = UserRole.values();
        for(UserRole userRole : roles) {
          Role role = new Role();
          role.setName(userRole.name());
          role.setDescription(userRole.description());
          roleRepository.save(role);
        }
      }

      if(userRepository.count() == 0) {
        log.info("ADD ADMIN ACCOUNT");
        User user = new User();
        user.setUsername(adminUsername);
        user.setPassword(adminPassword);
        user.setEmail(adminEmail);
        user.setFirstname(adminFirstName);
        user.setLastname(adminLastName);

        user = userRepository.save(user);

        Set<Role> adminRoles = new HashSet<>(Arrays.asList(roleRepository.findByName(UserRole.ADMIN.name()), roleRepository.findByName(UserRole.USER.name())));
        user.setRoles(adminRoles);

        userRepository.save(user);
      }
    };
  }
}

