package com.gregory.gregoryservice.bizmodel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "bizlog")
public class BizLog {

  @Id
  @SequenceGenerator(name = "bizlog_seq", sequenceName = "bizlog_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bizlog_seq")
  @JsonProperty("id")
  private Long ID;

  @Column(nullable = false)
  private String module;

  @Column(name = "type_", nullable = false)
  private String type;

  @Embedded
  private BizLogTarget target;

  @Column(columnDefinition = "text")
  private String content;

  @Column(nullable = false)
  private String ip;

  @Column(nullable = false)
  private String username;

  @Column
  private String userRole;

  @Column
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Transient
  private boolean favorite;

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    BizLog v = (BizLog) o;
    return ID != null && Objects.equals(ID, v.ID);
  }

  @Override
  public int hashCode() {

    return getClass().hashCode();
  }
}
