package com.spinning.backend.service;

import org.springframework.stereotype.Service;

@Service
public class FreteService {

    public double calcularFrete(String cepDestino) {
        if (cepDestino == null || cepDestino.length() < 2) {
            return 0.0;
        }

        int prefixo = Integer.parseInt(cepDestino.substring(0, 2));

        if (prefixo >= 10 && prefixo <= 39) {
            return 25.0; // Sudeste
        } else if (prefixo >= 80 && prefixo <= 99) {
            return 15.0; // Sul
        } else if ((prefixo >= 70 && prefixo <= 73) || (prefixo >= 74 && prefixo <= 76) || prefixo == 79) {
            return 35.0; // Centro-Oeste
        } else if ((prefixo >= 40 && prefixo <= 49) || (prefixo >= 50 && prefixo <= 59)
                || (prefixo >= 60 && prefixo <= 64)) {
            return 40.0; // Nordeste
        } else if ((prefixo >= 68 && prefixo <= 69) || prefixo == 76 || prefixo == 77 || prefixo == 65
                || prefixo == 66) {
            return 50.0; // Norte
        } else {
            return 60.0; // Região não reconhecida
        }
    }
}
