package com.idealista.infrastructure.api.controller;

import com.idealista.infrastructure.api.services.AdService;
import com.idealista.infrastructure.utils.CustomResponse;
import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.utils.ResponseCode;
import com.idealista.infrastructure.utils.ResponseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdsController {

    @Autowired private AdService adService;
    @Autowired private ResponseManager responseManager;

    @GetMapping("/quality-listing")
    public @ResponseBody CustomResponse qualityListing() {
        return this.responseManager.response(this.adService.getQualityListing());
    }

    @GetMapping("/public-listing")
    public  @ResponseBody CustomResponse publicListing() {
        List<PublicAd> result = this.adService.getPublicListing();
        if(result == null) {
            return  this.responseManager.response(ResponseCode.ILLEGAL_ARGUMENT_EXCEPTION);
        }else {
            return this.responseManager.response(result);
        }
    }

    @PostMapping("/calculate")
    public  @ResponseBody CustomResponse<Void> calculateScore() {
        this.adService.calculateQuality();
        return this.responseManager.response(ResponseCode.OK_CODE);
    }
}
