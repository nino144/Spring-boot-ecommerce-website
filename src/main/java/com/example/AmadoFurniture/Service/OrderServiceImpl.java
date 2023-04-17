package com.example.AmadoFurniture.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.AmadoFurniture.Repository.UserOrderRepository;
import com.example.AmadoFurniture.Repository.OrderDetailRepository;
import com.example.AmadoFurniture.model.OrderDetail;
import com.example.AmadoFurniture.model.UserOrder;
import com.example.AmadoFurniture.model.Users;

public class OrderServiceImpl implements OrderService{

    @Autowired
    private UserOrderRepository UserOrderRepository;

    @Autowired
    private OrderDetailRepository OrderDetailRepository;

    @Override
    public void saveOrder(UserOrder order) {
        UserOrderRepository.save(order);
    }

    @Override
    public void saveOrderDetail(List<OrderDetail> detail) {
        OrderDetailRepository.saveAll(detail);
    }

    @Override
    public Page<UserOrder> findAllOrderByUser(int currentPage, int size, String sortField, 
                                              String sortDirection, Users user) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() :Sort.by(sortField).descending();  
                    
        Pageable pageable = PageRequest.of(currentPage, size, sort);
        
        return UserOrderRepository.findAllOrderByUser(user, pageable);
    }

    @Override
    public Page<OrderDetail> findAllDetailByOrder(int currentPage, int size, String sortField, 
                                                       String sortDirection, int orderId) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() :Sort.by(sortField).descending();
                    
        Pageable pageable = PageRequest.of(currentPage, size, sort);                                                
       
        return OrderDetailRepository.findAllDetailByOrder(orderId, pageable);
    }

    @Override
    public Page<UserOrder> findOrderByDate(int currentPage, int size, String sortField, 
                                                       String sortDirection, Date date, Users user) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() :Sort.by(sortField).descending();
                    
        Pageable pageable = PageRequest.of(currentPage, size, sort);                                                
       
        return UserOrderRepository.findOrderByDate(date, pageable, user);
    }

    @Override
    public UserOrder findOrderById(int id) {
        Optional<UserOrder> OrderOptional = UserOrderRepository.findById(id);
        if (OrderOptional.isPresent()) {
            return OrderOptional.get();
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public Page<OrderDetail> findOrderDetailByName(int currentPage, int size, String sortField, 
                                                    String sortDirection, String name) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() :Sort.by(sortField).descending();
                
        Pageable pageable = PageRequest.of(currentPage, size, sort); 

        return OrderDetailRepository.findOrderDetailByName(name, pageable);
        
    }

    @Override
    public Page<UserOrder> findAllOrder(int currentPage, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
        Sort.by(sortField).ascending() :Sort.by(sortField).descending();  
        
        Pageable pageable = PageRequest.of(currentPage, size, sort);

        return UserOrderRepository.findAllOrder(pageable);
    }

    @Override
    public void updateOrderStatus(int orderId) {
        Optional<UserOrder> optionalOrder = UserOrderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            UserOrder order = optionalOrder.get();
            order.setStatus("COMPLETED");
            UserOrderRepository.save(order);
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    
    
}
