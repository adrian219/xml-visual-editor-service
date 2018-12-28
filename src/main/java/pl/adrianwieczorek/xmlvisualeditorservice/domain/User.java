package pl.adrianwieczorek.xmlvisualeditorservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String username;

  @Column
  @JsonIgnore
  private String password;

  @Column
  private String email;

  @Column
  private String firstname;

  @Column
  private String lastname;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "USER_ROLES", joinColumns = {
          @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
          @JoinColumn(name = "ROLE_ID")})
  private Set<Role> roles;

  public User() {

  }

  public User(Long id) {
    this.id = id;
  }
}
