package pl.adrianwieczorek.xmlvisualeditorservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@Entity
@Audited
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnXml extends Auditable<String> {
  private static final long serialVersionUID = -3441797813123594349L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String xml;

  @Column
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @NotAudited
  private User user;
}
