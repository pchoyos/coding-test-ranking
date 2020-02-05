package com.idealista.infrastructure.api.converter;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.model.entity.Ad;
import com.idealista.infrastructure.api.model.entity.Picture;
import com.idealista.infrastructure.api.model.vo.AdVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdConverter {

    public AdVO toVO(Ad ad){

        if (ad==null) { return null; }

        AdVO vo = new AdVO();
        vo.setId(ad.getId());
        vo.setDescription(ad.getDescription());
        vo.setGardenSize(ad.getGardenSize());
        vo.setHouseSize(ad.getHouseSize());
        vo.setScore(ad.getScore());
        vo.setTypology(ad.getTypology());
        vo.setIrrelevantSince(ad.getIrrelevantSince());
        vo.setPictures(ad.getPictures().stream().map(Picture::getId).collect(Collectors.toList()));
        return vo;

    }

    public List<AdVO> toVoList(List<Ad> ads){
        if(ads==null) return null;

        List<AdVO> vo = new ArrayList<>();
        for(Ad ad: ads){
            vo.add(toVO(ad));
        }

        return vo;
    }

    public PublicAd toPublicVO(Ad ad){

        if (ad==null) { return null; }

        PublicAd vo = new PublicAd();
        vo.setId(ad.getId());
        vo.setDescription(ad.getDescription());
        vo.setGardenSize(ad.getGardenSize());
        vo.setHouseSize(ad.getHouseSize());
        vo.setTypology(ad.getTypology());
        return vo;

    }

    public List<PublicAd> toPublicVoList(List<Ad> ads){
        if(ads==null) return null;

        List<PublicAd> vo = new ArrayList<>();
        for(Ad ad: ads){
            vo.add(toPublicVO(ad));
        }

        return vo;
    }

}
