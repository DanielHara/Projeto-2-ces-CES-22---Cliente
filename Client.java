import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
    
    public class Client extends JFrame
    {
       	public Client ()
    	{
       		String IPAdress = null;
       		String Porta = null;
       		int Port = 0;
       		
       		while(IPAdress == null || IPAdress.equals(""))
       			IPAdress = JOptionPane.showInputDialog("Digite o IP:");
       		
       		while(Porta == null || Porta.equals(""))
       			Porta = JOptionPane.showInputDialog("Digite a porta:");
       		
       		Port = Integer.parseInt(Porta);
       		
       		Board Tela = new Board(IPAdress, Port);
       		add(Tela, BorderLayout.WEST);
    		add(Tela.Placar_Jogo, BorderLayout.EAST);
    		pack();
    		setVisible(true);
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}
    	
    	public static void main (String[] args)
    	{
    		Client Jogo = new Client ();
    	}
    	
    }
