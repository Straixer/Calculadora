package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Clase que contiene la calculadora
 */
public class Engine implements ActionListener {

	// Marco de la ventana
	private JFrame frame;

	// Barra de herramientas
	private JMenuBar menuBar;
	private JMenu menu;
	private JCheckBoxMenuItem itemDarkMode;

	// Panel general que ocupa toda la ventana
	private JPanel contentPanel;
	// Panel norte que contiene el display
	private JPanel displayPanel;
	private JPanel datosPanel;
	// Panel sur que contiene los botones
	private JPanel buttonPanel;
	// Display
	private JTextField display;

	private JLabel base;
	private JButton casio;
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
	private JButton a;
	private JButton b;
	private JButton c;
	private JButton d;
	private JButton e;
	private JButton f;
	private JButton info;
	private JButton owner;
	private JButton b2;
	private JButton b8;
	private JButton b10;
	private JButton b16;

	// Tipos de boton
	private enum ButtonType {
		REGULAR, OPERATOR, BASE, LETTER, ESPECIAL
	}

	// Tipos de base numérica
	private enum Base {
		DECIMAL, BINARIO, HEXADECIMAL, OCTAL
	}

	// Patron de separación del display
	private String patron;
	private Pattern pattern;

	// Almacenar temporalmente ciertos valores
	private int num1, num2, result;
	private char operation;
	private Base bs;

	// QUE TIPO DE INTERFAZ TIENE QUE MOSTRAR
	private boolean lightMode;

	// COLORES
	private Color colorRegular;
	private Color colorOperador;
	private Color colorBase;
	private Color colorLetter;
	private Color colorEspecial;
	private Color colorLetraRegular;
	private Color colorLetraOperador;
	private Color colorLetraBase;
	private Color colorLetraLetter;
	private Color colorLetraEspecial;
	private Color colorDisplay;

	/**
	 * Constructora que instancia todos los atributos
	 */
	public Engine() {
		// INSTANCIAR EL MARCO DE LA VENTANA
		this.frame = new JFrame("Calculadora V1");

		// INSTANCIAR LA BARRA DE HERRAMIENTAS
		this.menuBar = new JMenuBar();
		this.menu = new JMenu("Opciones");
		this.itemDarkMode = new JCheckBoxMenuItem("Modo Oscuro");

		// INSTANCIAR PANEL
		this.contentPanel = new JPanel();
		// INSTANCIAR EL PANEL DE EL DISPLAY
		this.displayPanel = new JPanel();

		this.base = new JLabel("Introduce una base");
		this.casio = new JButton("CASIO");

		this.datosPanel = new JPanel();
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
		this.a = new JButton("A");
		this.b = new JButton("B");
		this.c = new JButton("C");
		this.d = new JButton("D");
		this.e = new JButton("E");
		this.f = new JButton("F");
		this.b2 = new JButton("B2");
		this.b8 = new JButton("B8");
		this.b10 = new JButton("B10");
		this.b16 = new JButton("B16");
		this.info = new JButton("Info");
		this.owner = new JButton("Owner");

		// PATRON QUE COMPRUEBA SI EL NUMERO TIENE EL FORMATO DE DIGITO+OPERADOR+DIGITO
		this.patron = "(-?\\d+)([+\\-X/])(-?\\d+)";
		this.pattern = Pattern.compile(patron);

		// POR DEFECTO TIENE EL MODO CLARO
		this.lightMode = true;

		// COLORES
		this.colorRegular = new Color(12, 129, 182);
		this.colorOperador = new Color(14, 233, 190);
		this.colorBase = new Color(27, 113, 255);
		this.colorLetter = new Color(14, 233, 190);
		this.colorEspecial = new Color(14, 233, 190);
		this.colorLetraRegular = new Color(255, 230, 195);
		this.colorLetraOperador = new Color(0, 0, 0);
		this.colorDisplay = new Color(163, 233, 255);

		setSettings();
		addActionEvent();
	}

	/**
	 * Metodo que configura la interfaz de la calculadora
	 */
	public void setSettings() {

		// LLAMO AL METODO QUE PONE LOS COLORES DE LA INTERFAZ QUE PUEDEN CAMBIAR
		changeColors();

		this.contentPanel.setLayout(new BorderLayout());
		this.contentPanel.setBackground(new Color(255, 230, 195));

		this.displayPanel.setLayout(new GridLayout(2, 1));
		this.displayPanel.setBackground(new Color(255, 230, 195));

		this.datosPanel.setLayout(new GridLayout(1, 2));
		this.datosPanel.setBackground(this.colorDisplay);

		this.buttonPanel.setLayout(new GridLayout(7, 4));
		this.buttonPanel.setBackground(new Color(255, 230, 195));

		// CONFIGURAR EL DISPLAY
		this.display.setFont(new Font("Fuente Display", Font.PLAIN, 30));
		this.display.setHorizontalAlignment(JTextField.RIGHT);
		this.display.setBackground(this.colorDisplay);
		this.display.setBorder(new EmptyBorder(0, 0, 0, 0));

		// AÑADIR LA BARRA DE HERRAMIENTAS
		this.menu.add(itemDarkMode);
		this.menuBar.add(menu);
		this.frame.setJMenuBar(menuBar);

		this.datosPanel.add(this.base);
		setFeaturesButton(this.casio, ButtonType.ESPECIAL);
		this.datosPanel.add(this.casio);

		this.displayPanel.add(this.datosPanel);
		this.displayPanel.add(display);

		this.buttonPanel.add(b2);
		this.buttonPanel.add(b8);
		this.buttonPanel.add(b10);
		this.buttonPanel.add(b16);
		this.buttonPanel.add(a);
		this.buttonPanel.add(b);
		this.buttonPanel.add(c);
		this.buttonPanel.add(info);
		this.buttonPanel.add(d);
		this.buttonPanel.add(e);
		this.buttonPanel.add(f);
		this.buttonPanel.add(owner);
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
		this.frame.setSize(400, 400);
		this.frame.setVisible(true);
	}

	/**
	 * Metodo que añade todos los action listener de la calculadora
	 */
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
		this.itemDarkMode.addActionListener(this);
		this.a.addActionListener(this);
		this.b.addActionListener(this);
		this.c.addActionListener(this);
		this.d.addActionListener(this);
		this.e.addActionListener(this);
		this.f.addActionListener(this);
		this.b2.addActionListener(this);
		this.b8.addActionListener(this);
		this.b10.addActionListener(this);
		this.b16.addActionListener(this);
		this.owner.addActionListener(this);
		this.info.addActionListener(this);
		this.casio.addActionListener(this);
	}

	/**
	 * Metodo que cambia la apariencia de los botones
	 * 
	 * @param _button
	 * @param _type
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {
		// SI ES REGULAR LE PINTO EL FONDO DE AZUL
		if (_type == ButtonType.REGULAR) {
			_button.setBackground(this.colorRegular);
			_button.setForeground(this.colorLetraRegular);
		} else if (_type == ButtonType.OPERATOR) {
			// SIE ES OPERADOR LE PINTO EL FONDO DE COLOR TURQUESA
			_button.setBackground(this.colorOperador);
			_button.setForeground(this.colorLetraOperador);
		} else if (_type == ButtonType.BASE) {
			// SIE ES OPERADOR LE PINTO EL FONDO DE COLOR TURQUESA
			_button.setBackground(this.colorBase);
			_button.setForeground(this.colorLetraBase);
		} else if (_type == ButtonType.LETTER) {
			// SIE ES OPERADOR LE PINTO EL FONDO DE COLOR TURQUESA
			_button.setBackground(this.colorLetter);
			_button.setForeground(this.colorLetraLetter);
		} else if (_type == ButtonType.ESPECIAL) {
			// SIE ES OPERADOR LE PINTO EL FONDO DE COLOR TURQUESA
			_button.setBackground(this.colorEspecial);
			_button.setForeground(this.colorLetraEspecial);
		}

		_button.setBorder(new LineBorder(new Color(255, 230, 195), 1, true));// LE PONGO UN BORDE DE COLOR NARANJA CLARO
		_button.setFont(new Font("Nombre", Font.PLAIN, 16));// LE PONGO UNA FUENTE MAS GRANDE
		_button.setFocusable(false);// NO SE PUEDE QUEDAR SEÑALADO
	}

	/**
	 * Metodo que el boton pulsado y ejecuta la función de este
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String input_text = e.getActionCommand();
		// SE COMPRUEBA SI SE HA PULSADO ALGUNA TECLA Y ACTIVA SU FINCIONALIDAD
		if (input_text.equals("Modo Oscuro")) {
			if (itemDarkMode.isSelected()) {
				this.lightMode = false;
			} else {
				this.lightMode = true;
			}
			changeColors();
		} else if (input_text.equals("=")) {
			// REALIZA LA OPERACIÓN CORRESPONDIENTE
			operation();
		} else if (input_text.equals("R")) {
			// SE PONE LA PANTALLA VACIA
			this.display.setText("");
			this.base.setText("Introduce una base");
			// LOS VALORES DE LOS NUMEROS SE PONEN A 0
			this.num1 = 0;
			this.num2 = 0;
			this.bs = null;
		} else if (input_text.equals("B2")) {
			this.base.setText("Base 2");
			bs = Base.BINARIO;
		} else if (input_text.equals("B8")) {
			this.base.setText("Base 8");
			bs = Base.OCTAL;
		} else if (input_text.equals("B10")) {
			this.base.setText("Base 10");
			bs = Base.DECIMAL;
		} else if (input_text.equals("B16")) {
			this.base.setText("Base 16");
			bs = Base.HEXADECIMAL;
		} else if (input_text.equals("Info")) {
			VentanaEmergente v = new VentanaEmergente("Ventana informativa, quiero sacar un 10");
		} else if (input_text.equals("Owner")) {
			VentanaEmergente v = new VentanaEmergente("Ventana de propietario, quiero sacar un 10");
		} else if (input_text.equals("CASIO")) {
			try {
				Desktop.getDesktop().browse(new URI("https://www.casio.com/es/"));
			} catch (IOException e1) {
			} catch (URISyntaxException e1) {

			}
		} else {
			// ESCRIBE LA TECLA POR PANTALLA
			this.display.setText(this.display.getText() + input_text);
		}

	}

	/**
	 * Metodo en el que se realiza la lógica de las operaciones y se obtiene la
	 * operación a realizar
	 */
	public void operation() {
		// ACTUALIZO LA CADENA DEL PATRON
		numerosaDecimal();

		// COMPRUEBA QUE LA CADENA DEL DISPLAY SEA CORRECTA

		// SWITCH PARA CADA OPERADOR
		switch (this.operation) {
		case '+': {
			this.result = num1 + num2;
			break;
		}
		case '-': {
			this.result = this.num1 - this.num2;
			break;
		}
		case 'X': {
			this.result = num1 * num2;
			break;
		}
		case '/': {
			this.result = num1 / num2;

			break;
		}

		}
		// SE MUESTRA EL RESULTADO POR PANTALLA
		escribirNumeros();
		// LOS OTROS VALORES SE PONEN A 0
		this.num1 = 0;
		this.num2 = 0;
		this.result = 0;

	}

	/**
	 * Metodo que cambia el tema de la interfaz
	 */
	public void changeColors() {
		if (this.lightMode) {
			this.colorRegular = new Color(12, 129, 182);
			this.colorOperador = new Color(14, 233, 190);
			this.colorBase = new Color(27, 113, 255);
			this.colorLetter = new Color(27, 255, 150);
			this.colorEspecial = new Color(255, 27, 113);
			this.colorLetraRegular = new Color(255, 255, 255);
			this.colorLetraOperador = new Color(0, 0, 0);
			this.colorLetraBase = new Color(0, 0, 0);
			this.colorLetraLetter = new Color(0, 0, 0);
			this.colorLetraEspecial = new Color(0, 0, 0);
			this.colorDisplay = new Color(163, 233, 255);
		} else {
			this.colorRegular = new Color(54, 48, 98);
			this.colorOperador = new Color(67, 85, 133);
			this.colorBase = new Color(169, 27, 255);
			this.colorLetter = new Color(255, 27, 132);
			this.colorEspecial = new Color(14, 233, 190);
			this.colorLetraRegular = new Color(255, 230, 195);
			this.colorLetraOperador = new Color(255, 255, 255);
			this.colorLetraBase = new Color(0, 0, 0);
			this.colorLetraLetter = new Color(0, 0, 0);
			this.colorLetraEspecial = new Color(0, 0, 0);
			this.colorDisplay = new Color(129, 143, 180);
		}

		this.display.setBackground(colorDisplay);

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

		// CONFIGURAR BOTONES BASE
		setFeaturesButton(b2, ButtonType.BASE);
		setFeaturesButton(b8, ButtonType.BASE);
		setFeaturesButton(b10, ButtonType.BASE);
		setFeaturesButton(b16, ButtonType.BASE);

		// CONFIGURAR BOTONES LETTER
		setFeaturesButton(a, ButtonType.LETTER);
		setFeaturesButton(b, ButtonType.LETTER);
		setFeaturesButton(c, ButtonType.LETTER);
		setFeaturesButton(d, ButtonType.LETTER);
		setFeaturesButton(e, ButtonType.LETTER);
		setFeaturesButton(f, ButtonType.LETTER);

		// CONFIGURAR BOTONES ESPECIALES
		setFeaturesButton(info, ButtonType.ESPECIAL);
		setFeaturesButton(owner, ButtonType.ESPECIAL);

		this.datosPanel.setBackground(this.colorDisplay);

	}

	/**
	 * Pasa el número del display a decimal para poder operar con los valores
	 */
	public void numerosaDecimal() {
		Matcher matcher;
		if (bs == Base.DECIMAL) {
			this.patron = "(-?\\d+)([+\\-X/])(-?\\d+)";
			this.pattern = Pattern.compile(patron);
			matcher = pattern.matcher(this.display.getText());
			if (matcher.find()) {
				this.num1 = Integer.parseInt(matcher.group(1));
				this.operation = matcher.group(2).charAt(0);
				this.num2 = Integer.parseInt(matcher.group(3));
			}
		} else if (bs == Base.BINARIO) {
			this.patron = "(-?\\d+)([+\\-X/])(-?\\d+)";
			this.pattern = Pattern.compile(patron);
			matcher = pattern.matcher(this.display.getText());
			if (matcher.find()) {

				this.num1 = Integer.parseInt(matcher.group(1), 2);
				this.operation = matcher.group(2).charAt(0);
				this.num2 = Integer.parseInt(matcher.group(3), 2);
			}
		} else if (bs == Base.OCTAL) {
			this.patron = "(-?\\d+)([+\\-X/])(-?\\d+)";
			this.pattern = Pattern.compile(patron);
			matcher = pattern.matcher(this.display.getText());
			if (matcher.find()) {

				this.num1 = Integer.parseInt(matcher.group(1), 8);
				this.operation = matcher.group(2).charAt(0);
				this.num2 = Integer.parseInt(matcher.group(3), 8);
			}
		} else if (bs == Base.HEXADECIMAL) {
			this.patron = "(-?\\p{XDigit}+)([+\\-X/])(-?\\p{XDigit}+)";
			this.pattern = Pattern.compile(patron);
			matcher = pattern.matcher(this.display.getText());
			if (matcher.find()) {
				this.num1 = Integer.parseInt(matcher.group(1), 16);
				this.operation = matcher.group(2).charAt(0);
				this.num2 = Integer.parseInt(matcher.group(3), 16);
			}
		}

	}

	/**
	 * Pasa el número a la base correspondiente y lo escribe en el display
	 */
	public void escribirNumeros() {
		String res = "Error";
		if (bs == Base.DECIMAL) {
			res = "" + this.result;
		} else if (bs == Base.BINARIO) {
			if (this.result < 0) {
				this.result = this.result * -1;
				res = "-" + Integer.toBinaryString(this.result);
			} else {
				res = Integer.toBinaryString(this.result);
			}

		} else if (bs == Base.OCTAL) {
			if (this.result < 0) {
				this.result = this.result * -1;
				res = "-" + Integer.toOctalString(this.result);
			} else {
				res = Integer.toOctalString(this.result);
			}

		} else if (bs == Base.HEXADECIMAL) {
			if (this.result < 0) {
				this.result = this.result * -1;
				res = "-" + Integer.toHexString(this.result).toUpperCase();
			} else {
				res = Integer.toHexString(this.result).toUpperCase();
			}

		}

		this.display.setText(res);
	}
}
