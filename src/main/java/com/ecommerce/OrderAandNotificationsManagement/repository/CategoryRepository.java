package com.ecommerce.OrderAandNotificationsManagement.repository;

import com.ecommerce.OrderAandNotificationsManagement.entity.Category;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("SELECT c, COUNT(p) FROM Category c LEFT JOIN c.products p GROUP BY c")
    List<Object[]> findAllWithProductCount();
}
