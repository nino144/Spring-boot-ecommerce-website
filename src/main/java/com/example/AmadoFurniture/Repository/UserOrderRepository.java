package com.example.AmadoFurniture.Repository;

import com.example.AmadoFurniture.model.UserOrder;
import com.example.AmadoFurniture.model.Users;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {

    @Query("SELECT i FROM UserOrder i WHERE i.users = :users")
    public Page<UserOrder> findAllOrderByUser(@Param("users") Users users, Pageable pageable);

    @Query("SELECT i FROM UserOrder i")
    public Page<UserOrder> findAllOrder(Pageable pageable);
    
    @Query("SELECT p FROM UserOrder p WHERE DATE(p.created_date) = :date AND p.users = :users") 
    public Page<UserOrder> findOrderByDate(@Param("date") Date date, Pageable pageable, @Param("users") Users users);

}
