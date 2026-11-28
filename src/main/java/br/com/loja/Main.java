package br.com.loja;

import br.com.loja.Model.Produto;
import br.com.loja.Service.LojaService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final LojaService lojaService = new LojaService();

        Scanner input = new Scanner(System.in);

        int opcao;

        do {
            opcao = menu(input);
            input.nextLine();
            System.out.println();

            switch (opcao) {
                case 1:

                    try {
                        adicionarProduto(input, lojaService);
                    } catch (IllegalArgumentException | SQLException e) {
                        System.out.printf("\n%s \n", e.getMessage());
                    }

                    break;

                case 2:
                    try {
                        System.out.printf("\n%s \n\n", lojaService.listarProdutos());
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        buscarProdutoPorId(input, lojaService);
                    } catch (IllegalArgumentException | SQLException e) {
                        System.out.printf("\n%s \n\n", e.getMessage());
                    }
                    break;

                case 4:

                    break;

                case 5:
                    try {
                        excluirProdutoPorId(input, lojaService);
                    } catch (IllegalArgumentException | SQLException e) {
                        System.out.printf("\n%s \n\n", e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        System.out.printf("\n%s \n\n", lojaService.listarProdutosComCategoria());
                    } catch (SQLException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.format("\nPrograma encerrado.\n");
                    break;

                default:
                    System.out.println("\nOpção do menu inválida.\n");
            }
        } while (opcao != 0);

    }

    public static int menu(Scanner input) {
        System.out.println();
        System.out.print("========== SISTEMA LOJA ==========\n");
        System.out.println();
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Buscar produto por ID");
        System.out.println("4 - Atualizar produto");
        System.out.println("5 - Excluir produto");
        System.out.println("6 - Listar produtos com categoria");
        System.out.println("0 - Sair");
        System.out.println();
        System.out.print("Escolha uma opção: ");
        return input.nextInt();
    }

    public static void adicionarProduto(Scanner input, LojaService lojaService) throws IllegalArgumentException, SQLException {
        System.out.print("Nome do produto: ");
        String nomeProduto = input.nextLine();

        System.out.print("Preço: ");
        BigDecimal preco = input.nextBigDecimal();

        System.out.print("Quantidade: ");
        int quantidade = input.nextInt();
        input.nextLine();

        System.out.print("Id da categoria: ");
        Integer categoriaId = input.nextInt();
        input.nextLine();

        if (lojaService.adicionarProduto(nomeProduto, preco, quantidade, categoriaId) > 0){
            System.out.println("Produto adicionado com sucesso.");
        } else {
            System.out.println("Produto não adicionado.");
        }



    }

    public static void buscarProdutoPorId (Scanner input, LojaService lojaService) throws SQLException, IllegalArgumentException {
        System.out.print("ID: ");
        int id = input.nextInt();
        input.nextLine();

        Produto produto = lojaService.buscarProdutoPorId(id);

        System.out.format("\nID = %d - Nome = %s - Preço = %s - Estoque = %d - Categoria = %s\n", produto.getId(), produto.getNome(), produto.getPreco(), produto.getEstoque(), produto.getCategoria());

    }

    public static void excluirProdutoPorId(Scanner input, LojaService lojaService) throws SQLException, IllegalArgumentException {
        System.out.print("ID: ");
        int id = input.nextInt();
        input.nextLine();

        if (lojaService.excluirProdutoPorId(id) >0){
            System.out.println("Produto excluido com sucesso.");
        } else {
            System.out.println("Produto não encontrado");
        }
    }
}