package ar.edu.unq.desapp.grupoM.backenddesappapi.model.helpers;

import java.util.Random;

public class Generator {
    public  String generateCVU() {
        return generateRandomNumber(22);
    }

    public Integer generateWallet() {
        Integer wallet = Integer.parseInt(generateRandomNumber(8));
        return wallet;
    }

    public String generateRandomNumber(Integer len) {
        String chars = "0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
}
