package com.anotherbot.FPSBackend.web;

import com.anotherbot.FPSBackend.dtos.CorporateSubscriberDTO;
import com.anotherbot.FPSBackend.dtos.IndividualSubscriberDTO;
import com.anotherbot.FPSBackend.dtos.SubscriberDTO;
import com.anotherbot.FPSBackend.exceptions.SubscriberNotFoundException;
import com.anotherbot.FPSBackend.services.SubscriberFPSService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Subscriber")
@AllArgsConstructor
@Slf4j
public class SubscriberRestController {
    private SubscriberFPSService subscriberFPSService;

    @GetMapping("/subscribers")
    public List<SubscriberDTO> subscribers(){
        return subscriberFPSService.listSubscribers();
    }
    @GetMapping("/subscribers/{subscriberId}")
    public SubscriberDTO getSubscriber(@PathVariable Long subscriberId) throws SubscriberNotFoundException {
        return subscriberFPSService.getSubscriberById(subscriberId);
    }


    @DeleteMapping("/subscribers/{subscriberId}")
    public void deleteIndividual(@PathVariable Long subscriberId){
        subscriberFPSService.deleteSubscriber(subscriberId);
    }


//    @PostMapping("/subscribers")
//    public Subscriber SaveSubscriber(@RequestBody Subscriber subscriber){
//        return subscriberFPSService.saveSubscriber(subscriber);
//    }
    @PostMapping("/individual")
    public IndividualSubscriberDTO SaveIndividual(@RequestBody IndividualSubscriberDTO individualSubscriberDTO){
        return subscriberFPSService.saveIndividual(individualSubscriberDTO);
    }
    @PostMapping("/corporate")
    public CorporateSubscriberDTO SaveCorporate(@RequestBody CorporateSubscriberDTO corporateSubscriberDTO){
        return subscriberFPSService.saveCorporate(corporateSubscriberDTO);
    }

    @PutMapping("/individual/{subscriberId}")
    public IndividualSubscriberDTO updateIndividual(@RequestBody IndividualSubscriberDTO individualSubscriberDTO,@PathVariable Long subscriberId){
        individualSubscriberDTO.setSubID(subscriberId);
        return subscriberFPSService.updateIndividual(individualSubscriberDTO);
    }
    @PutMapping("/corporate/{subscriberId}")
    public CorporateSubscriberDTO updateCorporate(@RequestBody CorporateSubscriberDTO corporateSubscriberDTO,@PathVariable Long subscriberId){
        corporateSubscriberDTO.setSubID(subscriberId);
        return subscriberFPSService.updateCorporate(corporateSubscriberDTO);
    }



}
