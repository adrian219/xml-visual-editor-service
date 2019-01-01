package pl.adrianwieczorek.xmlvisualeditorservice.domain;

import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {
  private static final long serialVersionUID = -5633697813123896689L;

  @Version
  @NotNull
  @Column
  private Integer version;

  @CreatedBy
  @NotNull
  @Column(updatable = false)
  private U createdBy;

  @CreatedDate
  @NotNull
  @Column(updatable = false)
  private LocalDateTime createdDate;

  @LastModifiedBy
  @NotNull
  @Column
  private U lastModifiedBy;

  @LastModifiedDate
  @NotNull
  @Column
  private LocalDateTime lastModifiedDate;
}
