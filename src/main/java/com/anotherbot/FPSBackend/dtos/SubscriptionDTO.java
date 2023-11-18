package com.anotherbot.FPSBackend.dtos;

import com.anotherbot.FPSBackend.enums.SubscriptionType;
import lombok.Data;

import java.util.Date;
@Data
public class SubscriptionDTO {
    private String id;
    private Date createAt;
    private Date expirationDate;
    private SubscriptionType types;
    private SubscriberDTO subscriberDTO;
}
