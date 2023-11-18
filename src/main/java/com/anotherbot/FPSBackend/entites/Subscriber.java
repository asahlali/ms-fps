package com.anotherbot.FPSBackend.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type", length = 10)
@Data @NoArgsConstructor @AllArgsConstructor
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subID;
    private String subFirstName;
    private String subLastName;
    private String contactNumber;
    private String emailAddress;
    @OneToMany(mappedBy = "subscriber", fetch = FetchType.EAGER)//va regarder l'autre classe il y a un attribue qui s'appell subscriber
    private List<Subscription> subscriptions;
    @OneToMany(mappedBy = "subscriber")
    private List<CreditCard> creditCards;
    @OneToMany(mappedBy = "subscriber")
    private List<Application> applications;
}
