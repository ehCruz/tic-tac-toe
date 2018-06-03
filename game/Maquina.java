import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class Maquina extends Jogo {

	JButton[] arrBotoes;
	JogadaAleatoria jogada;
	Color oColor = new Color(100, 17, 216);
	
	public Maquina(JButton[] arrBotoes) {
		this.arrBotoes = arrBotoes;
		if (Jogo.venceu == false) {
			start();
		}
	}

	@Override
	public void run() {	
		gerarAleatorio();
	}

	private void gerarAleatorio() {
		boolean cond = true;
		while (cond) {
			jogada = teste();
			if (Jogo.matizPosicoes[jogada.getLinha()][jogada.getColuna()] != 1
					&& Jogo.matizPosicoes[jogada.getLinha()][jogada.getColuna()] != 2) {
				Jogo.matizPosicoes[jogada.getLinha()][jogada.getColuna()] = 2;
				cond = false;
			}
		}
		setMaquinaJogada(jogada.getLinha(), jogada.getColuna());
	}

	private JogadaAleatoria teste() {
		int randLinha = (int) (Math.random() * 3);
		int randColuna = (int) (Math.random() * 3);

		return new JogadaAleatoria(randLinha, randColuna);
	}

	private void setMaquinaJogada(int linha, int coluna) {
		if (linha == 0 && coluna == 0) {
			setBotaoTexto(arrBotoes[0]);
		} else if (linha == 0 && coluna == 1) {
			setBotaoTexto(arrBotoes[1]);
		} else if (linha == 0 && coluna == 2) {
			setBotaoTexto(arrBotoes[2]);
		} else if (linha == 1 && coluna == 0) {
			setBotaoTexto(arrBotoes[3]);
		} else if (linha == 1 && coluna == 1) {
			setBotaoTexto(arrBotoes[4]);
		} else if (linha == 1 && coluna == 2) {
			setBotaoTexto(arrBotoes[5]);
		} else if (linha == 2 && coluna == 0) {
			setBotaoTexto(arrBotoes[6]);
		} else if (linha == 2 && coluna == 1) {
			setBotaoTexto(arrBotoes[7]);
		} else if (linha == 2 && coluna == 2) {
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
