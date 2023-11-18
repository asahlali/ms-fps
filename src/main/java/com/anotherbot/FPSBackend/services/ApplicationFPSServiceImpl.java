package com.anotherbot.FPSBackend.services;

import com.anotherbot.FPSBackend.dtos.CorporateSubscriberDTO;
import com.anotherbot.FPSBackend.dtos.IndividualSubscriberDTO;
import com.anotherbot.FPSBackend.dtos.SubscriberDTO;
import com.anotherbot.FPSBackend.dtos.SubscriptionDTO;
import com.anotherbot.FPSBackend.entites.*;
import com.anotherbot.FPSBackend.enums.ApplicationType;
import com.anotherbot.FPSBackend.exceptions.SubscriberNotFoundException;
import com.anotherbot.FPSBackend.exceptions.SubscriptionNotFoundException;
import com.anotherbot.FPSBackend.mappers.ApplicationFPSMapperImpl;
import com.anotherbot.FPSBackend.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ApplicationFPSServiceImpl implements ApplicationFPSService {
    private SubscriberRepository subscriberRepository;
    private SubscriptionRepository subscriptionRepository;
    private ApplicationFPSMapperImpl dtFpsMapper;
    private ApplicationRepository applicationRepository;
    private ApplicationOperationRepository applicationOperationRepository;
    private CreditCardRepository creditCardRepository;

    @Override
    public Application saveApplication(ApplicationType types, String cardNum, Long subscriberId) throws SubscriberNotFoundException {
        Subscriber subscriber = subscriberRepository.findById(subscriberId).orElse(null);
        if(subscriber==null)
            throw new SubscriberNotFoundException("Subscriber not Found");
        Application application = new Application();
        application.setDateOfApplication(new Date());
        application.setSubscriber(subscriber);
        application.setTypes(types);
        Application savedApplication = applicationRepository.save(application);
        return savedApplication;
    }

}
