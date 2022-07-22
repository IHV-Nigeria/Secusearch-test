package com.example.demosearch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
@Getter
@Setter
@Entity
@Table(name = "biometricinfo")
@ToString
public class BiometricInfo {
    @Id
    @Column(name = "biometricinfo_Id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long biometricInfo_Id;
    @Column(name = "patient_Id")
    private Long patientId;
    private String template;
    @Column(name = "imagewidth")
    private Long imageWidth;
    @Column(name = "imageheight")
    private Long imageHeight;
    @Column(name = "imagedpi")
    private Long imageDPI;
    @Column(name = "imagequality")
    private Long imageQuality;
    @Column(name = "fingerposition")
    private String fingerPosition;
    @Column(name = "serialnumber")
    private String serialNumber;
    private String model;
    private String manufacturer;
    private Long creator;
    private Date dateCreated;
    private String newTemplate;
}
