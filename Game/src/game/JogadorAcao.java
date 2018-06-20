package game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class JogadorAcao extends Jogo {

    Color xColor = new Color(216, 17, 10);

    protected void setBotaoTexto(JButton btn, int linha, int coluna) {
        if (Jogo.matizPosicoes[linha][coluna] == 0 && Jogo.venceu == false) {
            Jogo.matizPosicoes[linha][coluna] = 1;
            btn.setText("X");
            btn.setFont(new Font("Arial", Font.BOLD, 60));
            btn.setForeground(xColor);
            flagMaquina = false;
            carregaInfoJogador();
            verificaGame(1);
        }
    }

    private void carregaInfoJogador() {
        resetTela();
        jogador.setScore(jogador.getScore() + 30);
        lblScoreValue.setText(Integer.toString(jogador.getScore()));
        lblVitoriasValue.setText(Integer.toString(jogador.getQtdVitorias()));
    }
}
