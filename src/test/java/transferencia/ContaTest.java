package transferencia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    Cliente xuxa;
    Cliente silvioSantos;
    Conta contaXuxa;
    Conta contaSilvio;
    String testeGit;


    //Before Each Test   Use: ALT + INSERT
    @BeforeEach
    void setUp() {
        xuxa = new Cliente("Xuxa", "12345678900", "11111");
        silvioSantos = new Cliente("Silvio Santos", "00987654321", "22222");

        contaXuxa = new Conta("0025", "2254", 2500, xuxa);
        contaSilvio = new Conta("0025", "33208", 3500, silvioSantos);
    }

    @Test
    public void realizarTransacao() {
        contaXuxa.realizarTransferencia(1000, contaSilvio);

        assertEquals(1500, contaXuxa.getSaldo());
        assertEquals(4500, contaSilvio.getSaldo());
    }

    @Test
    public void validarTransferenciaInvalida() {
        boolean resultado = contaXuxa.realizarTransferencia(3500, contaSilvio);

        assertFalse(resultado);
    }

    @Test
    public void validarProprietario() {
        assertEquals(xuxa, contaXuxa.getProprietario());

    }

}