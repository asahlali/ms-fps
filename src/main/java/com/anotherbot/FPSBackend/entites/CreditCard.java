package com.anotherbot.FPSBackend.entites;

import com.anotherbot.FPSBackend.enums.CreditCardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class CreditCard {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Enumerated(EnumType.STRING)
    private CreditCardType cardType;
    private String cardNum;
    private Date cardExpiry;
    private String cardHolderName;
    private String cvv;
    @ManyToOne
    private Subscriber subscriber;
}
