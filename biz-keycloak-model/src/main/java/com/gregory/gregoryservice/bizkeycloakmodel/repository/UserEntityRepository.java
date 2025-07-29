package com.gregory.gregoryservice.bizkeycloakmodel.repository;

import java.util.Collection;
import java.util.Optional;
import com.gregory.gregoryservice.bizkeycloakmodel.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, String>,
    JpaSpecificationExecutor<UserEntity> {

  Collection<UserEntity> findByGroups_Id(String id);

  boolean existsByUsername(String username);

  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findByUsernameAndRealmId(String username, String realmId);

  @Query("""
      select u from UserEntity u
      join u.groups g
      where g.id = :groupId
      """)
  Collection<UserEntity> findByGroups_IdAndUsernameNotLike(String groupId);
}