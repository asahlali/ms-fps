package com.anotherbot.FPSBackend;

import com.anotherbot.FPSBackend.dtos.CorporateSubscriberDTO;
import com.anotherbot.FPSBackend.dtos.IndividualSubscriberDTO;
import com.anotherbot.FPSBackend.dtos.SubscriberDTO;
import com.anotherbot.FPSBackend.entites.*;
import com.anotherbot.FPSBackend.enums.ApplicationType;
import com.anotherbot.FPSBackend.enums.CreditCardType;
import com.anotherbot.FPSBackend.enums.OperationType;
import com.anotherbot.FPSBackend.enums.SubscriptionType;
import com.anotherbot.FPSBackend.exceptions.SubscriberNotFoundException;
import com.anotherbot.FPSBackend.repositories.*;
import com.anotherbot.FPSBackend.services.ApplicationFPSService;
import com.anotherbot.FPSBackend.services.SubscriberFPSService;
import com.anotherbot.FPSBackend.services.SubscriptionFPSService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class FpsBackendApplication {


	public static void main(String[] args) {
		SpringApplication.run(FpsBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(SubscriberFPSService applicationService,
							ApplicationFPSService applicationFPSService,
							SubscriptionFPSService subscriptionFPSService){
		return args -> {
			Stream.of("Meryem","Ilham", "Soukaina", "Abdellatif").forEach(
					subscriberName -> {
						IndividualSubscriberDTO individualSubscriberDTO = new IndividualSubscriberDTO();
						individualSubscriberDTO.setSubFirstName("subFN" + new Random().nextInt() * 10);
						individualSubscriberDTO.setSubLastName("subLN" + new Random().nextInt() * 10);
						individualSubscriberDTO.setContactNumber("+212 00000000000");
						individualSubscriberDTO.setEmailAddress(subscriberName + "@Another-bot.com");
						individualSubscriberDTO.setCustomerFirstName(subscriberName);
						individualSubscriberDTO.setCustomerLastName("SAHLALI");
						individualSubscriberDTO.setNumOfCopies(new Random().nextInt() * 1000);
						applicationService.saveIndividual(individualSubscriberDTO);
					});

			Stream.of("BEDIER","REOLIAN", "ANOTHERBOT").forEach(subscriberName ->{
				CorporateSubscriberDTO corporateSubscriberDTO= new CorporateSubscriberDTO();
				corporateSubscriberDTO.setSubFirstName("subFN"+new Random().nextInt()*10);
				corporateSubscriberDTO.setSubLastName("subLN"+new Random().nextInt()*10);
				corporateSubscriberDTO.setContactNumber("+33 00000000000");
				corporateSubscriberDTO.setEmailAddress(subscriberName+"@"+subscriberName.toLowerCase()+".com");
				corporateSubscriberDTO.setFirmName(subscriberName);
				corporateSubscriberDTO.setNumOfCopies(new Random().nextInt()*3000);
				applicationService.saveCorporate(corporateSubscriberDTO);
			});
					applicationService.listSubscribers().forEach(subscriber->{
					SubscriptionType[]subscriptionTypes=SubscriptionType.values();
					SubscriptionType subscriptionType = subscriptionTypes[new Random().nextInt(subscriptionTypes.length)];
						try {
							subscriptionFPSService.saveSubscription(subscriptionType,subscriber.getSubID());
						} catch (SubscriberNotFoundException e) {
							e.printStackTrace();
						}
					});

			applicationService.listSubscribers().forEach(subscriber -> {
				ApplicationType[] applicationTypes=ApplicationType.values();
				ApplicationType applicationType = applicationTypes[new Random().nextInt(applicationTypes.length)];
				try {
					applicationFPSService.saveApplication(applicationType,"----",subscriber.getSubID());
				} catch (SubscriberNotFoundException e) {
					e.printStackTrace();
				}
			});
		};
	}
	//@Bean
	CommandLineRunner start(SubscriberRepository subscriberRepository,
							SubscriptionRepository subscriptionRepository,
							ApplicationRepository  applicationRepository,
							ApplicationOperationRepository applicationOperationRepository,
							CreditCardRepository creditCardRepository){
		return args -> {
			Stream.of("Meryem","Ilham", "Soukaina", "Abdellatif").forEach(subscriberName ->{
				Individual subscriber= new Individual();
				subscriber.setSubFirstName("subFN"+new Random().nextInt()*10);
				subscriber.setSubLastName("subLN"+new Random().nextInt()*10);
				subscriber.setContactNumber("+212 00000000000");
				subscriber.setEmailAddress(subscriberName+"@Another-bot.com");
				subscriber.setCustomerFirstName(subscriberName);
				subscriber.setCustomerLastName("SAHLALI");
				subscriber.setNumOfCopies(new Random().nextInt()*1000);
				subscriberRepository.save(subscriber);

			});
			Stream.of("BEDIER","REOLIAN", "ANOTHERBOT").forEach(subscriberName ->{
				Corporate subscriber= new Corporate();
				subscriber.setSubFirstName("subFN"+new Random().nextInt()*10);
				subscriber.setSubLastName("subLN"+new Random().nextInt()*10);
				subscriber.setContactNumber("+33 00000000000");
				subscriber.setEmailAddress(subscriberName+"@"+subscriberName.toLowerCase()+".com");
				subscriber.setFirmName(subscriberName);
				subscriber.setNumOfCopies(new Random().nextInt()*3000);
				subscriberRepository.save(subscriber);
			});

			subscriberRepository.findAll().forEach(subscri->{
				SubscriptionType[]subscriptionTypes=SubscriptionType.values();
				Subscription subscription = new Subscription();
				subscription.setId(UUID.randomUUID().toString());
				subscription.setCreateAt(new Date());
				subscription.setExpirationDate(DateUtils.addMonths(new Date(), 1));
				subscription.setTypes(subscriptionTypes[new Random().nextInt(subscriptionTypes.length)]);
				subscription.setSubscriber(subscri);
				subscriptionRepository.save(subscription);
			});


			subscriberRepository.findAll().forEach(subscriber -> {
				ApplicationType[] applicationTypes=ApplicationType.values();
				Application application = new Application();
				application.setDateOfApplication(new Date());
				application.setTypes(applicationTypes[new Random().nextInt(applicationTypes.length)]);
				application.setSubscriber(subscriber);
				applicationRepository.save(application);
			});

			applicationRepository.findAll().forEach(application -> {
				OperationType[] operationTypes= OperationType.values();
				for (int i = 0; i < 20; i++) {
					ApplicationOperation applicationOperation= new ApplicationOperation();
					applicationOperation.setOperationDate(new Date());
					applicationOperation.setAmount(Math.random()*12);
					applicationOperation.setType(operationTypes[new Random().nextInt(operationTypes.length)]);
					applicationOperation.setApplication(application);
					applicationOperationRepository.save(applicationOperation);
				}

			subscriberRepository.findAll().forEach(subscriber -> {
				CreditCardType[] creditCardTypes= CreditCardType.values();
				CreditCard creditCard = new CreditCard();
				creditCard.setCardType(creditCardTypes[new Random().nextInt(creditCardTypes.length)]);
				creditCard.setCardNum(UUID.randomUUID().toString());
				creditCard.setCardExpiry(DateUtils.addMonths(new Date(), 1));
				creditCard.setCardHolderName(subscriber.getSubFirstName());
				creditCard.setCvv("755");
				creditCard.setSubscriber(subscriber);
				creditCardRepository.save(creditCard);
			});
			});

			Subscriber subscriber = subscriberRepository.findById(2L).orElse(null);
			System.out.println("**********************");
			System.out.println(subscriber.getEmailAddress());
			System.out.println(subscriber.getClass().getSimpleName());
			if(subscriber instanceof Corporate){
				System.out.println(((Corporate) subscriber).getFirmName());
			}else if(subscriber instanceof Individual individual) {
				System.out.println("Individual=>"+individual.getCustomerFirstName());
			}
			subscriber.getSubscriptions().forEach(subscription -> {
				System.out.println("========================");
				System.out.println(subscription.getTypes());
				System.out.println(subscription.getExpirationDate());
				System.out.println(subscription.getCreateAt());
			});
		};
	}

}
