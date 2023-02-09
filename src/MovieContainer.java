import javax.swing.*;

import java.awt.*;

import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class MovieContainer
{
	//private variables
	//movie variables
	private String movieCover = "";
	private String movieBackground = "";
	private String movieDirector = "";
	private String movieActors = "";
	private int  movieLength ;
	private String movieRating = "";
	private String movieGenre = "";
	private String movieReleaseDate = "";
	private String movieDescription = "";
	//showing variables
	private String showingName = "";
	private String showingDate = "";
	private String showingTime = "";
	private int showingTheatre;
	private int showingSeatCounter;
	//Jpanels
	private JPanel panelMain;
	private JPanel panelDetails;
	private JPanel panelSeats;

	//Jlabels
	//movie jlabel
	private JLabel cover;
	private JLabel background;
	private JLabel director;
	private JLabel actors;
	private JLabel length;
	private JLabel rating;
	private JLabel genre;
	private JLabel releaseDate;
	private JLabel description;
	//showing JLabel
	private JLabel name;
	private JLabel date;
	private JLabel time;
	private JLabel theatre;
	private JLabel seatCounter;

	public MovieContainer(jdbc connection, String movieName)
	{
		//movie variables
		movieCover = connection.getMovieCover(movieName);
		movieBackground = connection.getMovieBackground(movieName);
		movieDirector = connection.getMovieDirector(movieName);
		movieActors = connection.getMovieActors(movieName);
		movieLength = connection.getMovieLength(movieName);
		movieRating = connection.getMovieRating(movieName);
		movieGenre = connection.getMovieGenre(movieName);
		movieReleaseDate = connection.getMovieReleaseDate(movieName);
		movieDescription = connection.getMovieDescription(movieName);

		//showing variables
		showingName = connection.getShowingName(movieName);
		//showingDate = connection.getShowingDate(movieName);
		//showingTime = connection.getShowingTime(movieName);
		showingTheatre = connection.getShowingTheatre(movieName);
		//showingSeatCounter = connection.getShowingSeatCounter(movieName);



		cover = new JLabel(movieCover,null,JLabel.CENTER);
		background = new JLabel(movieBackground,null,JLabel.CENTER);
		director = new JLabel(movieDirector);
		actors = new JLabel(movieActors);
		length = new JLabel(Integer.toString(movieLength));
		rating =new JLabel(movieRating);
		genre=new JLabel(movieGenre);
		releaseDate=new JLabel(movieReleaseDate);
		description=new JLabel(movieDescription);
		name = new JLabel(showingName);
		date= new JLabel(showingDate);
		time= new JLabel(showingTime);
		theatre= new JLabel(Integer.toString(showingTheatre));
		seatCounter= new JLabel(Integer.toString(showingSeatCounter));

		panelMain = new JPanel();
		panelDetails = new JPanel();
		panelSeats = new JPanel();
	}
	public String getBackground(){
		return movieBackground;
	}
	public JButton setUpMain()
	{

		Image img = Toolkit.getDefaultToolkit().createImage(movieCover);
		JButton cover = new JButton();
		ImageIcon icon = new ImageIcon(img);
		panelMain.add(cover,BorderLayout.CENTER);
		cover.setLayout(new FlowLayout());
		cover.setIcon(icon);
		//panel.add(cover);
		return cover;
	}
	public JPanel setUpMoviePage()
	{
		Color textColor = new Color(220,220,220);
		Border border = BorderFactory.createLineBorder(Color.white, 1);
		//Font myFont = new Font("Serif", Font.BOLD, 16);
		JPanel descriptionPanel = new JPanel();
		Image img = Toolkit.getDefaultToolkit().createImage(movieCover);
		JLabel cover = new JLabel();
		ImageIcon icon = new ImageIcon(img);
		Color coverColor = new Color(255,255,255,160);
		Border coverBorder = BorderFactory.createLineBorder(coverColor, 30);
		
		cover.setBorder(coverBorder);
		cover.setBackground(new Color (0,0,0,30));
		JTextField dir = new JTextField("Director: "+movieDirector,40);
		//dir.setOpaque(false);
		dir.setBackground(new Color(0,0,0,200));
		dir.setForeground(textColor);
		JTextField act = new JTextField("Actor: "+movieActors,40);
		//act.setOpaque(false);
		act.setBackground(new Color(0,0,0,200));
		act.setForeground(textColor);
		JTextField len = new JTextField("Length: "+movieLength,40);
		//len.setOpaque(false);
		len.setBackground(new Color(0,0,0,200));
		len.setForeground(textColor);
		JTextField gen = new JTextField("Genre: "+movieGenre,40);
		//gen.setOpaque(false);
		gen.setBackground(new Color(0,0,0,200));
		gen.setForeground(textColor);
		JTextField rat = new JTextField("Rating: "+movieRating,40);
		//rat.setOpaque(false);
		rat.setBackground(new Color(0,0,0,200));
		rat.setForeground(textColor);
		JTextArea desc = new  JTextArea("Description: "+movieDescription);
		//desc.setOpaque(false);
		desc.setBackground(new Color(0,0,0,200));
		desc.setForeground(textColor);
		JTextField redate = new JTextField("Release Date: "+movieReleaseDate,40);
		//redate.setOpaque(false);
		redate.setBackground(new Color(0,0,0,200));
		redate.setForeground(textColor);
		descriptionPanel.setLayout(new GridLayout(7,1));
		panelDetails.setLayout(new FlowLayout());
		panelDetails.add(cover,BorderLayout.WEST);
		cover.setLayout(new FlowLayout());
		cover.setIcon(icon);
		desc.setRows(3);
		desc.setLineWrap(true);
		desc.setBorder(border);
		dir.setFont(new Font("Serif", Font.BOLD, 16));
		act.setFont(new Font("Serif", Font.BOLD, 16));
		len.setFont(new Font("Serif", Font.BOLD, 16));
		gen.setFont(new Font("Serif", Font.BOLD, 16));
		rat.setFont(new Font("Serif", Font.BOLD, 16));
		redate.setFont(new Font("Serif", Font.BOLD, 16));
		desc.setFont(new Font("Serif", Font.BOLD, 16));
		descriptionPanel.add(dir);
		descriptionPanel.add(act);
		descriptionPanel.add(len);
		descriptionPanel.add(rat);
		descriptionPanel.add(gen);
		descriptionPanel.add(redate);
		descriptionPanel.add(desc);
		descriptionPanel.setOpaque(false);
		//descriptionPanel.setBackground(Color.BLACK);

		panelDetails.add(descriptionPanel);
		panelDetails.setOpaque(false);
		return panelDetails;
	}
	public JPanel setUpSeatPage()
	{
		panelSeats.add(cover);
		panelSeats.add(director);
		panelSeats.add(actors);
		panelSeats.add(length);
		panelSeats.add(rating);
		panelSeats.add(genre);
		panelSeats.add(releaseDate);
		panelSeats.add(description);

		return panelSeats;
	}

	public static void main(String[] args)
	{
	}

}
