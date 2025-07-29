package com.gregory.gregoryservice.bizservice.controller;

import com.gregory.gregoryservice.bizkeycloakmodel.helper.JwtHelper;
import com.gregory.gregoryservice.bizkeycloakmodel.model.User;
import com.gregory.gregoryservice.bizkeycloakmodel.model.request.ResetPasswordRequest;
import com.gregory.gregoryservice.bizkeycloakmodel.service.KeycloakUserService;
import com.gregory.gregoryservice.bizmodel.model.Notification;
import com.gregory.gregoryservice.bizservice.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profile")
// 此处用 MyProfileController 命名是因为 Spring Data Rest 中有一个同名的 Controller，为了避免冲突导致启动失败
public class MyProfileController {

  private final KeycloakUserService keycloakUserService;

  private final NotificationService notificationService;

  @Autowired
  public MyProfileController(KeycloakUserService keycloakUserService,
      NotificationService notificationService) {

    this.keycloakUserService = keycloakUserService;
    this.notificationService = notificationService;
  }

  @PutMapping
  @Operation(summary = "修改用户信息")
  @PreAuthorize("isAuthenticated()")
  public User updateUser(@RequestBody User user) {

    return keycloakUserService.updateProfile(user);
  }

  @PostMapping("reset-password")
  @Operation(summary = "修改密码")
  @PreAuthorize("isAuthenticated()")
  @SneakyThrows
  public void resetPassword(@RequestBody ResetPasswordRequest request) {

    final var username = JwtHelper.getUsername();
    keycloakUserService.resetUserCredential(username, request.getOriginalPassword(),
        request.getPassword());
  }

  @Operation(summary = "标记所有通知为已读")
  @PostMapping("notification:read")
  @PreAuthorize("isAuthenticated()")
  @SneakyThrows
  public void markNotificationIsRead() {

    final var userId = JwtHelper.getUserId();
    notificationService.markAllAsRead(userId);
  }

  @Operation(summary = "获取通知")
  @GetMapping("notification")
  @PreAuthorize("isAuthenticated()")
  @SneakyThrows
  public Page<Notification> getNotifications(
      @RequestParam(defaultValue = "false") Boolean unreadOnly, Pageable pageable) {

    final var userId = JwtHelper.getUserId();
    return notificationService.getNotifications(userId, unreadOnly, pageable);
  }
}
