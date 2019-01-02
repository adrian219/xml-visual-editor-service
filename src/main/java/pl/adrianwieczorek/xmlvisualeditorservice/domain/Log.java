package pl.adrianwieczorek.xmlvisualeditorservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log extends Auditable<String> {
  private static final long serialVersionUID = 4565464563534522432L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Integer httpStatus;

  @Column
  private String httpMethod;

  @Column
  private String path;

  @Column
  private String clientIp;

  @Column
  @Lob
  private String request;

  @Column
  @Lob
  private String response;
}
