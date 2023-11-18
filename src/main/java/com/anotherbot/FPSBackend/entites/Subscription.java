package com.anotherbot.FPSBackend.entites;

import com.anotherbot.FPSBackend.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Subscription {
    @Id
    private String id;
    private Date createAt;
    private Date expirationDate;
    @Enumerated(EnumType.STRING)
    private SubscriptionType types;
    //OneToMany et ManyToOne il s'agit de la même étrangère c'est pour cette raison qu'il faut faire mapped by
    @ManyToOne // une Subscription concenrne un Subscriber + Subscription pour un seul Subscriber
    private Subscriber subscriber;
    //private List<ApplicationOperation> subscriptionOperations;
}
