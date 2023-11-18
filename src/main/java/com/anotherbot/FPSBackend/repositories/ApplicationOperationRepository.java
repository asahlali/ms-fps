package com.anotherbot.FPSBackend.repositories;

import com.anotherbot.FPSBackend.entites.ApplicationOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationOperationRepository extends JpaRepository<ApplicationOperation,Long> {
}
