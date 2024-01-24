package calculadora;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaEmergente implements ActionListener {
	private JFrame frame;
	private JPanel panel;
	private JLabel texto;
	private JButton boton;

	public VentanaEmergente(String t) {

		this.frame = new JFrame();
		this.panel = new JPanel();
		panel.setLayout(new BorderLayout());

		this.texto = new JLabel(
				t);
		this.boton = new JButton("Ok");

		this.boton.addActionListener(this);
		
		this.panel.add(texto,BorderLayout.NORTH);
		this.panel.add(boton,BorderLayout.SOUTH);

		this.frame.add(panel);
		this.frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		this.frame.setSize(700, 100);
		this.frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		this.frame.dispose();

	}
}
