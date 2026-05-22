public class CaixaEletronico {
    private double Saldo;

    private int cedulas200, cedulas100, cedulas50, cedulas20, cedulas10;

    public CaixaEletronico() {

        this.Saldo = 500;
        this.cedulas200 = 10;
        this.cedulas100 = 13;
        this.cedulas50 = 6;
        this.cedulas20 = 40;
        this.cedulas10 = 20;


    }

    public void verificarSaldo(int valorSaque) throws SaldoInsuficiente {// se public void verificarSaldo(double valorSaque)quando
        // receber um valor que não tenha
        // ele vai mandar esse erro para a classe SaldoInsuficiente para fazer o tratamento de exceção e assim der continuidade;
        if (valorSaque > this.Saldo) { // Se eu quiser sacar um valor mais alto do que eu tenho eu meu saldo,
            // irá imprimar a mensagem informando sobre.
            // No trow new eu tou lançando criando um objeto de erro
            // o throw é quem faz acontecer o erro ele disparar esse erro na hora que a condição acontece
            //format é para montar um texto formatado, bonito na posição certa e tudo mas.
            //%d é porque vai retornar a um valor inteiro

            throw new SaldoInsuficiente(String.format("erro: saldo insuficiente .Saldo atual :R$ %.2f | " +
                    "o valor solicitado é: R$ %d,00", this.Saldo, valorSaque));


        }
    }

    // é uma classe auxiliar interna no qual serve para guardar os números de quantas notas que vão usar
    private static class LocalDasCedulas {

        // aqui é onde vai armazenar a quantidade de números de cada nota que têm.

        int qtd200, qtd100, qtd50, qtd20, qtd10;


    }
    // esse metodo pega os objetos da classe auxiliar para saber quantas cedulas tem, depois ele calcular com base no valor do saque que o usuário digitar
    //e depois devolve quantas notas de cada cedula ele usou
    // throws FaltaDeCedulas ele avisa que se não tiver cedulas suficiente ele
    // restante/200 significa quantas notas de 200 eu vou usar para fazer o saque? ,ex; se o saque fosse 140 eu iria utilizar somente uma.
    // seo valor do saque for 140 o restante começa valendo 140,então seria 140/


    public LocalDasCedulas caucularCedulas(int valorSaque) throws FaltaDeCedulas {
        int restanteQueSobraSaque = valorSaque;//já começa com o valor certo sem conversão.

        //tou ultilizando esta outra classe para guardar informações atualizadas EX" quantas notas que sobrou dps que eu saquei?"
        //  ai ela vai e coloca a quantidade.
        LocalDasCedulas uso = new LocalDasCedulas();

        uso.qtd200 = Math.min(restanteQueSobraSaque / 200, this.cedulas200);
        restanteQueSobraSaque -= uso.qtd200 * 200;

        uso.qtd100 = Math.min(restanteQueSobraSaque / 100, this.cedulas100);
        restanteQueSobraSaque -= uso.qtd100 * 100;

        uso.qtd50 = Math.min(restanteQueSobraSaque / 50, this.cedulas50);
        restanteQueSobraSaque -= uso.qtd50 * 50;

        uso.qtd20 = Math.min(restanteQueSobraSaque / 20, this.cedulas50);
        restanteQueSobraSaque -= uso.qtd20 * 20;


        uso.qtd10 = Math.min(restanteQueSobraSaque / 10, this.cedulas20);
        restanteQueSobraSaque -= uso.qtd10;

        // aqui vamos verificar se conseguiu formar o valor total
        if (restanteQueSobraSaque > 0) {
            throw new FaltaDeCedulas(
                    String.format("erro: Não há cedulas suficientes para R% %d,00. Falta R$ %d,00 em notas",
                            valorSaque, restanteQueSobraSaque)
            );
        }

        // se deu tudo certo, agora sim diminui o estoque

        this.cedulas200 -= uso.qtd200;
        this.cedulas100 -= uso.qtd100;
        this.cedulas50 -= uso.qtd50;
        this.cedulas20 -= uso.qtd20;
        this.cedulas10 -= uso.qtd10;


        return uso;

    }

    public void realizarSaque(int valor) {
        System.out.println(" tentativa de saque : r$ " + valor + " ,00---");
        try {
            // verificar se tem saldo
            verificarSaldo(valor);

            this.Saldo -= valor;
            System.out.println(" valor reservado na conta ");


            // vamos tentar pegar  as notas
            LocalDasCedulas notas = calcularCedulas(valor);

            //vamos para a impressão das mensagens
            System.out.println("Saque realizado com sucesso!!");


            System.out.println(" Notas de 200: " + notas.qtd200);
            System.out.println(" Notas de 100: " + notas.qtd100);
            System.out.println(" Notas de 50: " + notas.qtd50);
            System.out.println(" Notas de 20: " + notas.qtd20);
            System.out.println(" Notas de 10: " + notas.qtd10);

        } catch (SaldoInsuficiente ex) {
            System.out.println(ex.getMessage());

        }

    }

    private LocalDasCedulas calcularCedulas(int valor) {
        return null;
    }

    //metodo para ver o saldo da conta
    public double getSaldo(){
        return  this.Saldo;


    }

}

