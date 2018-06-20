package game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BotoesJogo extends Jogo implements ActionListener {

    private JButton[] arrayBtnsJogo = new JButton[9];

    private JButton btnGameUm = new JButton();
    private JButton btnGameDois = new JButton();
    private JButton btnGameTres = new JButton();
    private JButton btnGameQuatro = new JButton();
    private JButton btnGameCinco = new JButton();
    private JButton btnGameSeis = new JButton();
    private JButton btnGameSete = new JButton();
    private JButton btnGameOito = new JButton();
    private JButton btnGameNove = new JButton();

    private JogadorAcao jogadorAcao = new JogadorAcao();

    protected JButton[] carregarBotoes() {
        btnGameUm.setText("");
        arrayBtnsJogo[0] = btnGameUm;
        arrayBtnsJogo[1] = btnGameDois;
        arrayBtnsJogo[2] = btnGameTres;
        arrayBtnsJogo[3] = btnGameQuatro;
        arrayBtnsJogo[4] = btnGameCinco;
        arrayBtnsJogo[5] = btnGameSeis;
        arrayBtnsJogo[6] = btnGameSete;
        arrayBtnsJogo[7] = btnGameOito;
        arrayBtnsJogo[8] = btnGameNove;
        adicionarEvento();
        return arrayBtnsJogo;
    }

    private void adicionarEvento() {
        for (int i = 0; i < arrayBtnsJogo.length; i++) {
            arrayBtnsJogo[i].setPreferredSize(new Dimension(150, 150));
            arrayBtnsJogo[i].setFocusable(false);
            arrayBtnsJogo[i].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGameUm) {
            jogadorAcao.setBotaoTexto(btnGameUm, 0, 0);
        } else if (e.getSource() == btnGameDois) {
            jogadorAcao.setBotaoTexto(btnGameDois, 0, 1);
        } else if (e.getSource() == btnGameTres) {
            jogadorAcao.setBotaoTexto(btnGameTres, 0, 2);
        } else if (e.getSource() == btnGameQuatro) {
            jogadorAcao.setBotaoTexto(btnGameQuatro, 1, 0);
        } else if (e.getSource() == btnGameCinco) {
            jogadorAcao.setBotaoTexto(btnGameCinco, 1, 1);
        } else if (e.getSource() == btnGameSeis) {
            jogadorAcao.setBotaoTexto(btnGameSeis, 1, 2);
        } else if (e.getSource() == btnGameSete) {
            jogadorAcao.setBotaoTexto(btnGameSete, 2, 0);
        } else if (e.getSource() == btnGameOito) {
            jogadorAcao.setBotaoTexto(btnGameOito, 2, 1);
        } else if (e.getSource() == btnGameNove) {
            jogadorAcao.setBotaoTexto(btnGameNove, 2, 2);
        }
    }
}
