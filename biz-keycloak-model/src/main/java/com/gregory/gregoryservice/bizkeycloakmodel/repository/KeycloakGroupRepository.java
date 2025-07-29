package com.gregory.gregoryservice.bizkeycloakmodel.repository;

import com.gregory.gregoryservice.bizkeycloakmodel.model.KeycloakGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeycloakGroupRepository extends JpaRepository<KeycloakGroup, String> {

  List<KeycloakGroup> findAllByParentGroup(String parentGroup);
}