package com.gregory.gregoryservice.bizkeycloakmodel.model.request;

import com.gregory.gregoryservice.bizkeycloakmodel.validator.NotSuperAdminRole;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewRoleRequest {

  @NotSuperAdminRole
  private String name;

  private List<String> scopes;
}
