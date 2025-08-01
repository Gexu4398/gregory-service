package com.gregory.gregoryservice.bizkeycloakmodel.model;

import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {

  private String id;

  private String name;

  private Set<String> scopes;
}
