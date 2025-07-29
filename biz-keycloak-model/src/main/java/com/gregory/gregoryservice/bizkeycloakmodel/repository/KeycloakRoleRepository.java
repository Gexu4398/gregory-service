package com.gregory.gregoryservice.bizkeycloakmodel.repository;

import java.util.Optional;
import java.util.Set;
import com.gregory.gregoryservice.bizkeycloakmodel.model.KeycloakRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeycloakRoleRepository extends JpaRepository<KeycloakRole, String> {

  Set<KeycloakRole> findByUsers_Username(String username);

  Optional<KeycloakRole> findByNameAndRealmId(String name, String realmId);

  boolean existsByNameAndClientRole(String roleName, Boolean clientRole);
}