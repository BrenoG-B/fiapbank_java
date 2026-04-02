import java.util.Scanner;

public class FiapBankAtm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double saldo = 0.0;
        int senhaCadastrada = 0;
        int senhaInformada = 0;
        int tentativas = 3;
        boolean senhaCriada = false;
        boolean acessoLiberado = false;
        int opcao = -1;

        System.out.println("==========================");
        System.out.println(" FIAP BANK - ATM ");
        System.out.println("==========================");

        // CADASTRO DE SENHA
        while (!senhaCriada) {
            System.out.print("Cadastre uma senha numérica de 4 dígitos: ");

            if (scanner.hasNextInt()) {
                senhaCadastrada = scanner.nextInt();

                if (senhaCadastrada >= 1000 && senhaCadastrada <= 9999) {
                    senhaCriada = true;
                    System.out.println("Senha cadastrada com sucesso!");
                } else {
                    System.out.println("Erro: a senha deve ter exatamente 4 dígitos!");
                }
            } else {
                System.out.println("ERRO: digite apenas números!");
                scanner.next();
            }
        }

        // LOGIN
        while (tentativas > 0 && !acessoLiberado) {
            System.out.print("\nDigite sua senha para acessar o caixa: ");

            if (scanner.hasNextInt()) {
                senhaInformada = scanner.nextInt();

                if (senhaInformada == senhaCadastrada) {
                    acessoLiberado = true;
                    System.out.println("Acesso liberado com sucesso!");
                } else {
                    tentativas--;
                    System.out.println("Senha incorreta!");
                    if (tentativas > 0) {
                        System.out.println("Tentativas restantes: " + tentativas);
                    }
                }
            } else {
                System.out.println("Erro: digite apenas números!");
                scanner.next();
            }
        }

        if (!acessoLiberado) {
            System.out.println("\nAcesso bloqueado. Número máximo de tentativas excedido!");
            scanner.close();
            return;
        }

        // MENU PRINCIPAL
        do {
            System.out.println("\n==============================");
            System.out.println("         MENU ATM");
            System.out.println("==============================");
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Alterar senha");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
                        break;

                    case 2:
                        System.out.print("Digite o valor do depósito: ");

                        if (scanner.hasNextDouble()) {
                            double deposito = scanner.nextDouble();

                            if (deposito > 0) {
                                saldo += deposito;
                                System.out.printf("Depósito realizado com sucesso! Novo saldo: R$ %.2f%n", saldo);
                            } else {
                                System.out.println("Erro: o valor do depósito deve ser maior que zero.");
                            }
                        } else {
                            System.out.println("Erro: digite um valor numérico válido.");
                            scanner.next();
                        }
                        break;

                    case 3:
                        System.out.print("Digite o valor do saque: ");

                        if (scanner.hasNextDouble()) {
                            double saque = scanner.nextDouble();

                            if (saque <= 0) {
                                System.out.println("Erro: o valor do saque deve ser maior que zero.");
                            } else if (saque > saldo) {
                                System.out.println("Erro: saldo insuficiente para realizar o saque.");
                            } else {
                                saldo -= saque;
                                System.out.printf("Saque realizado com sucesso! Novo saldo: R$ %.2f%n", saldo);
                            }
                        } else {
                            System.out.println("Erro: digite um valor numérico válido.");
                            scanner.next();
                        }
                        break;

                    case 4:
                        System.out.print("Digite a senha atual: ");

                        if (scanner.hasNextInt()) {
                            int senhaAtual = scanner.nextInt();

                            if (senhaAtual == senhaCadastrada) {
                                System.out.print("Digite a nova senha de 4 dígitos: ");

                                if (scanner.hasNextInt()) {
                                    int novaSenha = scanner.nextInt();

                                    if (novaSenha >= 1000 && novaSenha <= 9999) {
                                        senhaCadastrada = novaSenha;
                                        System.out.println("Senha alterada com sucesso!");
                                    } else {
                                        System.out.println("Erro: a nova senha deve ter exatamente 4 dígitos.");
                                    }
                                } else {
                                    System.out.println("Erro: digite apenas números.");
                                    scanner.next();
                                }
                            } else {
                                System.out.println("Erro: senha atual incorreta.");
                            }
                        } else {
                            System.out.println("Erro: digite apenas números.");
                            scanner.next();
                        }
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema... Obrigado por usar o FIAP BANK ATM.");
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }

            } else {
                System.out.println("Erro: digite uma opção numérica válida.");
                scanner.next();
            }

        } while (opcao != 0);

        scanner.close();
    }
}