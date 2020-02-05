package com.idealista.infrastructure.api.services;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.converter.AdConverter;
import com.idealista.infrastructure.api.model.entity.Ad;
import com.idealista.infrastructure.api.model.vo.AdVO;
import com.idealista.infrastructure.api.repository.AdRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdServiceImpl implements AdService {

    @Autowired AdRepository adRepository;
    @Autowired ModelMapper modelMapper;
    @Autowired AdConverter adConverter;

    @Override
    public void calculateQuality() {

        this.adRepository.findAll();

        // obtenemos todos los anuncios
        List<Ad> qualityAds = this.adRepository.findAll().stream().map(ad -> modelMapper.map(ad, Ad.class)).collect(Collectors.toList());

        qualityAds.stream().forEach(ad ->{
            int score = 0;
            boolean hasPhotos = false;

            // Check: no photo
            if(ad.getPictures() == null || ad.getPictures().size()<1) score-=10;

            // Check: Photos
            if(ad.getPictures() != null && ad.getPictures().size()>1){
                int hdPictures = (int)ad.getPictures().stream().filter(p-> p.getQuality().equals("HD")).count();
                int noHdPictures = ad.getPictures().size()-hdPictures;
                score+=(hdPictures*20);
                score+=(noHdPictures*10);
                hasPhotos=true;
            }

            // Check: Description
            if (!ad.getDescription().isEmpty() && ad.getDescription() != null){
                score += 5;

                // Check: Counting
                int words = new StringTokenizer(ad.getDescription()).countTokens();

                if( ad.getTypology().equals("FLAT")){
                    if (19<words && words<50) score += (words*10);
                    if (words>49) score += (words*30);

                    // Check: Complete
                    if (hasPhotos && ad.getHouseSize()!=null) score += 40;
                }
                if(ad.getTypology().equals("CHALET") ) {
                    if (words > 50) score += (words * 20);

                    // Check: Complete
                    if (hasPhotos && ad.getHouseSize()!=null && ad.getGardenSize()!=null) score += 40;
                }


                // Check: Key words
                if(ad.getDescription().toLowerCase().contains("Luminoso".toLowerCase())) score +=5;
                if(ad.getDescription().toLowerCase().contains("Nuevo".toLowerCase())) score +=5;
                if(ad.getDescription().toLowerCase().contains("Céntrico".toLowerCase())) score +=5;
                if(ad.getDescription().toLowerCase().contains("Reformado".toLowerCase())) score+=5;
                if(ad.getDescription().toLowerCase().contains("Ático".toLowerCase())) score +=5;

            }

            // Check: GARAGE Complete
            if (ad.getTypology().equals("GARAGE") && ad.getPictures()== null && ad.getPictures().size()>1) score += 40;


            // set score
            ad.setScore(score);

            // set irrelevance
            if(score<40) ad.setIrrelevantSince(new Date());
            this.adRepository.save(ad);
        });

    }

    @Override
    public List<AdVO> getQualityListing() {

        List<Ad> ad = this.adRepository.findAll();
        return this.adConverter.toVoList(ad);
    }

    @Override
    public List<PublicAd> getPublicListing() {
        List<Ad> ad = this.adRepository.findAll();
        try {

            List<Ad> filter = ad.stream().filter(a -> a.getScore() > 39).sorted(Comparator.comparing(Ad::getScore).reversed()).collect(Collectors.toList());
            return this.adConverter.toPublicVoList(filter);
        }catch (Exception e){
            return null;
        }

    }
}
