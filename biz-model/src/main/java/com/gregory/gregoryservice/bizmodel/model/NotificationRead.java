package com.gregory.gregoryservice.bizmodel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "notificationread")
public class NotificationRead {

  @Id
  @SequenceGenerator(name = "notificationread_seq", sequenceName = "notificationread_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notificationread_seq")
  @JsonProperty("id")
  private Long ID;

  @Column(nullable = false)
  private String userId;

  @ManyToOne
  @JsonIgnore
  private Notification notification;

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
    NotificationRead v = (NotificationRead) o;
    return ID != null && Objects.equals(ID, v.ID);
  }

  @Override
  public int hashCode() {

    return getClass().hashCode();
  }
}