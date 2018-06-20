package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Jogo extends Thread {

    static int[][] matizPosicoes;
    static boolean venceu = false;
    static JogadorDados jogador = new JogadorDados();
    static JButton[] arrayBotoesJogo;
    static int dificuldade;

    JLabel lblScore = new JLabel("Score:");
    JLabel lblScoreValue = new JLabel();
    JLabel lblVitorias = new JLabel("Vitórias:");
    JLabel lblVitoriasValue = new JLabel();

    String[] opcoes;
    Color colorGanhou = new Color(94, 204, 79);
    Color colorPerdeu = new Color(234, 30, 30);
    BotoesJogo botoesJogo;

    private int jogada = 0;
    boolean flagMaquina = true;

    @Override
    public void run() {
        jogador.setScore(0);
        matizPosicoes = new int[3][3];
        botoesJogo = new BotoesJogo();
        arrayBotoesJogo = botoesJogo.carregarBotoes();
        resetTela();
    }

    /**
     * <p>
     * Reseta os elementos dentro do JFrame
     * </p>
     *
     * @author eduardo
     */
    public void resetTela() {
        adicionarBotoes();
        Principal.frame.getContentPane().removeAll();
        Principal.frame.getContentPane().add(adicionarBotoes());
        Principal.frame.invalidate();
        Principal.frame.validate();
    }

    /**
     * <h1>Adicionar botões e informações do jogo</h1>
     * <p>
     * Adiciona os botões do jogo e também as informações de pontuação.
     * <p>
     *
     * @author eduardo
     * @return {@link JPanel}
     */
    private JPanel adicionarBotoes() {
        JPanel painel = new JPanel();
        JPanel painelBotoes = new JPanel();
        JPanel painelInfoJogador = new JPanel();
        painelBotoes.setPreferredSize(new Dimension(500, 470));
        painelInfoJogador.setPreferredSize(new Dimension(100, 480));

        for (int i = 0; i < arrayBotoesJogo.length; i++) {
            painelBotoes.add(arrayBotoesJogo[i]);
        }

        painelInfoJogador.add(lblScore);
        painelInfoJogador.add(lblScoreValue);

        painelInfoJogador.add(lblVitorias);
        painelInfoJogador.add(lblVitoriasValue);

        painel.add(painelBotoes, BorderLayout.PAGE_END);
        painel.add(painelInfoJogador, BorderLayout.PAGE_START);
        return painel;
    }

    /**
     * <h1>verificaGame</h1><br>
     * <p>
     * Verifica se algum dos jogadores ganhou de acordo com as posições da
     * matrizPosicoes, recebe como parâmetro uma variável do tipo inteiro, caso
     * seja igual a 1 verifica se o jogador ganhou, caso seja 2 verifica se a
     * maquina ganhou. Também faz a verificação se é vez da máquina fazer a
     * jogada.
     * </p>
     *
     * @author eduardo
     *
     * @param condicao
     * <p>
     * int = 1 ou 2
     * </p>
     */
    public void verificaGame(int condicao) {
        if (matizPosicoes[0][0] == condicao && matizPosicoes[0][1] == condicao && matizPosicoes[0][2] == condicao) {
            ganhou(arrayBotoesJogo[0], arrayBotoesJogo[1], arrayBotoesJogo[2], condicao);
        } else if (matizPosicoes[1][0] == condicao && matizPosicoes[1][1] == condicao
                && matizPosicoes[1][2] == condicao) {
            ganhou(arrayBotoesJogo[3], arrayBotoesJogo[4], arrayBotoesJogo[5], condicao);
        } else if (matizPosicoes[2][0] == condicao && matizPosicoes[2][1] == condicao
                && matizPosicoes[2][2] == condicao) {
            ganhou(arrayBotoesJogo[6], arrayBotoesJogo[7], arrayBotoesJogo[8], condicao);
        } else if (matizPosicoes[0][0] == condicao && matizPosicoes[1][0] == condicao
                && matizPosicoes[2][0] == condicao) {
            ganhou(arrayBotoesJogo[0], arrayBotoesJogo[3], arrayBotoesJogo[6], condicao);
        } else if (matizPosicoes[0][1] == condicao && matizPosicoes[1][1] == condicao
                && matizPosicoes[2][1] == condicao) {
            ganhou(arrayBotoesJogo[1], arrayBotoesJogo[4], arrayBotoesJogo[7], condicao);
        } else if (matizPosicoes[0][2] == condicao && matizPosicoes[1][2] == condicao
                && matizPosicoes[2][2] == condicao) {
            ganhou(arrayBotoesJogo[2], arrayBotoesJogo[5], arrayBotoesJogo[8], condicao);
        } else if (matizPosicoes[0][0] == condicao && matizPosicoes[1][1] == condicao
                && matizPosicoes[2][2] == condicao) {
            ganhou(arrayBotoesJogo[0], arrayBotoesJogo[4], arrayBotoesJogo[8], condicao);
        } else if (matizPosicoes[0][2] == condicao && matizPosicoes[1][1] == condicao
                && matizPosicoes[2][0] == condicao) {
            ganhou(arrayBotoesJogo[2], arrayBotoesJogo[4], arrayBotoesJogo[6], condicao);
        }
        jogada++;
        if (jogada == 5) {
            String mensagem = "EMPATE";
            verificaAcaoFinal(mensagem);
        }
        if (flagMaquina == false) {
            new Maquina(arrayBotoesJogo);
            flagMaquina = true;
        }

    }

    /**
     * <h1>Ganhou</h1>
     * <p>
     * É somente chamada quando um dos jogadores ganhou o jogo, se o jogador
     * ganhou set a sequência dos botões de verde, se a maquina ganhou set a
     * sequência de botões vermelho. Ao final realiza a chamada do método de
     * verificação final.
     * </p>
     *
     * @param btn1
     * @param btn2
     * @param btn3
     * @param j = 1 ou 2<br>
     * Se j = 1 jogador ganhou<br>
     * Se j = 2 máquina ganhou<br>
     *
     * @author eduardo
     */
    private void ganhou(JButton btn1, JButton btn2, JButton btn3, int j) {
        flagMaquina = true;
        try {
            if (j == 1) {
                btn1.setBackground(colorGanhou);
                btn2.setBackground(colorGanhou);
                btn3.setBackground(colorGanhou);
                jogador.setQtdVitorias(jogador.getQtdVitorias() + 1);
                venceu = true;
            } else {
                btn1.setBackground(colorPerdeu);
                btn2.setBackground(colorPerdeu);
                btn3.setBackground(colorPerdeu);
                venceu = true;
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String mensagem = "Voce ganhou";
        verificaAcaoFinal(mensagem);
    }

    /**
     * <h1>Primeira verificação</h1>
     * <p>
     * É exibido um JOptionPane, dando a opção para o usuário se ele deseja
     * salvar a pontução ou continuar jogando.
     * </p>
     *
     * @author eduardo
     */
    private void verificaAcaoFinal(String mensagem) {
        opcoes = new String[]{"Sim", "Não"};
        int opcao = JOptionPane.showOptionDialog(null, mensagem + " - Deseja salvar a pontuação?", "", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[1]);
        if (opcao == 0) {
            salvarDados();
        } else {
            novoJogo();
        }

    }

    /**
     * <h1>Salvar informções</h1>
     * <p>
     * Salva as informações do jogo atual, como pontuação e número de vitórias,
     * mas SOMENTE do jogo atual.
     * </p>
     *
     * @author eduardo
     */
    private void salvarDados() {
        JLabel lblNome = new JLabel("Nome do player:");
        JTextField tfNome = new JTextField(30);
        JButton btnSalvarInfo = new JButton("Salvar");

        JPanel painelInfo = new JPanel();

        painelInfo.add(lblNome);
        painelInfo.add(tfNome);
        painelInfo.add(btnSalvarInfo);

        Principal.frame.getContentPane().removeAll();
        Principal.frame.getContentPane().add(painelInfo);
        Principal.frame.invalidate();
        Principal.frame.validate();

        btnSalvarInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jogador.setNome(tfNome.getText());
                    Controller.inserirDados(jogador);
                    novoJogo();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    }

    /**
     * <h1>Novo Jogo</h1>
     * <p>
     * Da a opção ao jogador de iniciar um novo jogo, voltar ao menu ou sair.
     * </p>
     *
     * @author eduardo
     */
    private void novoJogo() {
        matizPosicoes = new int[3][3];
        System.out.println("------");
        flagMaquina = true;
        venceu = false;
        opcoes = new String[]{"Sim", "Não", "Voltar ao menu"};
        int opcao = JOptionPane.showOptionDialog(null, "Jogar novamente?", "", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[2]);
        switch (opcao) {
            case 0:
                lblVitoriasValue.setText(Integer.toString(jogador.getQtdVitorias()));
                botoesJogo = new BotoesJogo();
                arrayBotoesJogo = botoesJogo.carregarBotoes();
                resetTela();
                break;
            case 1:
                System.exit(0);
                break;
            case 2:
                new Principal().processar();
                break;
            default:
                new Principal().processar();
                break;
        }
    }

}
