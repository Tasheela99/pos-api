package com.pos.posapi.repo;

import com.pos.posapi.enity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface StockRepo extends JpaRepository<Stock,Integer> {
}
