package com.anotherbot.FPSBackend.dtos;

import lombok.Data;


@Data
public class SubscriberDTO {
    private Long subID;
    private String type;
    private String subFirstName;
    private String subLastName;
    private String contactNumber;
    private String emailAddress;
}
