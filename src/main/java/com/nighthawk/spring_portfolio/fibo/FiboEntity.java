package com.nighthawk.spring_portfolio.fibo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FiboEntity {
    @Id
    private Long id;

    // Add other fields and methods as needed

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
