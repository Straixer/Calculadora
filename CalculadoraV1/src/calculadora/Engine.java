package calculadora;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Engine {

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
	public void Engine() {
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
	}

}
