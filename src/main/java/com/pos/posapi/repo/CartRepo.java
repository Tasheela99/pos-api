package com.pos.posapi.repo;

import com.pos.posapi.enity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CartRepo extends JpaRepository<Cart,Integer> {
}
