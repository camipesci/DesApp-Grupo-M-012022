package ar.edu.unq.desapp.grupoM.backenddesappapi.service;


import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CryptoService {

    @Autowired
    private CryptoRepository cryptoRepository;

    public CryptoCurrency createCrypto(String symbol, Double price) {

        CryptoCurrency newCrypto = new CryptoCurrency(symbol, price, new Date());
        return cryptoRepository.save(newCrypto);
    }

    public List<CryptoCurrency> findBySymbolIs(String symbol) {
        return cryptoRepository.findBySymbolIs(symbol);
    }

    public CryptoCurrency updateCrypto(String symbol, Double price) {
        CryptoCurrency cryptoToModify = cryptoRepository.findBySymbolIs(symbol).stream().findFirst().get();

        cryptoToModify.price = price;
        cryptoToModify.price_date = new Date();

        return cryptoRepository.save(cryptoToModify);
    }
}

