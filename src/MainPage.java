import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class MainPage extends JFrame
{
	public static int ticketsLeft;
	private static String currentMovieChoice;
	private static String dateChoice;
	private static String timeChoice;
	private static String ticketChoice;
	private final int MAXHEIGHT =800;
	private final int MAXLENGTH = 820;
	private static JButton panelAmerican;
	private static JButton panelBirdman;
	private static JButton panelMachina;
	private static JButton panelJupiter;
	private static JButton panelTaken;
	private static JButton panelTheory;
	private static JComboBox timeBox;
	private static JComboBox dateBox;
	private static JComboBox ticketAmount;
	
	private static JLabel ticketsPanel ;
	private JPanel menuPanel;
	private static JPanel finalisedPanel;
	private static JPanel overAllPanel;
	private static JPanel moviesPanel;
	private static JPanel detailsAmerican;
	private static JPanel detailsBirdman;
	private static JPanel detailsMachina;
	private static JPanel detailsJupiter;
	private static JPanel detailsTaken;
	private static JPanel detailsTheory;
	
	private static jdbc connection;
	private static JButton buttonMain;
	private static JButton buttonBooking;
	private static JButton continueBooking;
	private static JButton buttonComing;
	private static JButton buttonContact;
	private static JButton buttonBookMovie;
	private static Dimension buttonSize;
	
	private static MainPage mainPage;
	private static MainPage bookPage;
	private static MainPage comingPage;
	private static MainPage bookingPage;
	private static MainPage detailsAmericanPage;
	private static MainPage detailsBirdmanPage;
	private static MainPage detailsMachinaPage;
	private static MainPage detailsJupiterPage;
	private static MainPage detailsTakenPage;
	private static MainPage detailsTheoryPage;
	
	private static MainPage currentPage;
	private static MainPage newPage;
	private static SomeActionListener actionListener; 
	private static ItemChangeListenerDate dateListener; 
	private static ItemChangeListenerTime timeListener; 
	private static ItemChangeListenerTicketAmount ticketAmountListener; 
	
	private static MovieContainer containerAmerican;
	private static MovieContainer containerBirdman;
	private static MovieContainer containerMachina;
	private static MovieContainer containerJupiter;
	private static MovieContainer containerTaken;
	private static MovieContainer containerTheory;
	
	
	Image img;
	private static class ItemChangeListenerDate implements ItemListener{
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	          Object item = event.getItem();
	          dateChoice = item.toString();
	          bookPage.overAllPanel.remove(timeBox);
	          bookPage.overAllPanel.remove(ticketAmount);
	          bookPage.overAllPanel.remove(ticketsPanel);
	          bookPageTime();
	       }
	    }       
	}
	private static class ItemChangeListenerTime implements ItemListener{
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	          Object item = event.getItem();
	          timeChoice = item.toString();
	          bookPage.overAllPanel.remove(ticketAmount);
	          bookPage.overAllPanel.remove(ticketsPanel);
	          bookPageTicketsChoice();
	       }
	    }       
	}
	private static class ItemChangeListenerTicketAmount implements ItemListener{
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	          Object item = event.getItem();
	          ticketChoice = item.toString();
	          bookPage.overAllPanel.remove(ticketsPanel);
	          bookPage.overAllPanel.remove(continueBooking);
	          bookPageTicketsPanel();
	          
	       }
	    }       
	}
	private static class SomeActionListener implements ActionListener{
	    public SomeActionListener(){
	        //...
	    }
	    public void actionPerformed(ActionEvent e) {
	    	String action = e.getActionCommand();
	        if ("main".equals(action)){
	        	newPage = mainPage;
	        	bookPage =  new MainPage("lightBlueBackground.jpg",false);
	        	setUpOverAllPanel();
	        }
	        else if("booking".equals(action)){
	        	newPage = bookingPage;
	        	bookPage =  new MainPage("lightBlueBackground.jpg",false);
	        	setUpOverAllPanel();
	        }
	        else if("coming".equals(action)){
	        	newPage = comingPage;
	        	bookPage =  new MainPage("lightBlueBackground.jpg",false);
	        	setUpOverAllPanel();
	        }
	        else if(action.equals("americanDetails")){
	        	newPage = detailsAmericanPage;
	        	currentMovieChoice = "American Sniper";
	        }
	        else if("birdmanDetails".equals(action)){
	        	newPage = detailsBirdmanPage;
	        	currentMovieChoice = "Birdman";
	        }
	        else if("machinaDetails".equals(action)){
	        	newPage =  detailsMachinaPage;
	        	currentMovieChoice = "Ex-Machina";
	        }
	        else if("jupiterDetails".equals(action)){
	        	newPage = detailsJupiterPage;
	        	currentMovieChoice = "Jupiter Ascending";
	        }
	        else if("takenDetails".equals(action)){
	        	newPage = detailsTakenPage;
	        	currentMovieChoice = "Taken 3";
	        }
	        else if("theoryDetails".equals(action)){
	        	newPage = detailsTheoryPage;
	        	currentMovieChoice = "The Theory of Everything";
	        }
	        else if("book".equals(action)){
	        	newPage = bookPage;
	        	bookPageDate();
	        }
	        else if("continueBooking".equals(action)){
	        	finaliseBooking();
	        }
	        changePage();
	    }

	}
	public static void finaliseBooking(){
		
		bookingPage.overAllPanel.remove(finalisedPanel);
		finalisedPanel = new JPanel();
		int amountOfTickets = 0;
		if (ticketChoice.equals("1"))
			amountOfTickets = 1;
		if (ticketChoice.equals("2"))
			amountOfTickets = 2;
		if (ticketChoice.equals("3"))
			amountOfTickets = 3;
		if (ticketChoice.equals("4"))
			amountOfTickets = 4;
		if (ticketChoice.equals("5"))
			amountOfTickets = 5;
		if(ticketsLeft <= amountOfTickets){
			JLabel deniedLabel = new JLabel("There is not enough tickets for this transaction");
			finalisedPanel.add(deniedLabel);
		}
		else{
			JLabel confirmedLabel = new JLabel();
		}
		bookingPage.overAllPanel.add(finalisedPanel);
	}
	public MainPage(String background,boolean visible)
	{
		setTitle("Cinema Application");
		setSize(MAXHEIGHT,MAXLENGTH);
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(0,0,0,30));
		

		add(menuPanel);
		actionListener = new SomeActionListener();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	    setContentPane(new JLabel(new ImageIcon(background)));
	    setLayout(new FlowLayout());
	    add(setUpButtons());
		validate();
		setVisible(visible);
	}

	
	
	
	
	
	
	public static void bookPageDate(){
		bookPage.add(overAllPanel);
		JLabel movie = new JLabel(currentMovieChoice);
		ArrayList<String> dateArray = new ArrayList<String>();
		dateArray = connection.getShowingDate(currentMovieChoice);
		dateBox = new JComboBox(dateArray.toArray());
		dateBox.addItemListener(dateListener);
		bookPage.overAllPanel.add(movie);
		bookPage.overAllPanel.add(dateBox);
		dateChoice = dateArray.get(0);
		bookPageTime();
	}
	public static void bookPageTime(){
		ArrayList<String> timeArray = new ArrayList<String>();
		timeArray = connection.getShowingTime(currentMovieChoice,dateChoice);
		timeBox = new JComboBox(timeArray.toArray());
		timeBox.addItemListener(timeListener);
		timeChoice = timeArray.get(0);
		bookPage.overAllPanel.add(timeBox);
		bookPage.setVisible(true);
		bookPageTicketsChoice();
	}
	public static void bookPageTicketsChoice(){
		String ticketAmountString[] = new String[]{"1","2","3","4","5"};
		ticketsPanel = new JLabel();
		ticketAmount = new JComboBox(ticketAmountString);
		ticketAmount.addItemListener(ticketAmountListener);
		ticketsLeft = connection.getShowingSeatCounter(currentMovieChoice,dateChoice,timeChoice);
		if (ticketsLeft <=0){
			
			ticketsPanel.setText("There are no tickets for this showing.");
			bookPage.overAllPanel.add(ticketsPanel);
		}
		else{
			String ticketsLeftString  = ("Tickets Left: "+ Integer.toString(ticketsLeft));
			ticketsPanel.setText(ticketsLeftString);
			bookPage.overAllPanel.add(ticketAmount);
			bookPageTicketsPanel();
		}
		bookPage.setVisible(true);
	}
	public static void bookPageTicketsPanel(){
		bookPage.overAllPanel.add(ticketsPanel);
		bookPage.overAllPanel.add(continueBooking);
		bookPage.setVisible(true);
	}

	public static void changePage(){
			currentPage.setVisible(false);
			newPage.setVisible(true);
			currentPage = newPage;
	}
	public JPanel setUpButtons()
	{
		//intialize
		buttonMain = new JButton("Main Page");
		buttonBooking = new JButton("Booking");
		buttonComing = new JButton("Coming Soon");
		
		buttonMain.setActionCommand("main");
		buttonBooking.setActionCommand("booking");
		buttonComing.setActionCommand("coming");
		
		buttonMain.addActionListener(actionListener);
		buttonBooking.addActionListener(actionListener);
		buttonComing.addActionListener(actionListener);
		//set size

		buttonComing.setPreferredSize(buttonSize);
		buttonBooking.setPreferredSize(buttonSize);
		buttonMain.setPreferredSize(buttonSize);
		menuPanel.add(buttonMain);
		menuPanel.add(buttonBooking);
		menuPanel.add(buttonComing);
		return menuPanel;
		
	}
	public JPanel setUpBookingButton(){
		buttonBookMovie = new JButton("Book");
		buttonBookMovie.setActionCommand("book");
		buttonBookMovie.addActionListener(actionListener);
		buttonBookMovie.setPreferredSize(buttonSize);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,0,0,50));
		panel.add(buttonBookMovie);
		return panel;
	}
	public static void setupListeners(){
		//add action commands
		panelAmerican.setActionCommand("americanDetails");
		panelBirdman.setActionCommand("birdmanDetails");
		panelMachina.setActionCommand("machinaDetails");
		panelJupiter.setActionCommand("jupiterDetails");
		panelTaken.setActionCommand("takenDetails");
		panelTheory.setActionCommand("theoryDetails");
		
		//add action listeners
		panelAmerican.addActionListener(actionListener);
		panelBirdman.addActionListener(actionListener);
		panelMachina.addActionListener(actionListener);
		panelJupiter.addActionListener(actionListener);
		panelTaken.addActionListener(actionListener);
		panelTheory.addActionListener(actionListener);
	}
	public static void callBookButtons(){
		detailsAmericanPage.add(detailsAmericanPage.setUpBookingButton());
		detailsBirdmanPage.add(detailsBirdmanPage.setUpBookingButton());
		detailsJupiterPage.add(detailsJupiterPage.setUpBookingButton());
		detailsTakenPage.add(detailsTakenPage.setUpBookingButton());
		detailsMachinaPage.add(detailsMachinaPage.setUpBookingButton());
		detailsTheoryPage.add(detailsTheoryPage.setUpBookingButton());
	}
	public static void setUpOverAllPanel(){
		overAllPanel = new JPanel();
		overAllPanel.setLayout(new GridLayout(6,1));
		overAllPanel.setBackground(new Color (0,0,0,50));
	}
	public static void main(String[] args) 
	{
		continueBooking = new JButton("Continue");
		continueBooking.setActionCommand("continueBooking");
		continueBooking.addActionListener(actionListener);
		setUpOverAllPanel();
		dateListener = new ItemChangeListenerDate(); 
		timeListener = new ItemChangeListenerTime(); 
		ticketAmountListener = new ItemChangeListenerTicketAmount(); 
		buttonSize = new Dimension(230,60);
		String movieName = "";
		jdbc connection = new jdbc();
		//create the movieContainers
		containerAmerican = new MovieContainer(connection, "American Sniper");
		containerBirdman = new MovieContainer(connection, "BirdMan");
		containerMachina = new MovieContainer(connection, "Ex-Machina");
		containerJupiter = new MovieContainer(connection, "Jupiter Ascending");
		containerTaken = new MovieContainer(connection, "Taken 3");
		containerTheory= new MovieContainer(connection, "The Theory of Everything");
		
		mainPage = new MainPage("lightBlueBackground.jpg",true);
		comingPage = new MainPage("lightBlueBackground.jpg",false);
		bookingPage = new MainPage("lightBlueBackground.jpg",false);
		bookPage = new MainPage("lightBlueBackground.jpg",false);
		detailsAmericanPage = new MainPage(containerAmerican.getBackground(),false);
		detailsBirdmanPage = new MainPage(containerBirdman.getBackground(),false);
		detailsJupiterPage = new MainPage(containerJupiter.getBackground(),false);
		detailsTakenPage = new MainPage(containerTaken.getBackground(),false);
		detailsTheoryPage = new MainPage(containerTheory.getBackground(),false);
		detailsMachinaPage = new MainPage(containerMachina.getBackground(),false);
		currentPage = mainPage;
		
		JFrame movieFrame = new JFrame();
	
		//parse the containers into Jpanels for main page
		panelAmerican  = containerAmerican.setUpMain();
		panelBirdman  = containerBirdman.setUpMain();
		panelMachina  = containerMachina.setUpMain();
		panelJupiter  = containerJupiter.setUpMain();
		panelTaken  = containerTaken.setUpMain();
		panelTheory  = containerTheory.setUpMain();
			
		setupListeners();
		// parses the containers into JPanels for movie details page
		detailsAmerican = containerAmerican.setUpMoviePage();
		detailsAmericanPage.add(detailsAmerican);
		
		detailsBirdman = containerBirdman.setUpMoviePage();
		detailsBirdmanPage.add(detailsBirdman);
		
		detailsTaken = containerTaken.setUpMoviePage();
		detailsTakenPage.add(detailsTaken);
		
		detailsMachina = containerMachina.setUpMoviePage();
		detailsMachinaPage.add(detailsMachina);
		
		detailsJupiter = containerJupiter.setUpMoviePage();
		detailsJupiterPage.add(detailsJupiter);
		
		detailsTheory = containerTheory.setUpMoviePage();
		detailsTheoryPage.add(detailsTheory);
		
		callBookButtons();
		
		//add the panels to the page
		mainPage.add(panelAmerican);
		mainPage.add(panelBirdman);
		mainPage.add(panelMachina);
		mainPage.add(panelJupiter);
		mainPage.add(panelTaken);
		mainPage.add(panelTheory);
		
		
		mainPage.setVisible(true);
		
		
		
	}

}
