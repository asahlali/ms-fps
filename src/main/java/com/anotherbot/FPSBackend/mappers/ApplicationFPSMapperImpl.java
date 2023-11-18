package com.anotherbot.FPSBackend.mappers;

import com.anotherbot.FPSBackend.dtos.CorporateSubscriberDTO;
import com.anotherbot.FPSBackend.dtos.IndividualSubscriberDTO;
import com.anotherbot.FPSBackend.dtos.SubscriberDTO;
import com.anotherbot.FPSBackend.dtos.SubscriptionDTO;
import com.anotherbot.FPSBackend.entites.Corporate;
import com.anotherbot.FPSBackend.entites.Individual;
import com.anotherbot.FPSBackend.entites.Subscriber;
import com.anotherbot.FPSBackend.entites.Subscription;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ApplicationFPSMapperImpl {

    public SubscriberDTO fromSubscriber(Subscriber subscriber){
        SubscriberDTO subscriberDTO=new SubscriberDTO();
        BeanUtils.copyProperties(subscriber,subscriberDTO);
        return subscriberDTO;
    }
    public Subscriber fromSubscriberDTO(SubscriberDTO subscriberDTO){
        Subscriber subscriber=new Subscriber();
        BeanUtils.copyProperties(subscriberDTO,subscriber);
        return subscriber;
    }


    public SubscriptionDTO fromSubscription(Subscription subscription){
        //trnasfert les attributs
        SubscriptionDTO subscriptionDTO=new SubscriptionDTO();
        BeanUtils.copyProperties(subscription,subscriptionDTO);
        subscriptionDTO.setSubscriberDTO(fromSubscriber(subscription.getSubscriber()));
        return subscriptionDTO;
    }
    public Subscription fromSubscriptionDTO(SubscriptionDTO subscriptionDTO){
        Subscription subscription=new Subscription();
        BeanUtils.copyProperties(subscriptionDTO,subscription);
        subscription.setSubscriber(fromSubscriberDTO(subscriptionDTO.getSubscriberDTO()));
        return subscription;
    }

    public IndividualSubscriberDTO fromIndividualSubscriber(Individual individual){
        IndividualSubscriberDTO individualSubscriberDTO=new IndividualSubscriberDTO();
        BeanUtils.copyProperties(individual,individualSubscriberDTO);
        individualSubscriberDTO.setType(individual.getClass().getSimpleName());
        return individualSubscriberDTO;
    }
    public Individual fromIndividualSubscriberDTO(IndividualSubscriberDTO individualSubscriberDTO){
        Individual individual=new Individual();
        BeanUtils.copyProperties(individualSubscriberDTO,individual);
        return individual;
    }


    public CorporateSubscriberDTO fromCorporateSubscriber(Subscriber corporate){
        CorporateSubscriberDTO corporateSubscriberDTO=new CorporateSubscriberDTO();
        BeanUtils.copyProperties(corporate,corporateSubscriberDTO);
        corporateSubscriberDTO.setType(corporate.getClass().getSimpleName());
        return corporateSubscriberDTO;
    }
    public Corporate fromCorporateSubscriberDTO(CorporateSubscriberDTO corporateSubscriberDTO){
        Corporate corporate=new Corporate();
        BeanUtils.copyProperties(corporateSubscriberDTO,corporate);
        return corporate;
    }
}
