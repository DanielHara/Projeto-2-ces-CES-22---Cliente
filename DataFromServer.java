import java.io.*;

//Esta é a classe DataFromServer

public class DataFromServer extends Thread  {
	
	private int x_Lula, y_Lula, x_Japa, y_Japa, x_Propina, y_Propina, n_Prisoes, n_Propina;
	private Board B;
	private InputStream StreamFromServer;
	
	//Construtor
	
	public DataFromServer (Board B, InputStream StreamFromServer)
	{
		this.B = B;
		this.StreamFromServer = StreamFromServer;
		x_Lula = 0;
		y_Lula = 0;
		x_Japa = 0;
		y_Japa = 0;
		x_Propina = 0;
		y_Propina = 0;
		n_Prisoes = 0;
		n_Propina = 0;
	}
	
	public void run()
	{
		int bytesRead;
		
		byte[] reply = new byte[1024];
		String message = new String ("");
		while(true)
		{
			System.out.println("ESTOU NO WHILE!");
			try
			{
				//Com a string message, que vem do Servidor, encontramos as posições do Lula, do Japa e da Propina.
				System.out.println("ESTOU NO Try!");
				
				bytesRead = StreamFromServer.read(reply);
				
				System.out.println("Passei do Read!");
				
				message = new String (reply, "US-ASCII");
				
				System.out.println("Message = " + message);
				
				x_Lula = Integer.parseInt(message.substring(0, message.indexOf(',')));
				message = message.substring(1 + message.indexOf(','));
				
				y_Lula = Integer.parseInt(message.substring(0, message.indexOf(',')));
				message = message.substring(1 + message.indexOf(','));
				
				x_Japa = Integer.parseInt(message.substring(0, message.indexOf(',')));
				message = message.substring(1 + message.indexOf(','));
				
				y_Japa = Integer.parseInt(message.substring(0, message.indexOf(',')));
				message = message.substring(1 + message.indexOf(','));
				
				x_Propina = Integer.parseInt(message.substring(0, message.indexOf(',')));
				message = message.substring(1 + message.indexOf(','));
				
				y_Propina = Integer.parseInt(message.substring(0, message.indexOf(',')));
				message = message.substring(1 + message.indexOf(','));
					
				n_Prisoes = Integer.parseInt(message.substring(0, message.indexOf(',')));
				message = message.substring(1 + message.indexOf(','));
				
				n_Propina = Integer.parseInt(message.substring(0, message.indexOf(',')));
				message = message.substring(1 + message.indexOf(','));
				
				B.set_Lula (x_Lula, y_Lula);
				B.set_Japa (x_Japa, y_Japa);
				B.set_Propina (x_Propina, y_Propina);
				
				B.set_Placar(n_Prisoes, n_Propina);
				
				B.repaint();
			}
			catch (IOException e)
			{
				;
			}
		}
	}

}
