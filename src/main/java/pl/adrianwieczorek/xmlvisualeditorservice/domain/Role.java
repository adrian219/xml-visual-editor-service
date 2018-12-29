package pl.adrianwieczorek.xmlvisualeditorservice.domain;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Data
public class Role extends Auditable<String> {
  private static final long serialVersionUID = -8444697813123556689L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String name;

  @Column
  private String description;
}
