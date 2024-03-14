package model;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;

public class Order {
	private LocalDate date;
	private String deliveryStatus;
	private LocalDate deliveryDate;
	private int orderId;
	private ArrayList<OrderLine> orderLines;
	private Customer customer;

	public Order() {
		this.setDeliveryStatus("pending");
		orderLines = new ArrayList<OrderLine>();
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void addOrderLine(OrderLine orderLine) {
		orderLines.add(orderLine);
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [date=" + date + ", deliveryStatus=" + deliveryStatus + ", deliveryDate=" + deliveryDate
				+ ", orderId=" + orderId + ", orderLines=" + orderLines + ", customer=" + customer + "]";
	}
}
