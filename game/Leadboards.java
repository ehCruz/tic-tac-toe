import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Leadboards extends Thread {

	JogadorDados jogador = new JogadorDados();
	JPanel painel = new JPanel();
	JTable table = new JTable();
	JScrollPane scrollPane = new JScrollPane(table);
	JLabel lblLeaderboard = new JLabel("Ranking");
	JButton btnVoltar = new JButton("Voltar");
	private int posicao = 1;

	public Leadboards() {
		start();
	}

	@Override
	public void run() {
		table.setBorder(new LineBorder(Color.GREEN));
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		table.setBackground(Color.LIGHT_GRAY);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		List<String> colunas = new ArrayList<>();
		List<String[]> linhas = new ArrayList<>();
		colunas.add("Posição");
		colunas.add("Player");
		colunas.add("Score");
		colunas.add("Vitórias");
		colunas.add("Data do jogo");

		try {
			List<JogadorDados> dados = Controller.selectDados(jogador);
			for (JogadorDados jogador : dados) {
				linhas.add(new String[] { String.valueOf(posicao), jogador.getNome(),
						String.valueOf(jogador.getScore()), String.valueOf(jogador.getQtdVitorias()),
						String.valueOf(jogador.getDataJogo()) });
				posicao++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TableModel tm = new DefaultTableModel(linhas.toArray(new String[][] {}), colunas.toArray());
		table.setModel(tm);
		table.setEnabled(false);

		scrollPane.getViewport().setBorder(null);
		scrollPane.setSize(new Dimension(450, 450));
		painel.add(lblLeaderboard);
		painel.add(scrollPane);
		painel.add(btnVoltar);
		Principal.frame.getContentPane().removeAll();
		Principal.frame.getContentPane().add(painel);
		Principal.frame.invalidate();
		Principal.frame.validate();

		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Principal().processar();
			}
		});
	}

}