package dao;

import model.Cliente;
import model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    public void create(Cliente cliente) throws SQLException{
        String sql = "INSERT INTO clientes (nome, email, telefone, endereco) VALUES (?, ?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getTelefone());
                stmt.setString(4, cliente.getEndereco());
                stmt.executeUpdate();
            };
    }

    public List<Cliente> read() throws SQLException{
        List<Cliente> clientes = new ArrayList<>();
    
        String sql = "SELECT * FROM clientes";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet  rs = stmt.executeQuery(sql)){
    
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                clientes.add(cliente);
            }
            return clientes;
        }
    
    }
    

    public void update(Cliente cliente) throws SQLException{
        String sql = "UPDATE clientes SET nome=?, email=?, telefone=?, endereco=? WHERE id =?";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getTelefone());
                stmt.setString(4, cliente.getEndereco());
                stmt.executeUpdate();
            };
    }


    public void delete(int id) throws SQLException{
        String sql = "DELETE * FROM WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        };
    }
 
}

