package com.example.AmadoFurniture.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.AmadoFurniture.model.OrderDetail;
import com.example.AmadoFurniture.model.UserOrder;
import com.example.AmadoFurniture.model.Users;

public interface OrderService {
    public UserOrder findOrderById(int id);
    public void saveOrder(UserOrder order);
    public void saveOrderDetail(List<OrderDetail> detail);
    public Page<UserOrder> findAllOrderByUser(int currentPage, int size, String sortField, 
                                              String sortDirection, Users user);

    public Page<OrderDetail> findAllDetailByOrder(int currentPage, int size, String sortField, 
                                                  String sortDirection, int orderId);
                                                       
    public Page<UserOrder> findOrderByDate(int currentPage, int size, String sortField, 
                                           String sortDirection, Date date, Users user);  
                                                       
    public Page<OrderDetail> findOrderDetailByName(int currentPage, int size, String sortField, 
                                                   String sortDirection, String name);


    public Page<UserOrder> findAllOrder(int currentPage, int size, String sortField, 
                                                   String sortDirection);

    public void updateOrderStatus(int orderId);                                              
}
