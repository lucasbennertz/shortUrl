package com.ltech.shortUrl.models;

import java.util.Random;

import com.google.zxing.common.BitMatrix;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Site")
public class SiteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    String urlOriginal;
    String urlEncurtada;
    BitMatrix qrCode;

    SiteModel(String urlOriginal){
        this.urlOriginal = urlOriginal;
        this.urlEncurtada = EncurtarUrl();
    };
    
    private String EncurtarUrl(){
        String pattern = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random generator = new Random();
        StringBuffer senha = new StringBuffer();
        for(int i = 0; i < 5; i++){
            int valor = generator.nextInt(52);
            senha.append(pattern.charAt(valor));
        }
        return senha.toString();
    }
}
