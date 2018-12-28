package pl.adrianwieczorek.xmlvisualeditorservice.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthToken {
  private Long id;
  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private String token;
}