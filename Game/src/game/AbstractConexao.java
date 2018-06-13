package game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConexao {

	String dbNome = "trabProgramacao";
	String usuario = "root";
	String senha = "123";

	private Connection conn;
	private Statement stmt;
	private ResultSet resultSet;

	public abstract void insertTable() throws SQLException;

	public abstract void updateTable() throws SQLException;

	public abstract List<JogadorDados> selectAll() throws SQLException;

	private Connection obterConexao() {
		// URL
		String url = "jdbc:mysql://localhost:3306/" + dbNome + "?createDatabaseIfNotExist=true";

		// Driver Manager
		try {
			return conn = DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	protected void sqlUpdate(String sqlComando) {
		conn = obterConexao();
		if (conn != null) {
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sqlComando);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Problema na conexão");
		}

	}

	protected List<JogadorDados> sqlQuery(String sqlComando) throws SQLException{
		JogadorDados dados;
		String data;
		LocalDate date;
		List<JogadorDados> listDados = new ArrayList<>();

		conn = obterConexao();

		if (conn != null) {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sqlComando);
			while (resultSet.next()) {
				dados = new JogadorDados();
				dados.setId(resultSet.getInt(1));
				dados.setNome(resultSet.getString(2));
				dados.setScore(resultSet.getInt(3));
				dados.setQtdVitorias(resultSet.getInt(4));
				// Conversão para LocalDate
				data = resultSet.getString(5);
				date = LocalDate.parse(data);
				dados.setDataJogo(date);
				listDados.add(dados);
			}
		}
		return listDados;
	}
}
