package com.anotherbot.FPSBackend.dtos;

import com.anotherbot.FPSBackend.entites.Subscriber;
import lombok.Data;

@Data
public class IndividualSubscriberDTO extends SubscriberDTO {
    private Long subID;
    private String subFirstName;
    private String subLastName;
    private String contactNumber;
    private String emailAddress;
    private String customerFirstName;
    private String customerLastName;
    private int numOfCopies;
}
