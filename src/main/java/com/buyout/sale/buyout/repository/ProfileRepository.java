package com.buyout.sale.buyout.repository;

import com.buyout.sale.buyout.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    public Profile findByEmail(String email);
}
