package com.anotherbot.FPSBackend.repositories;

import com.anotherbot.FPSBackend.entites.ApplicationOperation;
import com.anotherbot.FPSBackend.entites.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {
}
