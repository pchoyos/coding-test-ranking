package com.idealista.infrastructure.api.services;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.model.vo.AdVO;

import java.util.List;

public interface AdService {

    void calculateQuality();

    List<AdVO> getQualityListing();

    List<PublicAd> getPublicListing();
}
