package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.CryptoDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.CryptoService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.transaction.Transactional;
import java.util.List;

@Api(tags = "Crypto Controller")
@Tag(name = "Crypto Controller", description = "Manage Crypto ABM")
@RestController
public class CryptoController {
    @Autowired
    private CryptoService cryptoService;

    @Operation(summary = "Get and persist a crypto by its name")
    @GetMapping("/api/cryptos/{symbol}")
    @Transactional
    public ResponseEntity<CryptoDTO> getCryptoPrice(@PathVariable String symbol) {
        Crypto crypto = cryptoService.findCrypto(symbol);
        return ResponseEntity.ok().body(CryptoDTO.from(crypto));
    }

    @Operation(summary = "Gets and persists all cryptos related to the project scope")
    @GetMapping("/api/cryptos")
    @Transactional
    public ResponseEntity<List<CryptoDTO>> getAllCryptosPrice() {
        List<Crypto> cryptoList = cryptoService.getCryptos();
        return ResponseEntity.ok().body(CryptoDTO.from(cryptoList));
    }
}
