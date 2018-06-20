package game;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Principal implements DesignTela {

    protected static JFrame frame = new JFrame("Jogo da Velha - v2.0 beta");

    private JPanel painel = new JPanel();
    private JButton btnStart = new JButton("Iniciar");
    private JButton btnLeadboard = new JButton("Ranking");
    private BufferedImage title;
    private BufferedImage logo;
    private JLabel imgLabel;
    private JLabel author = new JLabel("Desenvolvido por Eduardo H Cruz");
    private JLabel gitHub = new JLabel("github.com/ehCruz");

    public Principal() {
        setDesignTela();
    }

    public static void main(String[] args) {
        new Principal().processar();
    }

    protected void processar() {
        frame.getContentPane().removeAll();
        frame.setIconImage(logo);
        painel.add(Box.createVerticalStrut(10));
        painel.add(imgLabel);
        painel.add(Box.createVerticalStrut(25));
        painel.add(btnStart);
        painel.add(Box.createVerticalStrut(15));
        painel.add(btnLeadboard);
        painel.add(Box.createVerticalStrut(200));
        painel.add(author);
        painel.add(gitHub);
        frame.getContentPane().add(painel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.invalidate();
        frame.validate();
        frame.setSize(650, 520);
        frame.setLocationRelativeTo(null);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDificuldade();
            }
        });

        btnLeadboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Leadboards leadboards = new Leadboards();
                leadboards.start();
            }
        });
    }

    private void setDificuldade() {
        String[] opcoes = new String[]{"Sem graça de tao facil", "Voce NUNCA vai ganhar"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha a dificuldade do jogo:", "Dificuldade", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, opcoes, 3);
        if (opcao >= 0) {
            Jogo.dificuldade = opcao;
            System.out.println(Jogo.dificuldade);
            Jogo j = new Jogo();
            j.start();
        }
    }

    @Override
    public void setDesignTela() {
        try {
            title = ImageIO.read(new File("src/image/Logo.png"));
            logo = ImageIO.read(new File("src/image/app-logo.png"));
            imgLabel = new JLabel(new ImageIcon(title));
            imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLeadboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        author.setAlignmentX(Component.CENTER_ALIGNMENT);
        gitHub.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
    }
}
