package com.nighthawk.spring_portfolio.fibo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FiboJpaRepository extends JpaRepository<FiboEntity, Long> {
    // You can add custom query methods here if needed
}
