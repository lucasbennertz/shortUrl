package com.ltech.shortUrl.models;

import java.util.Hashtable;
import java.util.Random;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import java.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ltech.shortUrl.Dto.SiteDTO;

import jakarta.persistence.Column;
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
@Table(name = "site")
public class SiteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    @Column(name = "url_original", nullable = false)
    private String urlOriginal;
    @Column(name = "url_encurtada", nullable = false, unique = true)
    private String urlEncurtada;
    @Column(name = "qr_code_base64", nullable = false, columnDefinition = "TEXT")
    private String qrCodeBase64;

    public SiteModel(String urlOriginal){
        this.urlOriginal = urlOriginal;
        this.urlEncurtada = EncurtarUrl();
        BitMatrix matrix = createQRImage(urlOriginal);
        this.qrCodeBase64 = bitMatrixToBase64(matrix);
    };

    public SiteModel(){}
    
    private String EncurtarUrl(){
        String pattern = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random generator = new Random();
        StringBuilder senha = new StringBuilder();
        for(int i = 0; i < 5; i++){
            int valor = generator.nextInt(52);
            senha.append(pattern.charAt(valor));
        }
        return senha.toString();
    }
    private BitMatrix createQRImage(String qrCodeText ){
        BitMatrix byteMatrix = null;
		try{
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, 100, 100, hintMap);
        }catch(WriterException e){
            System.out.println("Deu erro aqui");
            e.printStackTrace();
        }
        return byteMatrix;
	}
    private String bitMatrixToBase64(BitMatrix matrix) {
        try {
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, matrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public SiteModel siteDtoToModel(SiteDTO siteDTO){
        SiteModel siteModel = new SiteModel(siteDTO.getUrlOriginal());
        return siteModel;
    }
}

