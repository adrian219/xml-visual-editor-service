package pl.adrianwieczorek.xmlvisualeditorservice.dto;

import lombok.Data;

@Data
public class SignupDTO {
  private String username;
  private String password;
  private String email;
  private String firstName;
  private String lastName;
}
