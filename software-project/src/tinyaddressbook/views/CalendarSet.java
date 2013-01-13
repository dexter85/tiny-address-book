package tinyaddressbook.views;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.toedter.calendar.JDateChooser;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import tinyaddressbook.model.Database;
import tinyaddressbook.model.Item;

public class CalendarSet extends JFrame {
	public CalendarSet() {
	}

	private JPanel contentPane;
	private JDateChooser from;
	private JDateChooser to;
	private JLabel lblAssociaAUn;
	private JTextField title;
	private JTextArea description;
	
	private Integer id_calendar;

	public Integer getId_calendar() 
	{
		return id_calendar;
	}

	public void setId_calendar(Integer id_calendar) 
	{
		this.id_calendar = id_calendar;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarSet frame = new CalendarSet();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
//	public CalendarSet() 
	public void init() 
	{
		setResizable(false);
		setTitle("Nuovo Evento");
		
		Date valFrom 		= new Date();
		Date valTo 			= new Date();
		String valId_people = "0";
		String valTitle 	= "";
		String valDescription = "";
		int mSelectedIndex = 0;
		
		//Verifico se mi viene passatto un utente
		if(getId_calendar() != null)
		{
			
			
			Database mDatabase = new Database();
			
			ResultSet mResultSet = mDatabase.getCalendarByPrimary(getId_calendar());			
			
			try 
			{
				setTitle("Modifica "+mResultSet.getString("title"));

				valFrom.setTime(Long.parseLong(mResultSet.getString("from")));
				valTo.setTime(Long.parseLong(mResultSet.getString("to")));
				valId_people = mResultSet.getString("id_people");
				if(valId_people == null)
					valId_people = "0";
				
				valTitle = mResultSet.getString("title");
				valDescription = mResultSet.getString("description");
				
				//Libero la connessione
				mResultSet.close();
				


			} 
			catch (SQLException e) {e.printStackTrace(); }
			

		}
		//Prelevo tutti gli utenti
		Database mDatabase = new Database();
		
		//Prelevo gli utenti
		ResultSet rs = mDatabase.getPeople("");
		
		//Mi setto il totale degli utenti

		Vector peoples = new Vector();  
			
		
		try 
		{
			peoples.addElement(new Item(0, "Nessuna Utente"));
			int i = 0;
			while(rs.next())
			{
				i++;
				//Cerco l'id dell'utente da selezionare		
				if(Integer.parseInt(valId_people) == rs.getInt("id_people"))
					mSelectedIndex = i;
				
					
				peoples.addElement(new Item(rs.getInt("id_people"), rs.getString("last_name")+" "+rs.getString("first_name")));
				
				
			}
		rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(CalendarSet.class.getResource("/tinyaddressbook/resources/calendar_view_day.png")));
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDal = new JLabel("Dal");
		
		from = new  JDateChooser(valFrom);
//		from.setColumns(10);
		
		to = new  JDateChooser(valTo);
//		to.setColumns(10);
		
		JLabel lblAl = new JLabel("Al");
		
		lblAssociaAUn = new JLabel("Associa a un Utente");
		
		JSeparator separator = new JSeparator();
		
		final JComboBox id_people = new JComboBox(peoples);
		id_people.setSelectedIndex(mSelectedIndex);
		
//		id_people.setModel(peoples);
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblTitolo = new JLabel("Titolo");
		
		title = new JTextField();
		title.setColumns(10);
		title.setText(valTitle);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JSeparator separator_2 = new JSeparator();
		
		JButton btnSalva = new JButton("Salva");
		btnSalva.setIcon(new ImageIcon(CalendarSet.class.getResource("/tinyaddressbook/resources/accept.png")));
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String mId_Calendar = "";
				if(id_calendar != null)
					mId_Calendar = id_calendar.toString();
					
				 Item item = (Item)id_people.getSelectedItem();
				 
				 String id_user = "";
				 int valueSelect = item.getId();
				 if(valueSelect != 0)
					 id_user =  String.valueOf(valueSelect);
				 
				 
				Database mDatabase = new Database();
				mDatabase.setCalendar(mId_Calendar, id_user, String.valueOf(from.getDate().getTime()), String.valueOf(to.getDate().getTime()), title.getText(),description.getText());

				dispose();
				
			}
		});
		
		
		
		JButton btnA = new JButton("Annulla");
		btnA.setIcon(new ImageIcon(CalendarSet.class.getResource("/tinyaddressbook/resources/action_stop.gif")));
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(from, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
									.addComponent(lblDal))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(to, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblAl))
								.addGap(9))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblDescrizione)
								.addContainerGap(360, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(title, 404, 404, 404)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblTitolo)
								.addContainerGap(388, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblAssociaAUn)
								.addContainerGap(318, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(separator, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
									.addComponent(id_people, 0, 404, Short.MAX_VALUE))
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(separator_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
								.addContainerGap()))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnA)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSalva)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAl)
						.addComponent(lblDal))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(to, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(from, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAssociaAUn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(id_people, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTitolo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDescrizione)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalva)
						.addComponent(btnA))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		
		description = new JTextArea();
		description.setText(valDescription);
		scrollPane.setViewportView(description);
		contentPane.setLayout(gl_contentPane);
	}
}
