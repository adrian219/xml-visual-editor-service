package pl.adrianwieczorek.xmlvisualeditorservice.dto;

import lombok.Data;

@Data
public class SignupDTO {
  private String username;
  private String password;
  private String email;
  private String firstname;
  private String lastname;
}
