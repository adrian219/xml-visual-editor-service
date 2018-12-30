package pl.adrianwieczorek.xmlvisualeditorservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
@Data
@AllArgsConstructor
public class User extends Auditable<String> {
  private static final long serialVersionUID = -1113697813123896689L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;

  @Column
  @JsonIgnore
  private String password;

  @Column(unique = true)
  private String email;

  @Column
  private String firstname;

  @Column
  private String lastname;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(name = "USER_ROLES", joinColumns = {
          @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
          @JoinColumn(name = "ROLE_ID")})
  @AuditJoinTable
  private Set<Role> roles = new HashSet<>();

  public User() {

  }

  public User(Long id) {
    this.id = id;
  }
}
