package com.ecommerce.OrderAandNotificationsManagement.repository;

import com.ecommerce.OrderAandNotificationsManagement.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    List<Notification> findByIsSentFalse();
    List<Notification>findByIsSentTrue();
    @Query("SELECT n.customer.email FROM Notification n GROUP BY n.customer.email ORDER BY COUNT(n) DESC")
    Optional<List<String>> findMostNotifiedEmail();
    @Query("SELECT n.customer.phoneNumber FROM Notification n GROUP BY n.customer.phoneNumber ORDER BY COUNT(n) DESC")
    Optional<List<String>> findMostNotifiedPhoneNumber();

    @Query("SELECT n.message FROM Notification n GROUP BY n.message ORDER BY COUNT(n) DESC")
    Optional<List<String>> findMostUsedMessage();

}
