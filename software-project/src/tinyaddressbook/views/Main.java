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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;


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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/tinyaddressbook/resources/main_addressbook.png")));
		setTitle("Tiny Address Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 386);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRubrica = new JMenu("Rubrica");
		mnRubrica.setBackground(SystemColor.windowBorder);
		mnRubrica.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/book.png")));
		menuBar.add(mnRubrica);
		
		JMenuItem mntmTuttiIContatti = new JMenuItem("Tutti i Contatti");
		mntmTuttiIContatti.addActionListener(new ActionListener() {
			/**
			 * @todo Caricare tutta la rubrica
			 */
			public void actionPerformed(ActionEvent arg0) 
			{
				repaintPeopleList();
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
				
				mPeople.addWindowListener( new WindowListener() {
                    @Override
                    public void windowClosing(WindowEvent we) {

                    }

					@Override
					public void windowActivated(WindowEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowClosed(WindowEvent arg0) {
						// TODO Auto-generated method stub
						repaintPeopleList();
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
		});
		mntmNuovoContantto.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/add.png")));
		mnRubrica.add(mntmNuovoContantto);
		
		JMenu mnCalendario = new JMenu("Calendario");
		mnCalendario.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/calendar_view_day.png")));
		menuBar.add(mnCalendario);
		
		JMenuItem mntmTuttiGliEventi = new JMenuItem("Tutti gli eventi");
		mntmTuttiGliEventi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				repaintCalendarList();
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
						// TODO Auto-generated method stub
						repaintCalendarList();
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
		});
		mntmNuovoEvento.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/add.png")));
		mnCalendario.add(mntmNuovoEvento);
		
		emptyBackground();
//		PeopleList mPeopleList =  new PeopleList();
//		setContentPane(mPeopleList.init());
	}
	
	/**
	 * 
	 */
	public void repaintPeopleList()
	{
		PeopleList mPeopleList =  new PeopleList();
		setContentPane(mPeopleList.init());
		validate();
		repaint();
	}

	public void repaintCalendarList()
	{
		CalendarList mCalendarList =  new CalendarList();
		setContentPane(mCalendarList.init());
		validate();
		repaint();
	}
	
	/**
	 * 
	 */
	public void emptyBackground()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				repaintPeopleList();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/main_addressbook_open.png")));
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				repaintCalendarList();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/main_calendar_open.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		validate();
		repaint();
	}
	
}
