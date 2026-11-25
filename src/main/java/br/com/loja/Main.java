package br.com.loja;

import br.com.loja.Database.ConnectionFactory;
import br.com.loja.Service.LojaService;

import java.sql.SQLException;
import java.util.Collections;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        LojaService lojaService = new LojaService();

        System.out.println(lojaService.listarProdutos());
    }
}