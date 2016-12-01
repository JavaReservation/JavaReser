package group42.hotel.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import group42.hotel.business.Hotel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class GUIViewController extends JFrame implements Observable {

	private JPanel contentPane;
	private Hotel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		/**
		 * EventQueue.invokeLater(new Runnable() { public void run() { try {
		 * GUIViewController frame = new GUIViewController();
		 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace();
		 * } } });
		 */
	}

	/**
	 * Create the frame.
	 */
	public GUIViewController(Hotel model) {
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		this.setVisible(true);
	}

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub

	}

}
