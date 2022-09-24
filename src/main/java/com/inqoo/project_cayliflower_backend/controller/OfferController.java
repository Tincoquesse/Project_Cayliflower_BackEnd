package com.inqoo.project_cayliflower_backend.controller;

import com.inqoo.project_cayliflower_backend.model.OfferPreparationRequestDTO;
import com.inqoo.project_cayliflower_backend.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/offer")
    public ResponseEntity<String> saveOffer(@RequestBody OfferPreparationRequestDTO offerPreparationRequestDTO){
        offerService.processOffer(offerPreparationRequestDTO);
        return ResponseEntity.ok().body("Offer send");
    }

}
