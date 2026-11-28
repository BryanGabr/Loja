package br.com.loja.Service;

import br.com.loja.Model.Produto;
import br.com.loja.Repository.LojaRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class LojaService {
    private final LojaRepository lojaRepository = new LojaRepository();

    public int adicionarProduto(String nome, BigDecimal preco, int estoque, Integer categoriaId) throws SQLException {
        if (estoque < 0)
            throw new IllegalArgumentException("Estoque inválido. Estoque não pode ser menor que zero.");

        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome inválido. Nome não pode está em branco ou conter espaço.");

        if (categoriaId != null){
            if (categoriaId <= 0)
                throw new IllegalArgumentException("Id inválido.");
        }


        if (preco == null || preco.compareTo(new BigDecimal(0)) <= 0)
            throw new IllegalArgumentException("Preço inválido. Preço não pode ser menor ou igual a zero.");

        return lojaRepository.adicionarProduto(nome, preco, estoque, categoriaId);
    }

    public ArrayList<Produto> listarProdutos() throws SQLException {
        return lojaRepository.listarProdutos();
    }

    public ArrayList<Produto> listarProdutosComCategoria() throws SQLException {
        return lojaRepository.listarProdutosComCategoria();
    }

    public Produto buscarProdutoPorId(int id) throws SQLException {
        if (id <= 0)
            throw new IllegalArgumentException("Id inválido.");

        return lojaRepository.buscarProdutoPorId(id);
    }

    public int excluirProdutoPorId(int id) throws SQLException {
        if (id <= 0)
            throw new IllegalArgumentException("Id inválido.");

        return lojaRepository.excluirProdutoPorId(id);
    }

    public int atualizarProduto(int id, String nome) throws SQLException {
        if (id <= 0)
            throw new IllegalArgumentException("Id inválido.");

        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome inválido. Nome não pode está em branco ou conter espaço.");

        return lojaRepository.atualizarProduto(id, nome);
    }

    public int atualizarProduto(int id, BigDecimal preco) throws SQLException {
        if (id <= 0)
            throw new IllegalArgumentException("Id inválido.");

        if (preco == null || preco.compareTo(new BigDecimal(0)) <= 0)
            throw new IllegalArgumentException("Preço inválido. Preço não pode ser menor ou igual a zero.");

        return lojaRepository.atualizarProduto(id, preco);
    }

    public int atualizarProduto(int id, int estoque) throws SQLException {
        if (id <= 0)
            throw new IllegalArgumentException("Id inválido.");

        if (estoque < 0)
            throw new IllegalArgumentException("Estoque inválido. Estoque não pode ser menor que zero.");

        return lojaRepository.atualizarProduto(id, estoque);
    }

    public int atualizarCategoriaIdProduto(int id, Integer categoriaId) throws SQLException {
        if (id <= 0)
            throw new IllegalArgumentException("Id inválido.");

        if (categoriaId != null){
            if (categoriaId <= 0)
                throw new IllegalArgumentException("Id inválido.");
        }

        return lojaRepository.atualizarProduto(id, categoriaId);
    }
}
