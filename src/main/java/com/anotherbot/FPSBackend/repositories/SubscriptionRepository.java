package com.anotherbot.FPSBackend.repositories;

import com.anotherbot.FPSBackend.entites.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,String> {
}
