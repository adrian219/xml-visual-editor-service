package pl.adrianwieczorek.xmlvisualeditorservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private String name;

  @Column
  private String description;
}
