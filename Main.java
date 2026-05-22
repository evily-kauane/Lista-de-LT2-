public class Main {
    public static void main(String[] args) {

        CaixaEletronico caixaEletronico = new CaixaEletronico();
        // aqui ser o pimeiro teste: valor que precisa de nota de 10

        System.out.println(" Saldo inicial:" + caixaEletronico.getSaldo());
        caixaEletronico.realizarSaque(30);
        System.out.println("Saldo final : " + caixaEletronico.getSaldo() + "/n");

        //teste 2: vou simular um valor e, que não da para formar com as notas que tem
        caixaEletronico.realizarSaque(15);
        System.out.println("Saldo final apos erro : " + caixaEletronico.getSaldo());


        // o saldo deve continuar o mesmo de antes pois ele devolveu o dinheoiro
    }
}

