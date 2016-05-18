import javax.swing.*;     
import java.awt.*; 

//Esta classe implementa o placar.

public class Placar extends JPanel{

	private JTextArea textArea = new JTextArea(2,30);
	private int n_Propina;
	private int n_Prisoes;
	
	//Construtor
	public Placar ()
	{
		textArea.setEditable(false);
		n_Propina = 0;
		n_Prisoes = 0;
		add(textArea);
		Show_Placar();
	}
	
	//Mostra placar.
	public void Show_Placar ()
	{
		textArea.setText("");
		textArea.append("Propina: R$" + n_Propina + "Bilhões \nPrisões: " + n_Prisoes);
	}
	
	//Setar o número do prisões com n_Prisoes
	public void set_Prisao (int n_Prisoes)
	{
		this.n_Prisoes = n_Prisoes;
	}
	
	//Setar o número de propinas com n_Propina
	public void set_Propina (int n_Propina)
	{
		this.n_Propina = n_Propina;
	}
	
}
