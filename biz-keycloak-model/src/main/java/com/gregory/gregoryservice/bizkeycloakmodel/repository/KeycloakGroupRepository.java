package com.gregory.gregoryservice.bizkeycloakmodel.repository;

import java.util.List;
import com.gregory.gregoryservice.bizkeycloakmodel.model.KeycloakGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeycloakGroupRepository extends JpaRepository<KeycloakGroup, String> {

  List<KeycloakGroup> findAllByParentGroup(String parentGroup);
}