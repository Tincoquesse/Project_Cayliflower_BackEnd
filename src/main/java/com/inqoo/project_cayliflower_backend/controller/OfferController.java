package com.inqoo.project_cayliflower_backend.controller;

import com.inqoo.project_cayliflower_backend.model.OfferPreparationRequestDTO;
import com.inqoo.project_cayliflower_backend.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/offer")
    public ResponseEntity<Void> saveOffer(@RequestBody OfferPreparationRequestDTO offerPreparationRequestDTO) throws MessagingException, UnsupportedEncodingException {
        offerService.processOffer(offerPreparationRequestDTO);
        return ResponseEntity.ok().build();
    }

}
