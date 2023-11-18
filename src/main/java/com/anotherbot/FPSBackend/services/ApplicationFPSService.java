package com.anotherbot.FPSBackend.services;

import com.anotherbot.FPSBackend.entites.Application;
import com.anotherbot.FPSBackend.enums.ApplicationType;
import com.anotherbot.FPSBackend.exceptions.SubscriberNotFoundException;

public interface ApplicationFPSService {
    Application saveApplication(ApplicationType types, String cardNum, Long subscriberId) throws SubscriberNotFoundException;

}
