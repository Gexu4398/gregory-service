package com.gregory.gregoryservice.bizkeycloakmodel.model.request;


import com.gregory.gregoryservice.bizkeycloakmodel.validator.NotSuperAdminRole;
import lombok.Data;

@Data
public class RenameRoleRequest {

  @NotSuperAdminRole
  private String newRoleName;
}
