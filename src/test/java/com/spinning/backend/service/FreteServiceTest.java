package com.spinning.backend.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreteServiceTest {

    private final FreteService freteService = new FreteService();

    @Test
    void deveCalcularFreteParaSudeste() {
        assertEquals(25.0, freteService.calcularFrete("13010-000")); // SP
        assertEquals(25.0, freteService.calcularFrete("20000-000")); // RJ
        assertEquals(25.0, freteService.calcularFrete("31000-000")); // MG
    }

    @Test
    void deveCalcularFreteParaSul() {
        assertEquals(15.0, freteService.calcularFrete("90000-000")); // RS
        assertEquals(15.0, freteService.calcularFrete("80000-000")); // PR
        assertEquals(15.0, freteService.calcularFrete("88000-000")); // SC
    }

    @Test
    void deveCalcularFreteParaCentroOeste() {
        assertEquals(35.0, freteService.calcularFrete("70000-000")); // DF
        assertEquals(35.0, freteService.calcularFrete("74000-000")); // GO
        assertEquals(35.0, freteService.calcularFrete("79000-000")); // MS
    }

    @Test
    void deveCalcularFreteParaNordeste() {
        assertEquals(40.0, freteService.calcularFrete("40000-000")); // BA
        assertEquals(40.0, freteService.calcularFrete("50000-000")); // PE
        assertEquals(40.0, freteService.calcularFrete("60000-000")); // CE
    }

    @Test
    void deveCalcularFreteParaNorte() {
        assertEquals(50.0, freteService.calcularFrete("69000-000")); // AM
        assertEquals(50.0, freteService.calcularFrete("68000-000")); // PA
        assertEquals(50.0, freteService.calcularFrete("77000-000")); // TO
    }

    @Test
    void deveCalcularFreteParaCepInvalidoOuNaoReconhecido() {
        assertEquals(60.0, freteService.calcularFrete("00000-000"));
        assertEquals(0.0, freteService.calcularFrete(""));
        assertEquals(0.0, freteService.calcularFrete(null));
    }

    @Test
    void deveCalcularFreteParaLimitesDeFaixa() {
        // Último de ES (Sudeste)
        assertEquals(25.0, freteService.calcularFrete("29999-999"));
        // Último de MG (Sudeste)
        assertEquals(25.0, freteService.calcularFrete("39999-999"));
        // Último de MS (Centro-Oeste)
        assertEquals(35.0, freteService.calcularFrete("79999-999"));
        // Último geral (Sul)
        assertEquals(15.0, freteService.calcularFrete("99999-999"));
    }
}
