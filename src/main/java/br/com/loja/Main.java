package br.com.loja;

import br.com.loja.Service.LojaService;

import java.math.BigDecimal;
import java.sql.SQLException;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        LojaService lojaService = new LojaService();

//        System.out.println(lojaService.listarProdutos());

//        System.out.println(lojaService.buscarProdutoPorId(3));

//        int linhas = lojaService.removerProdutoPorId(17);
//
//        if (linhas != 0){
//            System.out.println("Produto removido.");
//        } else {
//            System.out.println("Produto não encontrado.");
//        }

        int linhas = lojaService.adicionarProduto("Par de meias", BigDecimal.valueOf(20), 5, null);

        if (linhas != 0){
            System.out.println("Produto adicionado.");
        } else {
            System.out.println("Produto não encontrado.");
        }

    }
}