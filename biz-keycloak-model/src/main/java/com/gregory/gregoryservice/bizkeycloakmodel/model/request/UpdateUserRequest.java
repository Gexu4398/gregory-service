package com.gregory.gregoryservice.bizkeycloakmodel.model.request;

import java.util.Set;
import com.gregory.gregoryservice.bizkeycloakmodel.validator.NotSuperAdminRoleId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {

  private String name;

  private String groupId;

  private String phoneNumber;

  @NotSuperAdminRoleId
  private Set<String> roleId;

  private String picture;
}
