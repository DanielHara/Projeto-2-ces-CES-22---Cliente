import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.*;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.Random;

public class Board extends JPanel {

	public enum Direction {
		Right, Left, Up, Down;		
	}
	
	//Declaração de variáveis
	
	private static final int Largura = 600;
	private static final int Altura = 800;
	
	private int x_Lula = 100;
	private int y_Lula = 100;
	private int x_Propina;
	private int y_Propina;
	
	
	private int x_Japa = 500;
	private int y_Japa = 500;
	
	private Timer timer;
	private Image PF;
	private Image Lula;
	private Image Propina;
	
	public Placar Placar_Jogo = new Placar();

	InputStream StreamFromServer;
	OutputStream StreamToServer;
	
	Socket Server;
	
	DataFromServer Thread_Stream;
	
	
	//Esta função Conecta o cliente a um servidor com endereço IP armazenado na String IPAdress e na porta Port.
	public void Conectar (String IPAdress, int Port)
	{
		try
		{
			Server = new Socket (IPAdress, Port);
			StreamFromServer = Server.getInputStream();
			StreamToServer = Server.getOutputStream();
			Server.setSoTimeout(10);
			System.out.println("Conectou!");
		}
		catch (IOException e)
		{
			System.out.println("Exceção:" + e);
		}
	}
	
	
	//Construtor
	
	
	public Board (String IPAdress, int Port)
	{
		setBackground(Color.black);
		setPreferredSize(new Dimension(Largura, Altura));
	
	
		//Carregar as imagens	
		ImageIcon Imagem_PF = new ImageIcon("PF.png");
		PF = Imagem_PF.getImage();
		
		ImageIcon Imagem_Lula = new ImageIcon("Lula.png");
		Lula = Imagem_Lula.getImage();
		
		ImageIcon Imagem_Propina = new ImageIcon("Propina.png");
		Propina = Imagem_Propina.getImage();
		
		y_Lula = 100;
		x_Lula = 100;
		
		x_Japa = 500;
		y_Japa = 500;
		
		addKeyListener(new Controlador());
		setFocusable(true);
			
		Conectar(IPAdress, Port);
		
		System.out.println("Estou aqui!");
	
	
		Thread_Stream = new DataFromServer (this, StreamFromServer);
		Thread_Stream.start();    //Iniciar a Thread para ler os dados enviados pelo Servidor.
	}
	
	
	//Desenhar a tela.
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent (g);
		g.drawImage(PF, x_Japa, y_Japa, this);
		g.drawImage(Lula, x_Lula, y_Lula, this);
		g.drawImage(Propina, x_Propina, y_Propina, this);
        Toolkit.getDefaultToolkit().sync();
	}
	
	//Esta função seta a posição do Lula na posição (x,y)
	public void set_Lula (int x, int y)
	{
		x_Lula = x;
		y_Lula = y;
	}
	
	//Esta função seta a posição da propina na posição (x,y)
	public void set_Propina (int x, int y)
	{
		x_Propina = x;
		y_Propina = y;
	}
	
	//Esta função seta a posição do Japa na posição (x,y)
	public void set_Japa (int x, int y)
	{
		x_Japa = x;
		y_Japa = y;
	}
	
	//Esta função mostra o placar com n_Prisoes prisões e n_Propina propinas.
	public void set_Placar (int n_Prisoes, int n_Propina)
	{
		Placar_Jogo.set_Prisao (n_Prisoes);
		Placar_Jogo.set_Propina(n_Propina);
		Placar_Jogo.Show_Placar();
	}

	//Esta função move o Japa com as setas do teclado e envia a informção da mudança de direção para o Servidor.
	private class Controlador extends KeyAdapter
	{
		@Override
		public void keyPressed (KeyEvent e)
		{
			int key = e.getKeyCode();
			String Message = new String("");
			try
			{
				switch (key)
				{
					case KeyEvent.VK_RIGHT:
						Message = new String ("R");
						StreamToServer.write(Message.getBytes());
						break;
					case KeyEvent.VK_LEFT:
						Message = new String ("L");
						StreamToServer.write(Message.getBytes());
						break;
					case KeyEvent.VK_UP:
						Message = new String ("U");
						StreamToServer.write(Message.getBytes());
						break;
					case KeyEvent.VK_DOWN:
						Message = new String ("D");
						StreamToServer.write(Message.getBytes());
						break;
				}
			}
			catch (IOException evt)
			{
				;
			}
		}
	}   
	
}
