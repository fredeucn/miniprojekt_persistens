package model;
import java.time.*;

public class Invoice {
	private int invoiceNumber;
	private LocalDate paymentDate;
	private String paymentStatus;
	
	public Invoice(int invoiceNumber, LocalDate paymentDate, String paymentStatus) {
		this.setInvoiceNumber(invoiceNumber);
		this.setPaymentDate(paymentDate);
		this.setPaymentStatus(paymentStatus);
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}
