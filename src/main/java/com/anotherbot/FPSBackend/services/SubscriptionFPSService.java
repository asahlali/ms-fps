package com.anotherbot.FPSBackend.services;

import com.anotherbot.FPSBackend.dtos.SubscriptionDTO;
import com.anotherbot.FPSBackend.entites.Subscription;
import com.anotherbot.FPSBackend.enums.SubscriptionType;
import com.anotherbot.FPSBackend.exceptions.SubscriberNotFoundException;
import com.anotherbot.FPSBackend.exceptions.SubscriptionNotFoundException;

import java.util.List;

public interface SubscriptionFPSService {

    SubscriptionDTO saveSubscription(SubscriptionType types, Long subscriberId) throws SubscriberNotFoundException;

    List<SubscriptionDTO> listSubscriptions();

    SubscriptionDTO getSubscriptionsById(String subscriptionId) throws SubscriptionNotFoundException;
}
