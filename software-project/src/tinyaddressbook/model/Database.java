package tinyaddressbook.model;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.*;
import java.util.Random;

import org.sqlite.SQLiteConfig;

import tinyaddressbook.views.Main;

public class Database 
{
	private Connection conn;
	private Statement stat;

	
	/**
	 * Ogni volta che viene chiamato il db verifica se � presente un database
	 * e se nn � presente ne crea uno nuovo
	 */
	public Database() 
	{
		boolean firstInser;
		
		//Verifico se � presente il file del database
		if(false == new File(this.getFileName()).isFile())
			firstInser = true;
		else
			firstInser = false;
		
		SQLiteConfig config = new SQLiteConfig();
		config.enableRecursiveTriggers(true);

		
		//Verifico il driver
		if(checkDriver())
		{
	        try
	        {
	        	//Connessione
	            conn = DriverManager.getConnection("jdbc:sqlite:"+this.getFileName(), config.toProperties());
	        } 
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	            System.exit(1);
	        }
		}
		if(true == firstInser)
			setStructure();
			

	}
	
	
    public void setCalendar(String id_calendar, String id_people, String from, String to, String title, String description)
    {
		try 
		{

			stat = conn.createStatement();
			String query = null;
			if(id_calendar == "")
	    	{
	            query = "INSERT INTO calendar " +
						" (id_people, 'from', 'to', title, description) " +
						"VALUES " +
						"('"+id_people+"', '"+from+"', '"+to+"', '"+title+"',  '"+description+"');";

	    	}
			else
			{
				 query = "UPDATE calendar SET " +
							"id_people = '"+id_people+"'," +
							"`from` = '"+from+"'," +
							"`to` = '"+to+"'," +
							"title = '"+title+"'," +
							"description = '"+description+"'" +
							" WHERE " +
							"id_calendar = '"+id_calendar+"';";

			}
			
			System.out.println(query);
            stat.executeUpdate(query);
            stat.close();
    	
		} catch (SQLException e) { e.printStackTrace(); }
  
    }
	
	/**
	 * 
	 * @param id_people
	 * @return
	 */
	public ResultSet getCalendarByPrimary(Integer id_calendar)
	{
		String sql = "SELECT * FROM calendar_people WHERE id_calendar = '"+id_calendar.toString()+"'";
		
		
		ResultSet resultSet = null;
		try 
		{
			stat = conn.createStatement();
			resultSet = stat.executeQuery(sql);
			
		} catch (SQLException e) { e.printStackTrace(); }
		return resultSet;
	
	}

    
    
	/**
     * Restituisce i record della tabella people, se viene passata una stringa a ricerca
     * cerca nel nome o nel cognome
     * 
     * @param search
     * @return
     */
    public ResultSet getCalendar(String search)
    {
    	ResultSet resultSet = null;
    	
    	String where = "";
    	if("" != search)
    		where = " WHERE first_name LIKE '%"+search+"%' OR last_name LIKE '%"+search+"%'  OR title LIKE '%"+search+"%' OR description  LIKE '%"+search+"%' ";
    	
    	String query = "SELECT * FROM calendar_people "+where+" ORDER BY 'from','to' ASC";
        
		try 
		{
			stat = conn.createStatement();
			resultSet = stat.executeQuery(query);			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(query);
		
		return resultSet;  
 
    }
	
	
	
	
	
	public boolean delCalendar(Integer id_calendar)
	{
		boolean mReturn = false; 
		
		try 
		{
			stat = conn.createStatement();
			
			String sql = "DELETE FROM calendar WHERE id_calendar = '"+id_calendar.toString()+"'";
			int delete = stat.executeUpdate(sql);
			
			if(delete == 1)
				mReturn = true;
			
			stat.close();
			
		} catch (SQLException e) { e.printStackTrace(); }
	
		return mReturn;
	}

	/**
     * 
     * @param id_people
     * @param first_name
     * @param last_name
     * @param gender
     * @param born
     * @param phone
     * @param email
     * @param town
     * @param street
     * @param street_number
     */
    public void setPeople(String id_people, String  first_name, String  last_name, String  gender, String born, String phone, String email, String town, String street, String street_number)
    {
		try 
		{

			stat = conn.createStatement();
			String query = null;
			if(id_people == "")
	    	{
	            query = "INSERT INTO people " +
						" (first_name,last_name,gender,born,phone,email,town,street,street_number) " +
						"VALUES " +
						"('"+first_name+"', '"+last_name+"', '"+gender+"', '"+born+"', '"+phone+"', '"+email+"', '"+town+"', '"+street+"', '"+street_number+"');";

	    	}
			else
			{
				 query = "UPDATE people SET " +
							"first_name = '"+first_name+"'," +
							"last_name = '"+last_name+"'," +
							"gender = '"+gender+"'," +
							"born = '"+born+"'," +
							"phone = '"+phone+"'," +
							"email = '"+email+"'," +
							"town = '"+town+"'," +
							"street = '"+street+"'," +
							"street_number = '"+street_number+"' " +
							"WHERE " +
							"id_people = '"+id_people+"';";

			}
            stat.executeUpdate(query);
            stat.close();
    	
		} catch (SQLException e) { e.printStackTrace(); }
    	
    }
    
	
	/**
	 * 
	 * @param id_people
	 * @return
	 */
	public ResultSet getPeopleByPrimary(Integer id_people)
	{
		String sql = "SELECT * FROM people WHERE id_people = '"+id_people.toString()+"'";
		
		
		ResultSet resultSet = null;
		try 
		{
			stat = conn.createStatement();
			resultSet = stat.executeQuery(sql);
			
		} catch (SQLException e) { e.printStackTrace(); }
		return resultSet;
	
	}

	/**
     * Restituisce i record della tabella people, se viene passata una stringa a ricerca
     * cerca nel nome o nel cognome
     * 
     * @param search
     * @return
     */
    public ResultSet getPeople(String search)
    {
    	ResultSet resultSet = null;
    	
    	String where = "";
    	if("" != search)
    		where = " WHERE first_name LIKE '%"+search+"%' OR last_name LIKE '%"+search+"%'  OR phone LIKE '%"+search+"%' ";
    	
    	String query = "SELECT * FROM people "+where+" ORDER BY last_name, first_name ASC";
        
    	
    	
		try 
		{
			stat = conn.createStatement();
			resultSet = stat.executeQuery(query);			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;  
 
    }
	
	
	
	/**
	 * Passato un id di una persona lo elinina
	 * @param id_people
	 * @return
	 */
	public boolean delPeople(Integer id_people)
	{
		boolean mReturn = false; 
		
		try 
		{
			stat = conn.createStatement();
			
			String sql = "DELETE FROM people WHERE id_people = '"+id_people.toString()+"'";
			int delete = stat.executeUpdate(sql);
			
			if(delete == 1)
				mReturn = true;
			
			stat.close();
			
		} catch (SQLException e) { e.printStackTrace(); }
	
		return mReturn;
	}
	
	public Integer count(ResultSet resultSet)
	{
		int size = 0;
		try {
		    resultSet.last();
		    size = resultSet.getRow();
		    resultSet.beforeFirst();
		}
		catch(Exception ex) {
		    return 0;
		}
		
		return size;
	}

	
	/**
	 * 
	 * Verifica se �  presente il driver per sqlite all'interno del progetto
	 * 
	 * @return {@link Boolean}
	 */
    private boolean checkDriver()
    {
        try{
            Class.forName("org.sqlite.JDBC");}
        catch(ClassNotFoundException e){
            System.out.println("Driver sqlite non caricato");
            return false;
        }
        return true;
    }
    
    /**
     * Crea la struttura del database
     */
  
    
    
    
    private void setStructure()
    {
     System.out.println("Creazione Struttura");

        try
        {
            stat = conn.createStatement();
            
            //Creazione delle tabelle nel database
            stat.executeUpdate("CREATE TABLE people (id_people INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT, gender TEXT, born TEXT, phone TEXT, email TEXT, town TEXT, street TEXT, street_number TEXT);");
            stat.executeUpdate("CREATE  TABLE 'calendar' ('id_calendar' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 'id_people' INTEGER, 'from' INTEGER, 'to' INTEGER, 'title' VARCHAR, 'description' text)");
            
            //Creazione della view per visualizzare eventi e calendario
            stat.executeUpdate("CREATE VIEW calendar_people AS SELECT people.*,  calendar.id_calendar,  calendar.'from', calendar.'to',  calendar.title,  calendar.description FROM calendar LEFT OUTER JOIN people ON people.id_people = calendar.id_people");
            
            //Creazione della trigger            
            stat.executeUpdate("CREATE TRIGGER delete_people BEFORE DELETE ON people FOR EACH ROW BEGIN 	UPDATE calendar SET id_people = NULL WHERE calendar.id_people = OLD.id_people; END;");
            stat.close();
        }
        catch(SQLException e)
        {
         e.getErrorCode();
        }
//        setDeveloperInser();
    }
    
	/**
	* Inserisce dei dati fitizzi per lo sviluppo
	*/
    private void setDeveloperInser()
    {
    	String[] firstName = new String[]{"John", "Thomas", "Samuel", "Chris", "Daniel", "Joey", "David", "Joshua", "Michael", "John"};
        
        Random random = new Random();
        
        try
        {
            
            
            for(int i = 0;i<50;i++)
            {
             stat = conn.createStatement();
             int randomNumber = random.nextInt(firstName.length);	
             String timestamp = String.valueOf(System.currentTimeMillis());
            
			String query = "INSERT INTO people " +
			" (first_name,last_name,gender,born,phone,email,town,street,street_number) " +
			"VALUES " +
			"('"+firstName[randomNumber].toString()+"', 'Cognome', 'm', '"+timestamp+"', '"+random.nextInt()+"', 'test@test.it', 'Citta', 'Indirizzo', '"+random.nextInt()+"');";
			
			stat.executeUpdate(query);
			stat.close();
            }

            
        }
        catch(SQLException e){ e.getErrorCode(); }
    }
    
    
    /**
     * Restituisce il path del jar
     * @return
     */
    private String getFileName()
    {
    	
    	String mPath = System.getProperty("user.dir").toString()+"/database.sqlite";

//    	System.out.println(mPath);
//    	System.exit(0);
 
    	return mPath;
    	
    }
}
