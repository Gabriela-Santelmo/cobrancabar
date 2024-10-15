package cobrancabar;

import java.util.Scanner;

public class cobrancabar {

    private static final double PRECO_INGRESSO_HOMEM = 10.0;
    private static final double PRECO_INGRESSO_MULHER = 8.0;
    private static final double PRECO_CERVEJA = 5.0;
    private static final double PRECO_REFRIGERANTE = 3.0;
    private static final double PRECO_ESPETINHO = 7.0;
    private static final double TAXA_COUVERT = 4.0;
    private static final double VALOR_LIMITE_COUVERT = 30.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=========================");
        System.out.println("===  ATENDIMENTO BAR  ===");
        System.out.println("=========================\n");
        
        char sexo = lerSexo(scanner);
        int quantidadeCervejas = lerQuantidade(scanner, "- Quantidade de cervejas:");
        int quantidadeRefrigerantes = lerQuantidade(scanner, "- Quantidade de refrigerantes:");
        int quantidadeEspetinhos = lerQuantidade(scanner, "- Quantidade de espetinhos:");

        double ingresso = (sexo == 'M') ? PRECO_INGRESSO_HOMEM : PRECO_INGRESSO_MULHER;
        double consumo = calcularConsumo(quantidadeCervejas, quantidadeRefrigerantes, quantidadeEspetinhos);
        double couvert = (consumo > VALOR_LIMITE_COUVERT) ? 0 : TAXA_COUVERT;
        double valorTotal = ingresso + consumo + couvert;

        gerarRelatorio(consumo, couvert, ingresso, valorTotal);
        scanner.close();
    }

    private static char lerSexo(Scanner scanner) {
        System.out.println("Informe:");
        System.out.println("- Sexo (F ou M):");
        char sexo;
        while (true) {
            sexo = scanner.next().toUpperCase().charAt(0);
            if (sexo == 'F' || sexo == 'M') {
                break;
            }
            System.out.println("Sexo inválido. Por favor, digite F para feminino ou M para masculino:");
        }
        return sexo;
    }

    private static int lerQuantidade(Scanner scanner, String mensagem) {
        int quantidade = -1;
        while (quantidade < 0) {
            System.out.print(mensagem + " ");
            if (scanner.hasNextInt()) {
                quantidade = scanner.nextInt();
                if (quantidade < 0) {
                    System.out.println("Digite apenas números inteiros positivos.");
                }
            } else {
                System.out.println("Digite apenas números inteiros.");
                scanner.next(); 
            }
        }
        return quantidade;
    }

    private static double calcularConsumo(int quantidadeCervejas, int quantidadeRefrigerantes, int quantidadeEspetinhos) {
        return (quantidadeCervejas * PRECO_CERVEJA) +
               (quantidadeRefrigerantes * PRECO_REFRIGERANTE) +
               (quantidadeEspetinhos * PRECO_ESPETINHO);
    }

    private static void gerarRelatorio(double consumo, double couvert, double ingresso, double valorTotal) {
        System.out.println("\n ___________________\n");
        System.out.println("|  RELATÓRIO VALOR  |");
        System.out.println(" ___________________\n");
        System.out.printf("Consumo = R$ %.2f\n", consumo);
        
        if (couvert == 0) {
            System.out.println("Couvert = Isento de Couvert");
        } else {
            System.out.printf("Couvert = R$ %.2f\n", couvert);
        }
        
        System.out.printf("Ingresso = R$ %.2f\n", ingresso);
        System.out.printf("\nValor total a pagar = R$ %.2f\n", valorTotal);
    }
}
