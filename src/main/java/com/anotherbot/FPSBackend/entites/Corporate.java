package com.anotherbot.FPSBackend.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Corporate")
@Data @NoArgsConstructor @AllArgsConstructor
public class Corporate extends Subscriber{
    private String firmName;
    private int numOfCopies;
}
