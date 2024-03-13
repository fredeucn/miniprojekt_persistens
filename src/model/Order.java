package model;
import java.time.*;
import java.util.ArrayList;

public class Order {
	private LocalDate date;
	private String deliveryStatus;
	private LocalDate deliveryDate;
	private int orderId;
	private ArrayList<OrderLine> orderLines;
	
	public Order(LocalDate date, String deliveryStatus, LocalDate deliveryDate, int orderId) {
		// TODO Auto-generated constructor stub
		this.setDate(date);
		this.setDeliveryStatus(deliveryStatus);
		this.setDeliveryDate(deliveryDate);
		this.setOrderId(orderId);
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

	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
}
