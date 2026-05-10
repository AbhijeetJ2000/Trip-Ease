package com.example.tripease.Repository;

import com.example.tripease.Model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface CabRepository extends JpaRepository<Cab, Integer> {
    @Query("SELECT c FROM Cab c where c.available=true order by rand() limit 1")
    Cab getAvailableCabRandomly();

    @Query("SELECT c FROM Cab c WHERE c.available = true AND c.per_km_rate <= :perKmRate")
    List<Cab> findBudgetFriendlyCabs(@Param("perKmRate") double perKmRate);
}


