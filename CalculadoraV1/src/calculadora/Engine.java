package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Engine implements ActionListener {

	// Marco de la ventana
	private JFrame frame;
	// Panel general que ocupa toda la ventana
	private JPanel contentPanel;
	// Panel norte que contiene el display
	private JPanel displayPanel;
	// Panel sur que contiene los botones
	private JPanel buttonPanel;
	// Display
	private JTextField display;
	// Botones
	private JButton n0;
	private JButton n1;
	private JButton n2;
	private JButton n3;
	private JButton n4;
	private JButton n5;
	private JButton n6;
	private JButton n7;
	private JButton n8;
	private JButton n9;
	private JButton divide;
	private JButton multiply;
	private JButton subtract;
	private JButton add;
	private JButton equal;
	private JButton reset;

	// Tipos de boton
	private enum ButtonType {
		REGULAR, OPERATOR
	}

	// Almacenar temporalmente ciertos valores
	private int num1, num2, result;
	private char operation;

	/**
	 * CONSTRUCTORA QUE INSTANCIA TODOS LOS ATRIBUTOS
	 */
	public Engine() {
		// INSTANCIAR EL MARCO DE LA VENTANA
		this.frame = new JFrame("Calculadora V1");
		// INSTANCIAR PANEL
		this.contentPanel = new JPanel();
		// INSTANCIAR EL PANEL DE EL DISPLAY
		this.displayPanel = new JPanel();
		// INSTANCIAR EL PANEL QUE VA A CONTENER LOS BOTONES
		this.buttonPanel = new JPanel();

		// INSTANCIAR EL TEXTO DEL DISPLAY
		this.display = new JTextField();

		// INSTANCIAR LOS BOTONES
		this.n0 = new JButton("0");
		this.n1 = new JButton("1");
		this.n2 = new JButton("2");
		this.n3 = new JButton("3");
		this.n4 = new JButton("4");
		this.n5 = new JButton("5");
		this.n6 = new JButton("6");
		this.n7 = new JButton("7");
		this.n8 = new JButton("8");
		this.n9 = new JButton("9");
		this.divide = new JButton("/");
		this.multiply = new JButton("X");
		this.subtract = new JButton("-");
		this.add = new JButton("+");
		this.equal = new JButton("=");
		this.reset = new JButton("R");

		setSettings();
		addActionEvent();
	}

	public void setSettings() {
		this.contentPanel.setLayout(new BorderLayout());
		this.contentPanel.setBackground(new Color(255, 230, 195));

		this.displayPanel.setLayout(new GridLayout(1, 1));
		this.displayPanel.setBackground(new Color(255, 230, 195));

		this.buttonPanel.setLayout(new GridLayout(4, 4));
		this.buttonPanel.setBackground(new Color(255, 230, 195));

		// CONFIGURAR EL DISPLAY
		this.display.setFont(new Font("Fuente Display", Font.PLAIN, 20));
		this.display.setHorizontalAlignment(JTextField.RIGHT);
		this.display.setBackground(new Color(163, 233, 255));
		this.display.setBorder(new EmptyBorder(0, 0, 0, 0));

		// CONFIGURAR BOTONES REGULARES
		setFeaturesButton(n0, ButtonType.REGULAR);
		setFeaturesButton(n1, ButtonType.REGULAR);
		setFeaturesButton(n2, ButtonType.REGULAR);
		setFeaturesButton(n3, ButtonType.REGULAR);
		setFeaturesButton(n4, ButtonType.REGULAR);
		setFeaturesButton(n5, ButtonType.REGULAR);
		setFeaturesButton(n6, ButtonType.REGULAR);
		setFeaturesButton(n7, ButtonType.REGULAR);
		setFeaturesButton(n8, ButtonType.REGULAR);
		setFeaturesButton(n9, ButtonType.REGULAR);

		// CONFIGURAR BOTONES OPERADORES
		setFeaturesButton(divide, ButtonType.OPERATOR);
		setFeaturesButton(multiply, ButtonType.OPERATOR);
		setFeaturesButton(subtract, ButtonType.OPERATOR);
		setFeaturesButton(add, ButtonType.OPERATOR);
		setFeaturesButton(equal, ButtonType.OPERATOR);
		setFeaturesButton(reset, ButtonType.OPERATOR);

		this.displayPanel.add(display);

		this.buttonPanel.add(n7);
		this.buttonPanel.add(n8);
		this.buttonPanel.add(n9);
		this.buttonPanel.add(add);
		this.buttonPanel.add(n4);
		this.buttonPanel.add(n5);
		this.buttonPanel.add(n6);
		this.buttonPanel.add(subtract);
		this.buttonPanel.add(n1);
		this.buttonPanel.add(n2);
		this.buttonPanel.add(n3);
		this.buttonPanel.add(multiply);
		this.buttonPanel.add(n0);
		this.buttonPanel.add(reset);
		this.buttonPanel.add(equal);
		this.buttonPanel.add(divide);

		// AÑADO LOS PANELES AL PANEL PRINCIPAL
		this.contentPanel.add(displayPanel, BorderLayout.NORTH);
		this.contentPanel.add(buttonPanel, BorderLayout.CENTER);

		// AÑADO EL PANEL PRINCIPAL A LA VENTANA
		this.frame.add(contentPanel);
		// CONFIGURO LA VENTANA PARA QUE APAREZCA EN PANTALLA Y SE CIERRE
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(300, 300);
		this.frame.setVisible(true);
	}

	// setFeaturesButton(JButton _button, ButtonType _type){}

	public void addActionEvent() {
		this.n0.addActionListener(this);
		this.n1.addActionListener(this);
		this.n2.addActionListener(this);
		this.n3.addActionListener(this);
		this.n4.addActionListener(this);
		this.n5.addActionListener(this);
		this.n6.addActionListener(this);
		this.n7.addActionListener(this);
		this.n8.addActionListener(this);
		this.n9.addActionListener(this);
		this.divide.addActionListener(this);
		this.multiply.addActionListener(this);
		this.subtract.addActionListener(this);
		this.add.addActionListener(this);
		this.equal.addActionListener(this);
		this.reset.addActionListener(this);
	}

	public void setFeaturesButton(JButton _button, ButtonType _type) {
		// SI ES REGULAR LE PINTO EL FONDO DE AZUL
		if (_type == ButtonType.REGULAR) {
			_button.setBackground(new Color(12, 129, 182));
			_button.setForeground(Color.white);// COLOR DEL TEXTO BLANCO
		} else if (_type == ButtonType.OPERATOR) {
			// SIE ES OPERADOR LE PINTO EL FONDO DE COLOR TURQUESA
			_button.setBackground(new Color(14, 233, 190));

		}

		_button.setBorder(new LineBorder(new Color(255, 230, 195), 1, true));// LE PONGO UN BORDE DE COLOR NARANJA CLARO
		_button.setFont(new Font("Nombre", Font.PLAIN, 16));// LE PONGO UNA FUENTE MÁS GRANDE
		_button.setFocusable(false);// NO SE PUEDE QUEDAR SEÑALADO
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		String input_text = e.getActionCommand();

		if (input_text.equals("=")) {
			operation();
		} else if (input_text.equals("X")) {
			this.display.setText(this.display.getText() + input_text);
			this.operation = 'X';
		} else if (input_text.equals("/")) {
			this.display.setText(this.display.getText() + input_text);
			this.operation = '/';
		} else if (input_text.equals("+")) {
			this.display.setText(this.display.getText() + input_text);
			if (this.operation != 'X' && this.operation != '/') {
				this.operation = '+';
			}

		} else if (input_text.equals("-")) {
			this.display.setText(this.display.getText() + input_text);
			if (this.operation != 'X' && this.operation != '/' && this.operation != '+') {
				this.operation = '-';
			}

		} else if (input_text.equals("R")) {
			this.display.setText("");
			this.num1 = 0;
			this.num2 = 0;
			this.operation = '.';
		} else {
			this.display.setText(this.display.getText() + input_text);
		}

	}

	public void operation() {
		switch (this.operation) {
		case '+': {
			this.num1 = Integer.parseInt(this.display.getText().split("\\+")[0]);
			this.num2 = Integer.parseInt(this.display.getText().split("\\+")[1]);
			this.result = num1 + num2;
			endOperation();
			break;
		}
		case '-': {
			String separacion[] = this.display.getText().split("(?<=\\d)(?=[-+*/])|(?<=[-+*/])(?=\\d)");
			// this.num1 = Integer.parseInt(this.display.getText().split("-")[0]);
			// this.num2 = Integer.parseInt(this.display.getText().split("-")[1]);
			//this.num1 = Integer.parseInt(separacion[0]);
			//this.num1 = Integer.parseInt(separacion[2]);
			//this.result = num1 - num2;
			// this.display.setText(separacion[0] + " a " + separacion[2]);
			//endOperation();
			for(String elemento: separacion) {
				this.display.setText("\\"+this.display.getText()+elemento+"/");
			}
			break;
		}
		case 'X': {
			this.num1 = Integer.parseInt(this.display.getText().split("X")[0]);
			this.num2 = Integer.parseInt(this.display.getText().split("X")[1]);
			this.result = num1 * num2;
			endOperation();
			break;
		}
		case '/': {
			this.num1 = Integer.parseInt(this.display.getText().split("/")[0]);
			this.num2 = Integer.parseInt(this.display.getText().split("/")[1]);
			this.result = num1 / num2;
			endOperation();
			break;
		}
		case '.': {

		}
		}
	}

	/**
	 * METODO QUE MUESTRA EL RESULTADO Y CAMBIA LAS VARIABLES
	 */
	public void endOperation() {
		this.display.setText("" + result);
		this.num1 = this.result;
		this.num2 = 0;
		this.operation = '.';// CAMBIA EL OPERADOR A UNO QUE NO HACE NADA
	}

}
