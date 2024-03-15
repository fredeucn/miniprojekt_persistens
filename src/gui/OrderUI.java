package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.OrderController;
import db.DataAccessException;
import model.Customer;
import model.OrderLine;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class OrderUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Layout;
	private JTextField txtCustomerPhone;
	private JLabel lblCustomerName;
	private JLabel lblCustomerType;
	private JTextField txtProductBarcode;
	private JComboBox cbStock;
	private JSpinner txtProductAmount;
	
	private OrderController orderController;
	private String[] stockNames = {"Van", "Garage"};
	private JList lstOrderLines;
	private JLabel lblTotalPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderUI frame = new OrderUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws DataAccessException 
	 */
	public OrderUI() throws DataAccessException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 680, 600);
		Layout = new JPanel();
		Layout.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Layout);
		Layout.setLayout(new BorderLayout(0, 0));
		
		JButton btnCreateOrder = new JButton("Opret Ordre");
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createOrderClicked();
			}
		});
		btnCreateOrder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Layout.add(btnCreateOrder, BorderLayout.NORTH);
		
		JPanel OrderConfiguration = new JPanel();
		Layout.add(OrderConfiguration, BorderLayout.EAST);
		GridBagLayout gbl_OrderConfiguration = new GridBagLayout();
		gbl_OrderConfiguration.columnWidths = new int[]{292, 150, 0, 150, 61};
		gbl_OrderConfiguration.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_OrderConfiguration.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0};
		gbl_OrderConfiguration.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		OrderConfiguration.setLayout(gbl_OrderConfiguration);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 9;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		OrderConfiguration.add(scrollPane, gbc_scrollPane);
		
		lstOrderLines = new JList();
		scrollPane.setViewportView(lstOrderLines);
		
		JLabel lblBasket = new JLabel("Kurv");
		lblBasket.setHorizontalAlignment(SwingConstants.CENTER);
		lblBasket.setFont(new Font("Tahoma", Font.BOLD, 16));
		scrollPane.setColumnHeaderView(lblBasket);
		
		JLabel lblAddCustomer = new JLabel("Tilføj Kunde");
		lblAddCustomer.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblAddCustomer = new GridBagConstraints();
		gbc_lblAddCustomer.gridwidth = 4;
		gbc_lblAddCustomer.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddCustomer.gridx = 1;
		gbc_lblAddCustomer.gridy = 0;
		OrderConfiguration.add(lblAddCustomer, gbc_lblAddCustomer);
		
		txtCustomerPhone = new JTextField();
		txtCustomerPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_txtCustomerPhone = new GridBagConstraints();
		gbc_txtCustomerPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCustomerPhone.insets = new Insets(0, 0, 5, 5);
		gbc_txtCustomerPhone.gridx = 1;
		gbc_txtCustomerPhone.gridy = 1;
		OrderConfiguration.add(txtCustomerPhone, gbc_txtCustomerPhone);
		txtCustomerPhone.setColumns(10);
		
		JLabel lblCustomerPhone = new JLabel("Telefon");
		GridBagConstraints gbc_lblCustomerPhone = new GridBagConstraints();
		gbc_lblCustomerPhone.anchor = GridBagConstraints.WEST;
		gbc_lblCustomerPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerPhone.gridx = 2;
		gbc_lblCustomerPhone.gridy = 1;
		OrderConfiguration.add(lblCustomerPhone, gbc_lblCustomerPhone);
		
		JButton btnSetCustomer = new JButton("Tilføj Kunde");
		btnSetCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setCustomerClicked();
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnSetCustomer = new GridBagConstraints();
		gbc_btnSetCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSetCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_btnSetCustomer.gridx = 3;
		gbc_btnSetCustomer.gridy = 1;
		OrderConfiguration.add(btnSetCustomer, gbc_btnSetCustomer);
		
		lblCustomerName = new JLabel("Kunde-navn: ingen");
		lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCustomerName = new GridBagConstraints();
		gbc_lblCustomerName.gridwidth = 2;
		gbc_lblCustomerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerName.gridx = 1;
		gbc_lblCustomerName.gridy = 2;
		OrderConfiguration.add(lblCustomerName, gbc_lblCustomerName);
		
		lblCustomerType = new JLabel("Kunde-type: ingen");
		lblCustomerType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCustomerType = new GridBagConstraints();
		gbc_lblCustomerType.gridwidth = 2;
		gbc_lblCustomerType.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomerType.gridx = 3;
		gbc_lblCustomerType.gridy = 2;
		OrderConfiguration.add(lblCustomerType, gbc_lblCustomerType);
		
		JLabel lblAddProduct = new JLabel("Tilføj Produkter");
		lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblAddProduct = new GridBagConstraints();
		gbc_lblAddProduct.gridwidth = 4;
		gbc_lblAddProduct.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddProduct.gridx = 1;
		gbc_lblAddProduct.gridy = 4;
		OrderConfiguration.add(lblAddProduct, gbc_lblAddProduct);
		
		cbStock = new JComboBox(stockNames);
		GridBagConstraints gbc_cbStock = new GridBagConstraints();
		gbc_cbStock.gridwidth = 3;
		gbc_cbStock.insets = new Insets(0, 0, 5, 5);
		gbc_cbStock.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbStock.gridx = 1;
		gbc_cbStock.gridy = 5;
		OrderConfiguration.add(cbStock, gbc_cbStock);
		
		JLabel lblProductStock = new JLabel("Lager");
		GridBagConstraints gbc_lblProductStock = new GridBagConstraints();
		gbc_lblProductStock.anchor = GridBagConstraints.WEST;
		gbc_lblProductStock.insets = new Insets(0, 0, 5, 0);
		gbc_lblProductStock.gridx = 4;
		gbc_lblProductStock.gridy = 5;
		OrderConfiguration.add(lblProductStock, gbc_lblProductStock);
		
		txtProductBarcode = new JTextField();
		txtProductBarcode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_txtProductBarcode = new GridBagConstraints();
		gbc_txtProductBarcode.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductBarcode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductBarcode.gridx = 1;
		gbc_txtProductBarcode.gridy = 6;
		OrderConfiguration.add(txtProductBarcode, gbc_txtProductBarcode);
		txtProductBarcode.setColumns(10);
		
		JLabel lblProductBarcode = new JLabel("Stregkode");
		GridBagConstraints gbc_lblProductBarcode = new GridBagConstraints();
		gbc_lblProductBarcode.anchor = GridBagConstraints.WEST;
		gbc_lblProductBarcode.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductBarcode.gridx = 2;
		gbc_lblProductBarcode.gridy = 6;
		OrderConfiguration.add(lblProductBarcode, gbc_lblProductBarcode);
		
		txtProductAmount = new JSpinner();
		txtProductAmount.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		txtProductAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_txtProductAmount = new GridBagConstraints();
		gbc_txtProductAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductAmount.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductAmount.gridx = 3;
		gbc_txtProductAmount.gridy = 6;
		OrderConfiguration.add(txtProductAmount, gbc_txtProductAmount);
		
		JLabel lblProductAmount = new JLabel("Antal");
		GridBagConstraints gbc_lblProductAmount = new GridBagConstraints();
		gbc_lblProductAmount.anchor = GridBagConstraints.WEST;
		gbc_lblProductAmount.insets = new Insets(0, 0, 5, 0);
		gbc_lblProductAmount.gridx = 4;
		gbc_lblProductAmount.gridy = 6;
		OrderConfiguration.add(lblProductAmount, gbc_lblProductAmount);
		
		JButton btnAddProduct = new JButton("Tilføj Produkt");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addProductClicked();
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnAddProduct = new GridBagConstraints();
		gbc_btnAddProduct.gridwidth = 3;
		gbc_btnAddProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddProduct.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddProduct.gridx = 1;
		gbc_btnAddProduct.gridy = 7;
		OrderConfiguration.add(btnAddProduct, gbc_btnAddProduct);
		
		lblTotalPrice = new JLabel("Total Pris: 0 kr.");
		lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblTotalPrice = new GridBagConstraints();
		gbc_lblTotalPrice.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotalPrice.gridx = 0;
		gbc_lblTotalPrice.gridy = 9;
		OrderConfiguration.add(lblTotalPrice, gbc_lblTotalPrice);
		
		JButton btnFinishOrder = new JButton("Færdigør Ordre");
		btnFinishOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					finishOrderClicked();
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFinishOrder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Layout.add(btnFinishOrder, BorderLayout.SOUTH);
		
		
		
		
		init();
	}
	
	private void init() throws DataAccessException {
		this.setLocationRelativeTo(null);
		orderController = new OrderController();
		cbStock.setSelectedIndex(0);
		lstOrderLines.setCellRenderer(new OrderLinesRenderer());
	}

	private void createOrderClicked() {
		orderController.createOrder();
		updateCustomer();
		updateBasket();
	}

	private void setCustomerClicked() throws DataAccessException {
		String phoneNumber = txtCustomerPhone.getText();
		orderController.addCustomer(phoneNumber);
		updateCustomer();
	}
	
	private void addProductClicked() throws DataAccessException {
		int barcode = Integer.parseInt(txtProductBarcode.getText());
		int amount = (int) txtProductAmount.getValue();
		String stock = (String) cbStock.getSelectedItem();
		
		orderController.addProduct(barcode, amount, stock);
		updateBasket();
	}
	
	private void finishOrderClicked() throws DataAccessException, SQLException {
		orderController.finishOrder();
	}
	
	private void updateBasket() {
		DefaultListModel<OrderLine> dim = new DefaultListModel<>();
		dim.addAll(orderController.getOrder().getOrderLines());
		lstOrderLines.setModel(dim);
		
		// calculate total price
		float totalPrice = 0;
		for (OrderLine currentOrderLine : orderController.getOrder().getOrderLines()) {
			totalPrice += currentOrderLine.getAmount() * currentOrderLine.getProduct().getSalesPrice();
		}
		lblTotalPrice.setText("Total pris: " + totalPrice + " kr.");
	}
	
	private void updateCustomer() {
		Customer customer = orderController.getOrder().getCustomer();
		if (customer != null) {
			lblCustomerName.setText("Kunde-navn: " + customer.getName());
			lblCustomerType.setText("Kunde-type: " + customer.getType());
		} else {
			lblCustomerName.setText("Kunde-navn: ingen");
			lblCustomerType.setText("Kunde-type: ingen");
		}
	}
}
