package view;

import object.CandidatoObject;
import controller.CandidatoController;
import java.util.List;
import java.util.Scanner;

public class CandidatoView {
    private static int opcaoCandidato;
    private static boolean verificaDecisao = true;
    static CandidatoObject candidatoObject = new CandidatoObject();
    static CandidatoController candidatoController = new CandidatoController();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            System.out.println("Bem vindo a sua inscrição");
            do {
                System.out.println("Deseja se candidatar? Digite 1");
                System.out.println("Deseja alterar sua candidatura? Digite 2");
                System.out.println("Deseja listar os candidatos selecionados? Digite 3");
                System.out.println("Sair do sistema? Digite 4");
                opcaoCandidato = scanner.nextInt();

                switch (opcaoCandidato) {
                    case 1:
                        System.out.println(inserirCandidato());
                        break;
                    case 2:
                        System.out.println(alterarCandidato());
                        break;
                    case 3:
                        listarCandidatos();
                        break;
                    case 4:
                        verificaDecisao = false;
                        break;
                    default:
                        System.out.println("Insira uma opção válida");
                }
            } while (verificaDecisao);
        } catch (Exception erro) {
            System.err.println("Erro no sistema " + erro.getMessage());
        }
    }

    private static String inserirCandidato() {
        try {
            System.out.println("Insira seu nome: ");
            candidatoObject.setNomeCandidato(scanner.next());
            System.out.println("Insira seu email: ");
            candidatoObject.setEmailCandidato(scanner.next());
            System.out.println("Insira o salário pretendido: ");
            candidatoObject.setSalarioCandidato(scanner.nextDouble());

            return candidatoController.inserirCandidato(candidatoObject);
        } catch (Exception erro) {
            System.err.println("Insira os dados corretamente, se atente aos númericos e para decimais use o formatado (0.00)\n" + erro.getMessage());
        }
        return "Erro ao inserir candidato view";
    }

    private static String alterarCandidato() {
        try {
            System.out.println("Informe o seu email para alteração");
            System.out.println("Insira seu nome: ");
            candidatoObject.setNomeCandidato(scanner.next());
            System.out.println("Insira seu email: ");
            candidatoObject.setEmailCandidato(scanner.next());
            System.out.println("Insira o salário pretendido: ");
            candidatoObject.setSalarioCandidato(scanner.nextDouble());

            return candidatoController.alterarCandidato(candidatoObject);
        } catch (Exception erro) {
            System.err.println("Insira os dados corretamente, se atente aos númericos e para decimais use o formatado (0.00)\n" + erro.getMessage());
        }
        return "Erro ao alterar candidato view";
    }

    private static void listarCandidatos() {
        List<CandidatoObject> candidatos = candidatoController.buscarCandidatos(candidatoObject.getSalarioBase());
        if (candidatos.isEmpty()) {
            System.out.println("Nenhum candidato encontrado com salário menor ou igual a " + candidatoObject.getSalarioBase());
        } else {
            for (CandidatoObject candidato : candidatos) {
                System.out.println("Nome: " + candidato.getNomeCandidato() +
                        ", Email: " + candidato.getEmailCandidato() +
                        ", Salário: " + candidato.getSalarioCandidato());
                System.out.println(candidatoController.validarCandidato(candidato));
            }
        }
    }
}
