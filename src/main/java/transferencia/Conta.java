package transferencia;

public class Conta {
    //agencia, numeroConta, saldo, proprietario

    private String agencia;
    private String numeroConta;
    private double saldo;
    private Cliente proprietario;

    public Conta(String agencia, String numeroConta, double saldo, Cliente proprietario) {
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.proprietario = proprietario;
    }

    public String getAgencia() {
        return this.agencia;
    }

    public String getNumeroConta() {
        return this.numeroConta;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public Cliente getProprietario() {
        return this.proprietario;
    }

    public void realizarDeposito(double valor) {
        saldo += valor;
    }

    public boolean realizarSaque(double valor) {
        if (valor > this.saldo) {
            return false;
        } else {
            this.saldo -= valor;
            return true;
        }
    }

    public boolean realizarTransferencia(double valor, Conta contaDestino) {
        if (realizarSaque(valor)) {
            contaDestino.realizarDeposito(valor);
            return true;
        }
        return false;
    }
}
