package com.anotherbot.FPSBackend.repositories;

import com.anotherbot.FPSBackend.entites.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber,Long> {
}
