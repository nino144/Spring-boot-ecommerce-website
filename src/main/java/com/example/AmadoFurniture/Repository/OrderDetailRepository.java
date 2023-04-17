package com.example.AmadoFurniture.Repository;

import com.example.AmadoFurniture.model.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query("SELECT i FROM OrderDetail i WHERE i.order.order_id = :orderId")
    public Page<OrderDetail> findAllDetailByOrder(@Param("orderId") int orderId, Pageable Pageable);

    @Query("SELECT p FROM OrderDetail p WHERE p.item_name LIKE %:name%")
    public Page<OrderDetail> findOrderDetailByName(@Param("name") String name, Pageable pageable);
}
