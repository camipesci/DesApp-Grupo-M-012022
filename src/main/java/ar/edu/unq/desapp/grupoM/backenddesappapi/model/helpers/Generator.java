package ar.edu.unq.desapp.grupoM.backenddesappapi.model.helpers;

import java.util.Random;

public class Generator {
    private Random rnd = new Random();
    public  String generateCVU() {
        String chars = "0123456789";
        Integer cvu_len = 22;
        StringBuilder sb = new StringBuilder(cvu_len);
        for (int i = 0; i < cvu_len; i++)
            sb.append(chars.charAt(this.rnd.nextInt(chars.length())));
        return sb.toString();
    }

    public Integer generateWallet() {
        return getRandomNumber(10000000,99999999);
    }

    private Integer getRandomNumber(Integer min, Integer max) {
        Integer random_int = (int) Math.floor(Math.random()*(max-min+1)+min);
        return random_int;
    }
}
