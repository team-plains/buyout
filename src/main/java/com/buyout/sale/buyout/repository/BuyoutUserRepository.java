package com.buyout.sale.buyout.repository;

import com.buyout.sale.buyout.models.BuyoutUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyoutUserRepository extends JpaRepository<BuyoutUser, Long> {
    public BuyoutUser findByUsername(String username);
}
