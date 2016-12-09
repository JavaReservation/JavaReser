package group42.hotel.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.*;
import group42.hotel.business.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("serial")
public class GUIViewController extends JFrame implements Observer {

	private JPanel contentPane;
	private JPanel resultPanel;
	private JTextArea resultText;
	private JPanel getEmailPanel;
	private JTextField email;
	private Hotel model;

	public GUIViewController(Hotel model) {
		setResizable(false);
		setTitle("Dawson Hotel");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(getTitlePanel(), BorderLayout.NORTH);
		contentPane.add(getCenterPanel(), BorderLayout.CENTER);
		contentPane.add(getBottomPanel(), BorderLayout.SOUTH);

		this.setVisible(true);

		// changes made by Werner

		this.model = model;
		model.addObserver(this);

	}

	private JPanel getTitlePanel() {
		JPanel titlePanel = new JPanel();
		JLabel lblDawsonHotelInfo = new JLabel("Dawson Hotel - Information Desk");
		lblDawsonHotelInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		titlePanel.add(lblDawsonHotelInfo);
		titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		return titlePanel;
	}

	private JPanel getCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel dataEntry = new JPanel();
		centerPanel.add(dataEntry);

		getEmailPanel = new JPanel();
		dataEntry.add(getEmailPanel);
		getEmailPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblEmail = new JLabel("Enter email address: ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		getEmailPanel.add(lblEmail, BorderLayout.WEST);

		email = new JTextField();
		getEmailPanel.add(email);
		email.setColumns(25);

		dataEntry.add(getButtonPanel());

		resultPanel = new JPanel();
		resultPanel.setVisible(true);
		centerPanel.add(resultPanel);

		resultText = new JTextArea();
		resultPanel.add(resultText);
		resultText.setVisible(true);

		return centerPanel;
	}

	private JPanel getButtonPanel() {
		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton custInfo = new JButton("Customer Info");

		buttonPanel.add(custInfo);

		JButton resInfo = new JButton("Reservation Info");

		buttonPanel.add(resInfo);

		custInfo.addActionListener(new CustomerInfo());
		resInfo.addActionListener(new ReservationInfo());

		return buttonPanel;
	}

	private JPanel getBottomPanel() {
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		bottomPanel.add(panel);

		JButton exit = new JButton("Exit");
		panel.add(exit);

		return bottomPanel;
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		resultText.setText(arg1.toString());

	}

	private class CustomerInfo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				Customer cusInfo = model.findCustomer(email.getText());
				resultText.setText(cusInfo.toString());

			} catch (Exception e) {
				System.out.println(e.getMessage() + " =======  " + e.toString());

				JOptionPane.showMessageDialog(resultText, e.getMessage() + e.getStackTrace());

			} 

		}

	}

	private class ReservationInfo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				Reservation resInfo = model.findReservations(model.findCustomer(email.getText())).get(0);

				resultText.setText(resInfo.getRoom().toString());

			} catch (Exception e) {
				System.out.println(e.getMessage() + " =======  " + e.getLocalizedMessage());

				JOptionPane.showMessageDialog(resultText, e.getMessage());

			}

		}

	}
}
