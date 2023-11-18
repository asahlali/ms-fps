package com.anotherbot.FPSBackend.services;

import com.anotherbot.FPSBackend.dtos.SubscriptionDTO;
import com.anotherbot.FPSBackend.entites.*;
import com.anotherbot.FPSBackend.enums.SubscriptionType;
import com.anotherbot.FPSBackend.exceptions.SubscriberNotFoundException;
import com.anotherbot.FPSBackend.exceptions.SubscriptionNotFoundException;
import com.anotherbot.FPSBackend.mappers.ApplicationFPSMapperImpl;
import com.anotherbot.FPSBackend.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class SubscriptionFPSServiceImpl implements SubscriptionFPSService {
    private SubscriberRepository subscriberRepository;
    private SubscriptionRepository subscriptionRepository;
    private ApplicationRepository applicationRepository;
    private ApplicationOperationRepository applicationOperationRepository;
    private CreditCardRepository creditCardRepository;
    private ApplicationFPSMapperImpl dtFpsMapper;
    @Override
    public SubscriptionDTO saveSubscription(SubscriptionType types, Long subscriberId) throws SubscriberNotFoundException {
        Subscriber subscriber = subscriberRepository.findById(subscriberId).orElse(null);
        if(subscriber==null)
            throw new SubscriberNotFoundException("Subscriber not Found");
        Subscription subscription = new Subscription();
        switch (types){
            case Free -> {log.info("add 50 actions per month "); System.out.println("50 actions per month ");}
            case Standard -> {log.info("add 500 actions per month "); System.out.println("500 actions per month ");}
            case Midsize -> {log.info("add 1200 actions per month "); System.out.println("1200 actions per month ");}
            case Large -> {log.info("add 5000 actions per month "); System.out.println("5000 actions per month ");}
            case Enterprise -> {log.info("add 12000 actions per month "); System.out.println("12000 actions per month ");}
            //case default -> {throw new SubscriptionTypeNotFoundException("SubscriptionType not Found");}
        }
        subscription.setId(UUID.randomUUID().toString());
        subscription.setCreateAt(new Date());
        subscription.setExpirationDate(DateUtils.addMonths(new Date(), 1));
        subscription.setTypes(types);
        subscription.setSubscriber(subscriber);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return dtFpsMapper.fromSubscription(savedSubscription);
    }

    @Override
    public List<SubscriptionDTO> listSubscriptions() {
        List<Subscription> allSubscriptions = subscriptionRepository.findAll();
        List<SubscriptionDTO> subscriptionDTOS = allSubscriptions.stream()
                .map(subscription -> dtFpsMapper.fromSubscription(subscription))
                .collect(Collectors.toList());
        return subscriptionDTOS;
    }

    @Override
    public SubscriptionDTO getSubscriptionsById(String subscriptionId) throws SubscriptionNotFoundException {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription Not Found"));
        return dtFpsMapper.fromSubscription(subscription);
    }

}
