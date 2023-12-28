package com.ecommerce.OrderAandNotificationsManagement.repository;

import com.ecommerce.OrderAandNotificationsManagement.entity.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTempleteRepository extends JpaRepository<NotificationTemplate,Integer> {
}
