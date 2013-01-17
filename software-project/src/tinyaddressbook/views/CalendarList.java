package tinyaddressbook.views;



import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import tinyaddressbook.model.ButtonColumn;
import tinyaddressbook.model.Database;

public class CalendarList 
{
	JPanel contentPane;
	Database mDatabase ;
	GroupLayout gl_contentPane;
	JScrollPane lastList;
	private JTextField textField;
	 Map<Integer, Integer> rowKey;
	
	/**
	 * Inizializzo l'interfaccia grafica
	 * 
	 * @return 
	 * @wbp.parser.entryPoint
	*/
	public CalendarList()
	{
		mDatabase = new Database();
		
		contentPane = new JPanel();
		
		JLabel lblRicerca = new JLabel("Ricerca");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateList(textField.getText()	);
			}
		});
		textField.setColumns(10);
		lastList = getList("");
		JScrollPane scrollBar = lastList;
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(lblRicerca, Alignment.LEADING))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRicerca)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollBar, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		contentPane.setLayout(gl_contentPane);
		
//		contentPane.add(getList());

	}
	
	/**
	 * Restuisce il JPanel con dentro la tabella appena inizializzata
	 * @return
	 */
	public JPanel init()
	{
		return contentPane;
	}
	
	/**
	 * Metodo che esegue l'aggiornamento della tabella in base alla ricerca
	 * @param search
	 */
   public void updateList(String search)
   {
	   JScrollPane newList = getList(search);
	   

	   gl_contentPane.replace(lastList, newList);
	   contentPane.setLayout(gl_contentPane);
	   lastList = newList;
	   contentPane.validate();
	   contentPane.repaint();
   }
	
	
  public  JScrollPane getList(String search) 
  {
	  //Reseto l'hashMap mi serve in Modifica o elimina
	  rowKey = new HashMap<Integer, Integer>();
	  
	  
	  //Header della tabella
	  String[] columnTitles = { "Dal", "Al", "titolo",  "Con",  "Modifica", "Elimina" };
	  
	  //Inizializzo il contenuto della tabella
	  Object[][] rowData =null;
	  
	  //Istanzio la tabella
	  final JTable table;
	  
	  //Creao un DataModel 
	  DefaultTableModel model = new DefaultTableModel(rowData,columnTitles);
	  
	  //Passo il dataModel alla tabella
	  table = new JTable(model);
	  
	  //Prelevo i dati dal database
	  ResultSet rs = mDatabase.getCalendar(search);
	  
	  
	  try 
	  {
		
		//Contatore
		int i = 0;  
		
		//Ciclo la query al db
		while(rs.next())
		{

				//Popolo l'hasMap
				rowKey.put(i, rs.getInt("id_calendar"));
				
				
				String with = "";
				if(rs.getString("id_people") != null)
					with = rs.getString("last_name")+" "+rs.getString("first_name");
				
				Date from 	= new Date(Long.parseLong(rs.getString("from")));
				Date to 	= new Date(Long.parseLong(rs.getString("to")));
				
				//Inserisco i dati nella tablla
				model.insertRow(table.getRowCount(),new Object[]{
					from.toLocaleString(),
				   	to.toLocaleString(), 
				   	rs.getString("title"), 
				   	with, 
				   	"Modifica "+rs.getString("title"),
				   	"Elimina "+rs.getString("title"),
				});
				
				//Incremento il contatore
				i++;
		  }
		 System.out.println("Tot:"+i);
		 
		 //Libero la connessione al database
		 rs.close();
		 
	  } catch (SQLException e1) { e1.printStackTrace(); }
	   
	  //Spengo il doppio click nella tabella
	  table.setCellSelectionEnabled(false);

	  //Bind Aggiorna
	  Action update = new AbstractAction()
	  {
		  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		  public void actionPerformed(ActionEvent e)
		  {
			  //Riga della tabella
			  int modelRow = Integer.valueOf( e.getActionCommand() );
            
			  //Chiamo la classe per il modifica
			  CalendarSet mCalendarSet = new CalendarSet();
			  
			  //Passo la chiave del db
			  mCalendarSet.setId_calendar(rowKey.get(modelRow));
			  
			  //Avvio l'interfaccia
			  mCalendarSet.init();
			  mCalendarSet.setVisible(true);
			
				mCalendarSet.addWindowListener( new WindowListener() {
                    @Override
                    public void windowClosing(WindowEvent we) {

                    }

					@Override
					public void windowActivated(WindowEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowClosed(WindowEvent arg0) {
						// Alla chiusura del popUp Aggiorno la lista
						updateList(textField.getText());
					}


					@Override
					public void windowDeactivated(WindowEvent arg0) {
						// TODO Auto-generated method stub

						
					}

					@Override
					public void windowDeiconified(WindowEvent arg0) {
						// TODO Auto-generated method stub

						
					}

					@Override
					public void windowIconified(WindowEvent arg0) {
						// TODO Auto-generated method stub

						
					}

					@Override
					public void windowOpened(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
                } );
			  
			  
			  
		  }
	  };     
    
    
    //Bind elimina
	Action delete = new AbstractAction()
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e)
		{
			//Chiedo conferma
			JFrame frame = new JFrame();
		    int result = JOptionPane.showConfirmDialog(frame, "Sei sicuro di voler eliminare questo Evento?");
		    
		    if(result == 0)
		    {
//				JTable table = (JTable)e.getSource();
				int modelRow = Integer.valueOf( e.getActionCommand());
				
				if(true == mDatabase.delCalendar(rowKey.get(modelRow)))
				{
					//Aggiorno la tabella
					updateList(textField.getText());
//					((DefaultTableModel)table.getModel()).removeRow(modelRow);
				}
		    }
          
		}
	};
    
	//Associo il bottone aggiorna
    ButtonColumn buttonColumnUpd = new ButtonColumn(table, update, 4);
    buttonColumnUpd.setMnemonic(KeyEvent.VK_D);
    
    //Associo il bottone elimina
    ButtonColumn buttonColumnDel = new ButtonColumn(table, delete, 5);
    buttonColumnDel.setMnemonic(KeyEvent.VK_D);
    
    
    //Restituisco la tabella dentro uno scrollPanel
    JScrollPane mJScrollPane = new JScrollPane(table);
    return mJScrollPane;

  }
  
  
  
}