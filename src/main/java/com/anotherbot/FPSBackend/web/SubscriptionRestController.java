package com.anotherbot.FPSBackend.web;

import com.anotherbot.FPSBackend.dtos.IndividualSubscriberDTO;
import com.anotherbot.FPSBackend.dtos.SubscriberDTO;
import com.anotherbot.FPSBackend.dtos.SubscriptionDTO;
import com.anotherbot.FPSBackend.entites.Subscription;
import com.anotherbot.FPSBackend.exceptions.SubscriptionNotFoundException;
import com.anotherbot.FPSBackend.services.SubscriptionFPSService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Subscription")
@Slf4j
public class SubscriptionRestController {
    private SubscriptionFPSService subscriptionFPSService;

    public SubscriptionRestController(SubscriptionFPSService subscriptionFPSService) {
        this.subscriptionFPSService = subscriptionFPSService;
    }
    @GetMapping("/subscriptions")
    public List<SubscriptionDTO> subscriptions(){
        return subscriptionFPSService.listSubscriptions();
    }

//    @GetMapping("/subscriptions/{subscriptionId}")
//    public SubscriptionDTO getSubscriptions(@PathVariable String subscriptionId) throws SubscriptionNotFoundException {
//        return subscriptionFPSService.getSubscriptionsById(subscriptionId);
//    }
    //public Subscription
}
