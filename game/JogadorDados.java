import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class JogadorDados extends AbstractConexao {

	private int id;
	private String nome;
	private int score;
	private int qtdVitorias;
	private LocalDate dataJogo;

	public JogadorDados() {
		LocalDate dataGame = LocalDate.now();
		this.dataJogo = dataGame;
	}

	public JogadorDados(String nome, int score, int qtdVitorias) {
		this.nome = nome;
		this.score = score;
		this.qtdVitorias = qtdVitorias;

		LocalDate dataGame = LocalDate.now();
		this.dataJogo = dataGame;
	}

	@Override
	public void insertTable() throws SQLException {
		String sqlInsertJogador = "INSERT INTO jogador (jog_nome, jog_score, jog_vitorias, jog_data_jogo) VALUES ('"
				+ getNome() + "', " + getScore() + ", " + getQtdVitorias() + ", '" + getDataJogo() + "');";

		System.out.println(sqlInsertJogador);
		super.sqlUpdate(sqlInsertJogador);
	}

	@Override
	public void updateTable() throws SQLException {

	}

	@Override
	public List<JogadorDados> selectAll() throws SQLException {
		String querySelectAll = "SELECT * FROM jogador ORDER BY jog_score DESC;";
		List<JogadorDados> dados = super.sqlQuery(querySelectAll);
		return dados;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getQtdVitorias() {
		return qtdVitorias;
	}

	public void setQtdVitorias(int qtdVitorias) {
		this.qtdVitorias = qtdVitorias;
	}

	public LocalDate getDataJogo() {
		return dataJogo;
	}

	public void setDataJogo(LocalDate dataJogo) {
		this.dataJogo = dataJogo;
	}

}
