package br.com.loja.Service;

import br.com.loja.Model.Produto;
import br.com.loja.Repository.LojaRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class LojaService {
    private final LojaRepository lojaRepository = new LojaRepository();

    public ArrayList<Produto> listarProdutos() throws SQLException {
        return lojaRepository.listarProdutos();
    }

    public Produto buscarProdutoPorId(int id) throws SQLException {
        if (id <= 0)
            throw new IllegalArgumentException("Id inválido.");

        return lojaRepository.buscarProdutoPorId(id);
    }
}
