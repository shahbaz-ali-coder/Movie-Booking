import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class jdbc 
{
	
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static Scanner in = new Scanner(System.in);
	public jdbc()
	{
		init_db(); 
	}
	public static void main(String[] args) throws SQLException 
	{
	}
//gets
	//Gets for the movie table	
//returns the movie name
public static String getMovieName(String movieName)
{
	try
	{
		String movieN ="";
		rs = stmt.executeQuery("SELECT movies.name FROM movies as movieNam WHERE name = "+movieName+";");
		rs.next();
		movieN= rs.getString("movieNam");
		return movieN;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return "";
	}
}
//returns the movie cover hyperlink
public static String getMovieCover(String movieName)
{
	try
	{
		String movieCover ="";
		rs = stmt.executeQuery("SELECT coverPhoto FROM movies as coverP WHERE name = \""+movieName+"\";");
		rs.next();
		movieCover= rs.getString(1);
		return movieCover;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return "";
	}
}
//returns the movie background hyperlink
public static String getMovieBackground(String movieName)
{
	try
	{
		String movieBackground ="";
		rs = stmt.executeQuery("SELECT backgroundPhoto FROM movies as background WHERE name = \""+movieName+"\";");
		rs.next();
		movieBackground= rs.getString(1);
		return movieBackground;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return "";
	}
}
//returns the movie director
public static String getMovieDirector(String movieName)
{
	try
	{
		String director ="";
		rs = stmt.executeQuery("SELECT director FROM movies as dir WHERE name = \""+movieName+"\";");
		rs.next();
		director= rs.getString(1);
		return director;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return "";
	}
}
//returns the movie actors
public static String getMovieActors(String movieName)
{
	try
	{
		String actors ="";
		rs = stmt.executeQuery("SELECT starring FROM movies as actors WHERE name = \""+movieName+"\";");
		rs.next();
		actors = rs.getString(1);
		return actors;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return "";
	}
}
//returns the movie length
public static int getMovieLength(String movieName)
{
	try
	{
		int length;
		rs = stmt.executeQuery("SELECT length FROM movies as leng WHERE name = \""+movieName+"\";");
		rs.next();
		length = rs.getInt(1);
		return length;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return 0;
	}
}
//returns the movie rating
public static String getMovieRating(String movieName)
{
	try
	{
		String rating = "";
		rs = stmt.executeQuery("SELECT rating FROM movies as rat WHERE name = \""+movieName+"\";");
		rs.next();
		rating = rs.getString(1);
		return rating;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return "";
	}
}
//returns the movie genre
public static String getMovieGenre(String movieName)
{
	try
	{
		String genre = "";
		rs = stmt.executeQuery("SELECT genre FROM movies as gen WHERE name = \""+movieName+"\";");
		rs.next();
		genre = rs.getString(1);
		return genre;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return "";
	}
}
//returns the movie release date
public static String getMovieReleaseDate(String movieName)
{
	try
	{
		String releaseDate = "";
		rs = stmt.executeQuery("SELECT releaseDate FROM movies as da WHERE name =\""+movieName+"\";");
		rs.next();
		releaseDate = rs.getString(1);
		return releaseDate;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return "";
	}
}
//returns the movie description
public static String getMovieDescription(String movieName)
{
	try
	{
		String description = "";
		rs = stmt.executeQuery("SELECT description FROM movies as descr WHERE name = \""+movieName+"\";");
		rs.next();
		description = rs.getString(1);
		return description;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return "";
	}
}
	
	//gets for the showing table
//returns the showing name
public static String getShowingName(String movieName)
{
	try
	{
		String movieN ="";
		rs = stmt.executeQuery("SELECT movieName FROM showing as movieNam WHERE movieName = \""+movieName+"\";");
		rs.next();
		movieN= rs.getString(1);
		return movieN;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return "";
	}
}
//returns the showing date
public static ArrayList getShowingDate(String movieName)
{
	try
	{
		ArrayList<String> dateArray = new ArrayList<String>();
		String showingDate ="";
		rs = stmt.executeQuery("SELECT showingDate FROM showing as dat WHERE movieName = \""+movieName+"\";");
		while (rs.next()){
			showingDate= rs.getString(1);
			dateArray.add(showingDate);
		}
		return dateArray;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return null;
	}
}
//returns the showing time
public static ArrayList getShowingTime(String movieName,String dateChoice)
{
	try
	{
		ArrayList<String> timeArray = new ArrayList<String>();
		String showingTime ="";
		rs = stmt.executeQuery("SELECT showingTime FROM showing as tim WHERE movieName = \""+movieName+"\" and showingDate= \""+dateChoice+"\";");
		while (rs.next()){
			showingTime= rs.getString(1);
			timeArray.add(showingTime);
		}
		return timeArray;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return null;
	}
}
//returns the showing theatre
public static int getShowingTheatre(String movieName)
{
	try
	{
		int theatreN;
		rs = stmt.executeQuery("SELECT theatreNo FROM showing as the WHERE movieName = \""+movieName+"\";");
		rs.next();
		theatreN= rs.getInt(1);
		return theatreN;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return 0;
	}
}
//returns the showing seat counter
public static int getShowingSeatCounter(String movieName,String dateChoice,String timeChoice)
{
	try
	{
		int seatCounter;
		rs = stmt.executeQuery("SELECT seatCounter FROM showing as seat WHERE movieName = \""+movieName+"\" and showingDate= \""+dateChoice+"\" and showingTime= \""+timeChoice+"\";");
		rs.next();
		seatCounter= rs.getInt(1);
		return seatCounter;
	}
	catch (SQLException sqle)
	{
		System.out.println("Error: failed to retrieve the resource");
		return 0;
	}
}

//Main methods
	public static void cleanup_resources()
	{
		try
		{
			con.close();
		}
		catch (SQLException sqle)
		{
			System.out.println("Error: failed to close the database");
		}
	}
	public static void init_db()
	{
		try
		{
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/movieapplication";
			con = DriverManager.getConnection(url, "root", "admin");
			stmt = con.createStatement();
			System.out.println("Connected to database");
		}
		catch(Exception e)
		{
			System.out.println("Error: Failed to connect to database\n"+e.getMessage());
		}
	}
}
