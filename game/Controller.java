import java.sql.SQLException;
import java.util.List;

public class Controller {

	public static void inserirDados(AbstractConexao obj) throws SQLException {
		obj.insertTable();
	}

	public static void updatetDados(AbstractConexao obj) throws SQLException {
		obj.updateTable();
	}

	public static List<JogadorDados> selectDados(AbstractConexao obj) throws SQLException {
		List<JogadorDados> dados = obj.selectAll();
		return dados;
	}
}