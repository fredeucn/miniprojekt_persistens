package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.OrderLine;

public class OrderLinesRenderer implements ListCellRenderer<OrderLine> {
	private DefaultListCellRenderer dlcr = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(JList<? extends OrderLine> list,
			OrderLine value, int index, boolean isSelected, boolean cellHasFocus) {

		String res = value.getAmount() + "x " + value.getProduct().getName() + 
				" | " + value.getProduct().getSalesPrice() * value.getAmount() + " kr. | " + 
				value.getProduct().getSalesPrice() + " kr.";

		return dlcr.getListCellRendererComponent(list, res, index, isSelected, cellHasFocus);

	}
}
