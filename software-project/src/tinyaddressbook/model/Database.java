package tinyaddressbook.model;


import java.io.File;
import java.sql.*;
import java.util.Random;

import org.sqlite.SQLiteConfig;

public class Database 
{
	public static String fileName = "database.sqlite";
	private Connection conn;
	
	/**
	 * Ogni volta che viene chiamato il db verifica se è presente un database
	 * e se nn è presente ne crea uno nuovo
	 */
	public Database() 
	{
		boolean firstInser;
		
		//Verifico se è presente il file del database
		if(false == new File(fileName).isFile())
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
	            conn = DriverManager.getConnection("jdbc:sqlite:"+fileName, config.toProperties());
	        } 
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	            System.exit(1);
	        }
		}
		
		if(true == firstInser)
		{
			setStructure();
			
		}
	}
	
	/**
	 * 
	 * @param id_people
	 * @return
	 */
    public ResultSet getPeopleByPrimary(Integer id_people)
    {
		String sql = "SELECT * FROM people WHERE id_people = '"+id_people.toString()+"'";
		
		System.out.println(sql);
		
    	ResultSet resultSet = null;
		try 
		{
			Statement stat;
			stat = conn.createStatement();
			


			resultSet = stat.executeQuery(sql);
			
			return resultSet;

			
		} catch (SQLException e) { e.printStackTrace(); }
		return resultSet;

    }
	
    
    public void setPeople(String id_people, String  first_name, String  last_name, String  gender, String born, String phone, String email, String town, String street, String street_number)
    {
		try 
		{
			Statement stat;
			stat = conn.createStatement();
	    	
			if(id_people == "")
	    	{
	            String insert = "INSERT INTO people " +
						" (first_name,last_name,gender,born,phone,email,town,street,street_number) " +
						"VALUES " +
						"('"+first_name+"', '"+last_name+"', '"+gender+"', '"+born+"', '"+phone+"', '"+email+"', '"+town+"', '"+street+"', '"+street_number+"');";
	            System.out.println(insert);
	            stat.executeUpdate(insert);
	            stat.close();
	    	}
			else
			{
				
			}
    	
		} catch (SQLException e) { e.printStackTrace(); }
    	
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
			Statement stat;
			stat = conn.createStatement();
			
			String sql = "DELETE FROM people WHERE id_people = '"+id_people.toString()+"'";
			int delete = stat.executeUpdate(sql);
			
			if(delete == 1)
				mReturn = true;
			
		} catch (SQLException e) { e.printStackTrace(); }

		return mReturn;
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
    	Statement statement = null;
    	ResultSet resultSet = null;
    	
    	String where = "";
    	if("" != search)
    		where = " WHERE first_name LIKE '%"+search+"%' OR last_name LIKE '%"+search+"%'  OR phone LIKE '%"+search+"%' ";
    	
    	String query = "SELECT * FROM people "+where+" ORDER BY last_name, first_name ASC";
    	
		try 
		{
			statement = conn.createStatement();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
		try 
		{
			return resultSet = statement.executeQuery(query);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;  
 
    }
	
	
	
	/**
	 * 
	 * Verifica se è  presente il driver per sqlite all'interno del progetto
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
            Statement stat = conn.createStatement();
            
            //Creazione delle tabelle nel database
            stat.executeUpdate("CREATE TABLE people (id_people INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT, gender TEXT, born TEXT, phone TEXT, email TEXT, town TEXT, street TEXT, street_number TEXT);");
            
            stat.close();
        }
        catch(SQLException e)
        {
        	e.getErrorCode();
        }
        setDeveloperInser();
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
            	Statement stat = conn.createStatement();
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
}
