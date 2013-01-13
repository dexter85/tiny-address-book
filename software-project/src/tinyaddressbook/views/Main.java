package tinyaddressbook.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Toolkit;
import javax.swing.ImageIcon;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Main() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException 
	{
		//Imposto il look di sistema
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		//Icona dell'applicazione	
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/tinyaddressbook/resources/book.png")));
		setTitle("Tiny Address Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRubrica = new JMenu("Rubrica");
		menuBar.add(mnRubrica);
		
		JMenuItem mntmTuttiIContatti = new JMenuItem("Tutti i Contatti");
		mntmTuttiIContatti.addActionListener(new ActionListener() {
			/**
			 * @todo Caricare tutta la rubrica
			 */
			public void actionPerformed(ActionEvent arg0) 
			{
				PeopleList mPeopleList =  new PeopleList();
				setContentPane(mPeopleList.init());
				validate();
				repaint();
			}
		});
		mntmTuttiIContatti.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/book_open.png")));
		mnRubrica.add(mntmTuttiIContatti);
		
		JMenuItem mntmNuovoContantto = new JMenuItem("Nuovo Contatto");
		mntmNuovoContantto.addActionListener(new ActionListener() {
			
			/**
			 * @todo Aprire il popup di inserzione contatto
			 */
			public void actionPerformed(ActionEvent e) 
			{
				PeopleSet mPeople = new PeopleSet();
				mPeople.setId_people(null);
				mPeople.init();
				mPeople.setVisible(true);
				
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setLayout(new BorderLayout(0, 0));
				setContentPane(contentPane);
				validate();
				repaint();
				
			}
		});
		mntmNuovoContantto.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/add.png")));
		mnRubrica.add(mntmNuovoContantto);
		
		JMenu mnCalendario = new JMenu("Calendario");
		menuBar.add(mnCalendario);
		
		JMenuItem mntmTuttiGliEventi = new JMenuItem("Tutti gli eventi");
		mntmTuttiGliEventi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				CalendarList mCalendarList =  new CalendarList();
				setContentPane(mCalendarList.init());
				validate();
				repaint();
			}
		});
		mntmTuttiGliEventi.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/calendar_view_day.png")));
		mnCalendario.add(mntmTuttiGliEventi);
		
		JMenuItem mntmNuovoEvento = new JMenuItem("Nuovo Evento");
		mntmNuovoEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				CalendarSet mCalendarSet = new CalendarSet();
				mCalendarSet.setId_calendar(null);
				mCalendarSet.init();
				mCalendarSet.setVisible(true);	
				
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setLayout(new BorderLayout(0, 0));
				setContentPane(contentPane);
				validate();
				repaint();
				
			}
		});
		mntmNuovoEvento.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/add.png")));
		mnCalendario.add(mntmNuovoEvento);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
//		PeopleList mPeopleList =  new PeopleList();
//		setContentPane(mPeopleList.init());
	}
	
	

	
}
