package com.idealista.infrastructure.api.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "Pictures")
@Entity
public class Picture {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="url")
    private String url;

    @Column(name="quality")
    private String quality;

    @ManyToOne
    @JoinColumn(name="ad_id")
    private Ad ad;

}
