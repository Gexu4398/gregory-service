package com.gregory.gregoryservice.bizservice.controller;

import com.gregory.gregoryservice.bizmodel.model.BizLog;
import com.gregory.gregoryservice.bizservice.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Calendar;
import java.util.Set;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "日志管理")
@RestController
@RequestMapping("log")
public class LogController {

  private final LogService logService;

  @Autowired
  public LogController(LogService logService) {

    this.logService = logService;
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  @Operation(summary = "展示日志")
  @SneakyThrows
  public Page<BizLog> getLogs(Pageable pageable,
      @RequestParam(required = false) String type,
      @RequestParam(required = false) String ip,
      @RequestParam(required = false) String username,
      @RequestParam(required = false) String q,
      @RequestParam(name = "module", required = false) Set<String> modules,
      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar fromDate,
      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar toDate) {

    return logService.getLogs(q, fromDate, toDate, type, ip, username, modules, pageable);
  }
}
