package com.gregory.gregoryservice.bizmodel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
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
@Table(name = "notification")
public class Notification {

  @Id
  @SequenceGenerator(name = "notification_seq", sequenceName = "notification_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_seq")
  @JsonProperty("id")
  private Long ID;

  @Column
  private String title;

  @Column(columnDefinition = "text")
  private String content;

  @Column
  private String userId;

  @Default
  @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @Exclude
  private Set<NotificationRead> reads = new HashSet<>();

  @Transient
  private Boolean read;

  @Column
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Notification v = (Notification) o;
    return ID != null && Objects.equals(ID, v.ID);
  }

  @Override
  public int hashCode() {

    return getClass().hashCode();
  }
}
