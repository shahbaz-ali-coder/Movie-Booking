import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame
{
	private final int MAXHEIGHT =1000;
	private final int MAXLENGTH = 800;
	private static JPanel panelAmerican;
	private static JPanel panelBirdman;
	private static JPanel panelMachina;
	private static JPanel panelJupiter;
	private static JPanel panelTaken;
	private static JPanel panelTheory;
	
	private static JPanel detailsAmerican;
	private static JPanel detailsBirdman;
	private static JPanel detailsMachina;
	private static JPanel detailsJupiter;
	private static JPanel detailsTaken;
	private static JPanel detailsTheory;
	private static jdbc connection;
	Image img;

	public MainPage()
	{
		setTitle("Cinema Application");
		setSize(MAXHEIGHT,MAXLENGTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setResizable(false);
		//setBounds(100,50,900,1050);
		setLayout(new FlowLayout());
		
		//setVisible(true);
		
		Image img = Toolkit.getDefaultToolkit().createImage("lightBlueBackground.jpg");
		//getContentPane().setBackground(Color.BLACK);
		/*
		setLayout(new BorderLayout());
		JLabel background = new JLabel();
		ImageIcon icon = new ImageIcon(img);
		add(background,BorderLayout.CENTER);
		background.setLayout(new FlowLayout());
		background.setIcon(icon);*/
		//repaint();
		//pack();
		validate();
		setVisible(true);
		
	}
	/*
	public JFrame detailsPage(JPanel panel)
	{
		JFrame frame = new JFrame();
		return frame;
	}*/
	public void paint(Graphics g)
	{	
		g.drawImage(img,0,0,null);	
		
	}
	public JPanel setup()
	{
		return null;
	}

	public static void main(String[] args) 
	{
		
		String movieName = "";
		MainPage newpage = new MainPage();
		

		jdbc connection = new jdbc();
		//create the movieContainers
		MovieContainer containerAmerican = new MovieContainer(connection, "American Sniper");
		MovieContainer containerBirdman = new MovieContainer(connection, "BirdMan");
		MovieContainer containerMachina = new MovieContainer(connection, "Ex-Machina");
		MovieContainer containerJupiter = new MovieContainer(connection, "Jupiter Ascending");
		MovieContainer containerTaken = new MovieContainer(connection, "Taken 3");
		MovieContainer containerTheory= new MovieContainer(connection, "The Theory of Everything");
		
		//parse the containers into Jpanels for main page
		panelAmerican  = containerAmerican.setUpMain();
		panelBirdman  = containerBirdman.setUpMain();
		panelMachina  = containerMachina.setUpMain();
		panelJupiter  = containerJupiter.setUpMain();
		panelTaken  = containerTaken.setUpMain();
		panelTheory  = containerTheory.setUpMain();
		
		// parses the containers into JPanels for movie details page
		detailsAmerican = containerAmerican.setUpMoviePage();
		detailsBirdman = containerBirdman.setUpMoviePage();
		detailsTaken = containerTaken.setUpMoviePage();
		detailsMachina = containerMachina.setUpMoviePage();
		detailsJupiter = containerAmerican.setUpMoviePage();
		detailsTheory = containerAmerican.setUpMoviePage();

		//Add the panels to the mainpage
		newpage.add(panelAmerican);
		newpage.add(panelBirdman);
		newpage.add(panelMachina);
		newpage.add(panelJupiter);
		newpage.add(panelTaken);
		newpage.add(panelTheory);
	
		newpage.setVisible(true);
		
		
		connection.cleanup_resources();
	}

}
