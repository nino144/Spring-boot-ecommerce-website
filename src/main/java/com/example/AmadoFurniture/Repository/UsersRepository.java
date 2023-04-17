package com.example.AmadoFurniture.Repository;

import com.example.AmadoFurniture.model.Users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT p FROM Users p")
    Page<Users> findAllUsers(Pageable pageable);

    @Query("SELECT p FROM Users p WHERE p.user_name LIKE %:name%")
    Page<Users> findUserByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Users p WHERE p.user_name =:name")
    Users getUserByUsername(@Param("name") String name);

    @Query("SELECT p FROM Users p WHERE p.email =:email")
    Users getUserByEmail(@Param("email") String email);

    @Query("SELECT COUNT(i) > 0 FROM Users i WHERE i.email = :email")
    boolean isAccountExist(@Param("email") String name);

    @Query("SELECT u FROM Users u WHERE u.verificationCode = ?1")
    Users findByVerificationCode(String code);
    
}
