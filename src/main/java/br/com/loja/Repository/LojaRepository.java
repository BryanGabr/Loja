package br.com.loja.Repository;

import br.com.loja.Database.ConnectionFactory;
import br.com.loja.Model.Categoria;
import br.com.loja.Model.Produto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class LojaRepository {

    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    private Connection conexao() throws SQLException {
        return connectionFactory.conectar();
    }

    public int adicionarProduto (String nome, BigDecimal preco, int estoque, Integer categoriaId) throws SQLException {
        String sql = "INSERT INTO PRODUTO VALUES (NULL, ?, ?, ?, ?)";

        try (Connection conn = conexao(); PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, nome);
            ps.setBigDecimal(2, preco);
            ps.setInt(3, estoque);
            if (categoriaId != null){
                ps.setInt(4, categoriaId);
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            return ps.executeUpdate();
        }
    }

    public ArrayList<Produto> listarProdutos() throws SQLException {
        ArrayList<Produto> produtoArrayList = new ArrayList<>();

        String sql = "SELECT PRODUTO.ID, PRODUTO.NOME, PRODUTO.PRECO, PRODUTO.ESTOQUE, PRODUTO.CATEGORIA_ID, CATEGORIA.NOME FROM PRODUTO LEFT JOIN CATEGORIA ON PRODUTO.CATEGORIA_ID = CATEGORIA.ID";

        try (Connection conn = conexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int id = rs.getInt(1);
                    String nome = rs.getString(2);
                    BigDecimal preco = rs.getBigDecimal(3);
                    int estoque = rs.getInt(4);
                    Integer categoriaId = rs.getInt(5);

                    if (rs.wasNull()) categoriaId = null;

                    String categoriaNome = rs.getString(6);

                    if (categoriaId != null) {
                        produtoArrayList.add(new Produto(id, nome, preco, estoque, new Categoria(categoriaId, categoriaNome)));
                    } else {
                        produtoArrayList.add(new Produto(id, nome, preco, estoque, null));
                    }

                }
            }
        }
        return produtoArrayList;
    }

    public ArrayList<Produto> listarProdutosComCategoria() throws SQLException {
        ArrayList<Produto> produtoArrayList = new ArrayList<>();

        String sql = "SELECT PRODUTO.ID, PRODUTO.NOME, PRODUTO.PRECO, PRODUTO.ESTOQUE, PRODUTO.CATEGORIA_ID, CATEGORIA.NOME FROM PRODUTO LEFT JOIN CATEGORIA ON PRODUTO.CATEGORIA_ID = CATEGORIA.ID";

        try (Connection conn = conexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int id = rs.getInt(1);
                    String nome = rs.getString(2);
                    BigDecimal preco = rs.getBigDecimal(3);
                    int estoque = rs.getInt(4);
                    Integer categoriaId = rs.getInt(5);

                    if (rs.wasNull()) categoriaId = null;

                    String categoriaNome = rs.getString(6);

                    if (categoriaId != null) {
                        produtoArrayList.add(new Produto(id, nome, preco, estoque, new Categoria(categoriaId, categoriaNome)));
                    } else {
                        produtoArrayList.add(new Produto(id, nome, preco, estoque, null));
                    }

                }
            }
        }
        return produtoArrayList;
    }

    public Produto buscarProdutoPorId(int id) throws SQLException {
        String sql = "SELECT PRODUTO.ID, PRODUTO.NOME, PRODUTO.PRECO, PRODUTO.ESTOQUE, PRODUTO.CATEGORIA_ID, CATEGORIA.NOME FROM PRODUTO LEFT JOIN CATEGORIA ON PRODUTO.CATEGORIA_ID = CATEGORIA.ID WHERE PRODUTO.ID = ?";

        try (Connection conn = conexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next())
                    return null;

                int produtoId = rs.getInt(1);
                String nome = rs.getString(2);
                BigDecimal preco = rs.getBigDecimal(3);
                int estoque = rs.getInt(4);
                Integer categoriaId = rs.getInt(5);

                if (rs.wasNull()) categoriaId = null;

                String categoriaNome = rs.getString(6);

                if (categoriaId != null) {
                    return new Produto(produtoId, nome, preco, estoque, new Categoria(categoriaId, categoriaNome));
                } else {
                    return new Produto(produtoId, nome, preco, estoque, null);
                }
            }
        }
    }

    public int excluirProdutoPorId(int id) throws SQLException {
        String sql = "DELETE FROM PRODUTO WHERE ID = ?";

        try (Connection conn = conexao(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);

            return ps.executeUpdate();

        }
    }

    public int atualizarProduto(int id, String nome) throws SQLException {
        String sql = "UPDATE PRODUTO SET NOME = ? WHERE ID = ?";

        int linhas = 0;

        try (Connection conn = conexao(); PreparedStatement ps = conn.prepareStatement(sql)){
            conn.setAutoCommit(false);
            try {
                ps.setInt(2, id);
                ps.setString(1, nome);

                linhas = ps.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            }
        }
        return linhas;
    }

    public int atualizarProduto(int id, BigDecimal preco) throws SQLException {
        String sql = "UPDATE PRODUTO SET PRECO = ? WHERE ID = ?";

        int linhas = 0;

        try (Connection conn = conexao(); PreparedStatement ps = conn.prepareStatement(sql)){
            conn.setAutoCommit(false);
            try {
                ps.setInt(2, id);
                ps.setBigDecimal(1, preco);

                linhas = ps.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            }
        }
        return linhas;
    }

    public int atualizarProduto(int id, int estoque) throws SQLException {
        String sql = "UPDATE PRODUTO SET ESTOQUE = ? WHERE ID = ?";

        int linhas = 0;

        try (Connection conn = conexao(); PreparedStatement ps = conn.prepareStatement(sql)){
            conn.setAutoCommit(false);
            try {
                ps.setInt(2, id);
                ps.setInt(1, estoque);

                linhas = ps.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            }
        }
        return linhas;
    }

    public int atualizarCategoriaIdProduto(int id, Integer categoriaId) throws SQLException {
        String sql = "UPDATE PRODUTO SET CATEGORIA_ID = ? WHERE ID = ?";

        int linhas = 0;

        try (Connection conn = conexao(); PreparedStatement ps = conn.prepareStatement(sql)){
            conn.setAutoCommit(false);
            try {
                ps.setInt(2, id);

                if (categoriaId != null) {
                    ps.setInt(1, categoriaId);
                } else {
                    ps.setNull(1, Types.INTEGER);
                }

                linhas = ps.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return linhas;
    }
}
