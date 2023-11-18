package com.anotherbot.FPSBackend.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Individual")
@Data  @NoArgsConstructor @AllArgsConstructor
public class Individual extends Subscriber{
    private String customerFirstName;
    private String customerLastName;
    private int numOfCopies;
}
