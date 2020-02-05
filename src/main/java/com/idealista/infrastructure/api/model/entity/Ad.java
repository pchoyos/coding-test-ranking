package com.idealista.infrastructure.api.model.entity;

import com.idealista.infrastructure.api.model.entity.Picture;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "ads")
@Entity
public class Ad {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="typology")
    private String typology;

    @Column(name="description")
    private String description;

    @Column(name="house_size")
    private Integer houseSize;

    @Column(name="garden_size")
    private Integer gardenSize;

    @Column(name="score")
    private Integer score;

    @Column(name="irrelevant_since")
    private Date irrelevantSince;

    @OneToMany(mappedBy = "ad",fetch = FetchType.LAZY)
    private List<Picture> pictures = new ArrayList<>();




}
