import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principal {

	protected static JFrame frame = new JFrame("Jogo da Velha - v1.0");

	private JPanel painel = new JPanel();
	private JButton btnStart = new JButton("Iniciar");
	private JButton btnLeadboard = new JButton("Leadboards");

	public static void main(String[] args) {
		new Principal().processar();
	}

	protected void processar() {

		frame.getContentPane().removeAll();

		painel.add(btnStart);
		painel.add(btnLeadboard);

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
				Jogo j = new Jogo();
				j.start();
			}
		});

		btnLeadboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Leadboards leadboards = new Leadboards();
			}
		});
	}
}
