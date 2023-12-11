package com.nighthawk.spring_portfolio.fibo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FiboJpaRepository extends JpaRepository<Jokes, Long> {

    void save(String Joke);

    List<Jokes> findAllByOrderByJokeAsc();

    List<Jokes> findByJokeIgnoreCase(String joke);
}
