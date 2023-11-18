package com.anotherbot.FPSBackend.repositories;

import com.anotherbot.FPSBackend.entites.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
}
