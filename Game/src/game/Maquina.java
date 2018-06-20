package game;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;

public class Maquina extends Jogo {

    List<Integer> melhorJogada;
    JButton[] arrBotoes;
    Color oColor = new Color(100, 17, 216);

    public Maquina(JButton[] arrBotoes) {
        this.arrBotoes = arrBotoes;
        if (Jogo.venceu == false) {
            start();
        }
    }

    @Override
    public void run() {
        jogada();
    }

    private void jogada() {
        if (Jogo.dificuldade == 0) {
            melhorJogada = new EasyMode().verificaAleatorio();
            setMaquinaJogada(melhorJogada);
        } else if (Jogo.dificuldade == 1) {
            melhorJogada = new Minimax().findBestMove(Jogo.matizPosicoes);
            setMaquinaJogada(melhorJogada);
        }
    }

    private void setMaquinaJogada(List<Integer> jogada) {
        if (jogada.get(0) == 0 && jogada.get(1) == 0) {
            setBotaoTexto(arrBotoes[0]);
        } else if (jogada.get(0) == 0 && jogada.get(1) == 1) {
            setBotaoTexto(arrBotoes[1]);
        } else if (jogada.get(0) == 0 && jogada.get(1) == 2) {
            setBotaoTexto(arrBotoes[2]);
        } else if (jogada.get(0) == 1 && jogada.get(1) == 0) {
            setBotaoTexto(arrBotoes[3]);
        } else if (jogada.get(0) == 1 && jogada.get(1) == 1) {
            setBotaoTexto(arrBotoes[4]);
        } else if (jogada.get(0) == 1 && jogada.get(1) == 2) {
            setBotaoTexto(arrBotoes[5]);
        } else if (jogada.get(0) == 2 && jogada.get(1) == 0) {
            setBotaoTexto(arrBotoes[6]);
        } else if (jogada.get(0) == 2 && jogada.get(1) == 1) {
            setBotaoTexto(arrBotoes[7]);
        } else if (jogada.get(0) == 2 && jogada.get(1) == 2) {
            setBotaoTexto(arrBotoes[8]);
        }
    }

    private void setBotaoTexto(JButton botaoMaquina) {
        try {
            sleep(500);
            botaoMaquina.setText("O");
            botaoMaquina.setFont(new Font("Arial", Font.BOLD, 60));
            botaoMaquina.setForeground(oColor);
            verificaGame(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
