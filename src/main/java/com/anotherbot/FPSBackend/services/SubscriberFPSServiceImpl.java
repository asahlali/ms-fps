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
public class SubscriberFPSServiceImpl implements SubscriberFPSService {
    private SubscriberRepository subscriberRepository;
    private SubscriptionRepository subscriptionRepository;
    private ApplicationFPSMapperImpl dtFpsMapper;
//    private ApplicationRepository applicationRepository;
//    private ApplicationOperationRepository applicationOperationRepository;
//    private CreditCardRepository creditCardRepository;
//


    @Override
    public IndividualSubscriberDTO saveIndividual(IndividualSubscriberDTO individualDTO) {
        log.info("Saving individual subscriber");
        Individual individual = dtFpsMapper.fromIndividualSubscriberDTO(individualDTO);
        subscriberRepository.save(individual);
        return dtFpsMapper.fromIndividualSubscriber(individual);
    }

    @Override
    public CorporateSubscriberDTO saveCorporate(CorporateSubscriberDTO corporateDTO) {
        log.info("Saving individual subscriber");
        Corporate subscriber = dtFpsMapper.fromCorporateSubscriberDTO(corporateDTO);
        subscriberRepository.save(subscriber);
        return dtFpsMapper.fromCorporateSubscriber(subscriber);
    }

    @Override
    public CorporateSubscriberDTO updateCorporate(CorporateSubscriberDTO corporateSubscriberDTO) {
        log.info("Update individual Corporate");
        Corporate corporate = dtFpsMapper.fromCorporateSubscriberDTO(corporateSubscriberDTO);
        Corporate savedCorporate = subscriberRepository.save(corporate);
        return dtFpsMapper.fromCorporateSubscriber(savedCorporate);
    }

    @Override
    public IndividualSubscriberDTO updateIndividual(IndividualSubscriberDTO individualSubscriberDTO) {
        log.info("Update individual subscriber");
        Individual individual = dtFpsMapper.fromIndividualSubscriberDTO(individualSubscriberDTO);
        Individual savedIndividual = subscriberRepository.save(individual);
        return dtFpsMapper.fromIndividualSubscriber(savedIndividual);
    }

    @Override
    public Subscriber saveSubscriber(Subscriber subscriber) {
        log.info("Saving individual subscriber");
        Corporate cop = (Corporate) subscriber;//dtFpsMapper.fromSubscriberDTO(subscriberDTO);
        log.info("==>"+cop);
        log.info("==>"+(cop instanceof Corporate));
        Subscriber save = subscriberRepository.save(subscriber);
        return save;//dtFpsMapper.fromCorporateSubscriber(subscriber);
//        if (subscriber instanceof Individual  individual){
//            return dtFpsMapper.fromIndividualSubscriber(individual);
//        } else if (subscriber instanceof  Corporate corporate) {
//            //Corporate subscriber = dtFpsMapper.fromCorporateSubscriber(corporate);
//            Corporate savedCoporate = subscriberRepository.save(corporate);
//            return savedCoporate;//dtFpsMapper.fromCorporateSubscriber(corporate);
//        }else {
//            return null;
//        }
    }

    @Override
    public SubscriberDTO updateIndividual(SubscriberDTO subscriberDTO) {
        log.info("Update individual subscriber");
        Subscriber subscriber = dtFpsMapper.fromSubscriberDTO(subscriberDTO);
        Subscriber savedSubscriber = subscriberRepository.save(subscriber);
        return dtFpsMapper.fromSubscriber(savedSubscriber);
    }
    @Override
    public void deleteSubscriber(Long subscriberId){
        subscriberRepository.deleteById(subscriberId);
    }

    @Override
    public SubscriberDTO getSubscriberById(Long subscriberId) throws SubscriberNotFoundException {
        Subscriber subscriber = subscriberRepository.findById(subscriberId)
                .orElseThrow(() -> new SubscriberNotFoundException("Subscriber not Found"));
        if (subscriber instanceof Individual  individual){
            return dtFpsMapper.fromIndividualSubscriber(individual);
        } else if (subscriber instanceof  Corporate corporate) {
            return dtFpsMapper.fromCorporateSubscriber(corporate);
        }else {
            return null;
        }
    }


    @Override
    public CreditCard saveCreditCard(String types, String cardNum, Date cardExpiry, String cardHolderName, Long subscriberId) {
        return null;
    }



    @Override
    public List<SubscriberDTO> listSubscribers() {
        List<Subscriber> subscribers = subscriberRepository.findAll();
        List<SubscriberDTO> subscriberDTOS = subscribers.stream().map(subscriber -> {
            if (subscriber instanceof Individual individual) {
                return dtFpsMapper.fromIndividualSubscriber(individual);
            } else if (subscriber instanceof Corporate corporate) {
                return dtFpsMapper.fromCorporateSubscriber(corporate);
            } else {
                return null;
            }
        }).collect(Collectors.toList());
//        List<SubscriberDTO> subscriberDTOS = subscribers.stream()
//                .map(subscriber -> dtFpsMapper.fromSubscriber(subscriber))
//                .collect(Collectors.toList());
        return subscriberDTOS;
    }

    @Override
    public Subscription getSubscription(String subscriptionId) throws SubscriptionNotFoundException {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription Not Found"));
        return subscription;
    }

    @Override
    public List<SubscriptionDTO> SubscriptionList(){
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        List<SubscriptionDTO> subscriptionDTOList = subscriptions.stream().map(subscription -> dtFpsMapper.fromSubscription(subscription)).collect(Collectors.toList());
        return subscriptionDTOList;
    }


    //Operation OCR Barcode 50:20 part2

}
