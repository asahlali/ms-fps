package com.anotherbot.FPSBackend.services;

import com.anotherbot.FPSBackend.dtos.CorporateSubscriberDTO;
import com.anotherbot.FPSBackend.dtos.IndividualSubscriberDTO;
import com.anotherbot.FPSBackend.dtos.SubscriberDTO;
import com.anotherbot.FPSBackend.dtos.SubscriptionDTO;
import com.anotherbot.FPSBackend.entites.*;
import com.anotherbot.FPSBackend.enums.ApplicationType;
import com.anotherbot.FPSBackend.enums.SubscriptionType;
import com.anotherbot.FPSBackend.exceptions.SubscriberNotFoundException;
import com.anotherbot.FPSBackend.exceptions.SubscriptionNotFoundException;

import java.util.Date;
import java.util.List;

public interface SubscriberFPSService {


    IndividualSubscriberDTO saveIndividual(IndividualSubscriberDTO subscriberDTO);
    CorporateSubscriberDTO saveCorporate(CorporateSubscriberDTO corporateDTO);

    Subscriber saveSubscriber(Subscriber subscriber);

    SubscriberDTO updateIndividual(SubscriberDTO subscriberDTO);

    void deleteSubscriber(Long subscriberId);

    SubscriberDTO getSubscriberById(Long subscriberId) throws SubscriberNotFoundException;
    CreditCard saveCreditCard(String types, String cardNum, Date cardExpiry, String cardHolderName, Long subscriberId);
     List<SubscriberDTO> listSubscribers();
    Subscription getSubscription(String subscriptionId) throws SubscriptionNotFoundException;

    List<SubscriptionDTO> SubscriptionList();

    CorporateSubscriberDTO updateCorporate(CorporateSubscriberDTO corporateSubscriberDTO);
    IndividualSubscriberDTO updateIndividual(IndividualSubscriberDTO individualSubscriberDTO);



//    Individual saveIndividual(String subFirstName,
//                              String subLastName,
//                              String contactNumber,
//                              String emailAddress,
//                              String customerFirstName,
//                              String customerLastName,
//                              int numOfCopies);
//
//    Corporate saveCorporate(String subFirstName,
//                            String subLastName,
//                            String contactNumber,
//                            String emailAddress,
//                            String firmName,
//                            int numOfCopies);
}
