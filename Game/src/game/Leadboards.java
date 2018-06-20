package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Leadboards extends Thread implements DesignTela {

    private JogadorDados jogador = new JogadorDados();
    private JPanel painel = new JPanel();
    private JPanel orderPainel = new JPanel();
    private JTable table = new JTable();
    private JScrollPane scrollPane = new JScrollPane(table);
    private JButton btnVoltar = new JButton("Voltar");
    private JLabel orderBy = new JLabel("Ordenado por nivel:");
    private String[] dificuldade = {"Facil", "Impossivel"};
    private JComboBox listBox = new JComboBox(dificuldade);

    private int posicao = 1;
    private BufferedImage title;
    private JLabel ranking;
    private int jogDificuldade = 0;

    public Leadboards() {
        setDesignTela();
        setAction();
    }

    @Override
    public void run() {
        table.setGridColor(Color.BLACK);
        table.setShowGrid(true);

        List<String> colunas = new ArrayList<>();
        List<String[]> linhas = new ArrayList<>();
        colunas.add("Posição");
        colunas.add("Player");
        colunas.add("Score");
        colunas.add("Vitórias");
        colunas.add("Data do jogo");

        try {
            List<JogadorDados> dados = Controller.selectDados(jogador, jogDificuldade);
            for (JogadorDados jogador : dados) {
                linhas.add(new String[]{String.valueOf(posicao), jogador.getNome(),
                    String.valueOf(jogador.getScore()), String.valueOf(jogador.getQtdVitorias()),
                    String.valueOf(jogador.getDataJogo())});
                posicao++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TableModel tm = new DefaultTableModel(linhas.toArray(new String[][]{}), colunas.toArray());
        table.setModel(tm);
        table.setEnabled(false);

        scrollPane.getViewport().setBorder(null);

        painel.add(ranking);
        painel.add(orderPainel);
        painel.add(scrollPane);
        painel.add(Box.createHorizontalStrut(15));
        painel.add(btnVoltar);

        Principal.frame.getContentPane().removeAll();
        Principal.frame.getContentPane().add(painel);
        Principal.frame.invalidate();
        Principal.frame.validate();

    }

    private void setAction() {
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Principal().processar();
            }
        });
        listBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posicao = 1;
                jogDificuldade = listBox.getSelectedIndex();
                run();
            }
        });
    }

    @Override
    public void setDesignTela() {
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        try {
            title = ImageIO.read(new File("src/image/ranking.png"));
            ranking = new JLabel(new ImageIcon(title));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ranking.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderBy.setAlignmentX(Component.LEFT_ALIGNMENT);
        listBox.setPreferredSize(new Dimension(100, 25));
        listBox.setSelectedIndex(0);
        listBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
        orderPainel.add(orderBy);
        orderPainel.add(listBox);
    }
}
