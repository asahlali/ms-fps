package com.anotherbot.FPSBackend.entites;

import com.anotherbot.FPSBackend.enums.ApplicationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOfApplication;
    @Enumerated(EnumType.STRING)
    private ApplicationType types;
    @ManyToOne
    private Subscriber subscriber;
    @OneToMany(mappedBy = "application")
    private List<ApplicationOperation> subscriptionOperations;
}
