package com.gregory.gregoryservice.bizmodel.repository;

import com.gregory.gregoryservice.bizmodel.model.Notification;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends BaseRepository<Notification, Long> {

}