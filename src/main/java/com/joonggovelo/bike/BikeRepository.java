package com.joonggovelo.bike;

import com.joonggovelo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Integer> {
}
