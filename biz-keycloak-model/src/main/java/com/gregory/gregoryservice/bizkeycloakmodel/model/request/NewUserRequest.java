package com.gregory.gregoryservice.bizkeycloakmodel.model.request;

import com.gregory.gregoryservice.bizkeycloakmodel.validator.NotSuperAdminRoleId;
import com.gregory.gregoryservice.bizkeycloakmodel.validator.NotSuperAdminUsername;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUserRequest {

  @NotBlank
  @NotSuperAdminUsername
  private String username;

  private String password;

  private String name;

  private String groupId;

  private String phoneNumber;

  private String picture;

  @NotSuperAdminRoleId
  private Set<String> roleId;
}
