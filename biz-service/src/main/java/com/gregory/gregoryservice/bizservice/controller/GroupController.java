package com.gregory.gregoryservice.bizservice.controller;

import com.gregory.gregoryservice.bizkeycloakmodel.model.Group;
import com.gregory.gregoryservice.bizkeycloakmodel.model.KeycloakGroup;
import com.gregory.gregoryservice.bizkeycloakmodel.model.request.MoveGroupRequest;
import com.gregory.gregoryservice.bizkeycloakmodel.model.request.RenameGroupRequest;
import com.gregory.gregoryservice.bizkeycloakmodel.repository.KeycloakGroupRepository;
import com.gregory.gregoryservice.bizkeycloakmodel.service.KeycloakGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户组管理")
@RestController
@RequestMapping("group")
public class GroupController {

  private final KeycloakGroupService keycloakGroupService;

  private final KeycloakGroupRepository groupRepository;

  @Autowired
  public GroupController(KeycloakGroupService keycloakGroupService,
      KeycloakGroupRepository groupRepository) {

    this.keycloakGroupService = keycloakGroupService;
    this.groupRepository = groupRepository;
  }

  @RequestMapping(method = RequestMethod.HEAD)
  @PreAuthorize("hasAnyAuthority('group:crud')")
  public ResponseEntity<?> statGroup(@RequestParam String name,
      @RequestParam(required = false) String parentId) {

    if (groupRepository.exists(Example.of(KeycloakGroup.builder()
        .name(name).parentGroup(parentId).build()))) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  @Operation(summary = "新建用户组")
  @PreAuthorize("hasAnyAuthority('group:crud')")
  public Group newGroup(@RequestBody Group group) {

    return keycloakGroupService.newGroup(group);
  }

  @Operation(summary = "编辑用户组")
  @PostMapping("{id}:rename")
  @PreAuthorize("hasAnyAuthority('group:crud')")
  public Group renameGroup(@PathVariable String id, @RequestBody RenameGroupRequest request) {

    return keycloakGroupService.renameGroup(id, request.getNewGroupName());
  }

  @Operation(summary = "移动用户组")
  @PostMapping("{id}:move")
  @PreAuthorize("hasAnyAuthority('group:crud')")
  public Group moveGroup(@PathVariable String id, @RequestBody MoveGroupRequest request) {

    return keycloakGroupService.moveGroup(id, request.getParentId());
  }

  @GetMapping("{id}")
  @Operation(summary = "查看用户组")
  @PreAuthorize("hasAnyAuthority('group:crud')")
  public Group getGroup(@PathVariable String id) {

    return keycloakGroupService.getGroup(id);
  }

  @GetMapping
  @Operation(summary = "获取用户组")
  @PreAuthorize("permitAll()")
  public List<Group> getGroups() {

    return keycloakGroupService.getGroups();
  }

  @DeleteMapping("{id}")
  @Operation(summary = "删除用户组")
  @PreAuthorize("hasAnyAuthority('group:crud')")
  public void deleteGroup(@PathVariable String id) {

    keycloakGroupService.deleteGroup(id);
  }
}