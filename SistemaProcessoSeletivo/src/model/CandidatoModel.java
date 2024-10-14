package model;

import object.CandidatoObject;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class CandidatoModel {
    private static final String URL = "jdbc:postgresql://localhost:5432/sistemaprocessoseletivo";
    private static final String USER = "knuckles";
    private static final String PASSWORD = "postdba";
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;

    protected static Connection connectDB() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    protected static void fecharConexao(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }

    public String inserirCandidato(CandidatoObject candidatoObject){
        String consulta = "INSERT INTO Candidatos (nomeCandidato, emailCandidato, salarioCandidato) VALUES (?, ?, ?)";
        try {
            conn = connectDB();
            pstmt = conn.prepareStatement(consulta);
            pstmt.setString(1, candidatoObject.getNomeCandidato());
            pstmt.setString(2, candidatoObject.getEmailCandidato());
            pstmt.setDouble(3, candidatoObject.getSalarioCandidato());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                consulta = "Candidato inserido com sucesso!";
            } else {
                consulta = "Erro ao inserir candidato model.";
            }

        } catch (SQLException e) {
            consulta = "Erro ao inserir candidato model: " + e.getMessage();
        } finally {
            fecharConexao(conn, pstmt, rs);
        }
        return consulta;
    }

    public String alterarCandidato(CandidatoObject candidatoObject) {
        String consulta = "UPDATE Candidatos SET nomeCandidato = ?, emailCandidato = ?, salarioCandidato = ? WHERE emailCandidato = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connectDB();
            pstmt = conn.prepareStatement(consulta);
            pstmt.setString(1, candidatoObject.getNomeCandidato());
            pstmt.setString(2, candidatoObject.getEmailCandidato());
            pstmt.setDouble(3, candidatoObject.getSalarioCandidato());
            pstmt.setString(4, candidatoObject.getEmailCandidato());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                consulta = "Candidato alterado com sucesso!";
            } else {
                consulta = "Nenhum candidato encontrado com o email especificado.";
            }

        } catch (SQLException e) {
            consulta = "Erro ao alterar candidato model: " + e.getMessage();
        } finally {
            fecharConexao(conn, pstmt, rs);
        }
        return consulta;
    }

    public List<CandidatoObject> buscarCandidatosComSalarioMenorOuIgual(double salarioBase) {
        String consulta = "SELECT nomeCandidato, emailCandidato, salarioCandidato FROM Candidatos WHERE salarioCandidato <= ?";
        List<CandidatoObject> candidatos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            pstmt = conn.prepareStatement(consulta);
            pstmt.setDouble(1, salarioBase);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CandidatoObject candidato = new CandidatoObject();
                candidato.setNomeCandidato(rs.getString("nomeCandidato"));
                candidato.setEmailCandidato(rs.getString("emailCandidato"));
                candidato.setSalarioCandidato(rs.getDouble("salarioCandidato"));
                candidatos.add(candidato);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar candidatos: " + e.getMessage());
        } finally {
            fecharConexao(conn, pstmt, rs);
        }
        return candidatos;
    }

    public String validaSalario(CandidatoObject candidatoObject) {
        return (candidatoObject.getSalarioBase() > candidatoObject.getSalarioCandidato() ? "Ligar para o candidato"
                : (candidatoObject.getSalarioBase() == candidatoObject.getSalarioCandidato() ? "Ligar para o candidato com contraproposta"
                : "Aguardando resultado dos demais candidatos"));
    }
}