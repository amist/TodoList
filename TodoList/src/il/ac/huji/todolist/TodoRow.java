package il.ac.huji.todolist;

import java.util.Calendar;
import java.util.Date;

public class TodoRow {
	protected int id;
	protected String text;
	protected Calendar date = Calendar.getInstance();
	
	public TodoRow() {
		
	}
	
	@SuppressWarnings("deprecation")
	public TodoRow(String text, Date date, int id) {
		this.id = id;
		this.text = text;
		this.date.set(Calendar.DAY_OF_MONTH, date.getDate());
		this.date.set(Calendar.MONTH, date.getMonth());
		this.date.set(Calendar.YEAR, date.getYear() + 1900);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	public String getDateString() {
		if (this.date == null)
			return "No due date";
		int year = this.date.get(Calendar.YEAR);
		int month = this.date.get(Calendar.MONTH) + 1;
		int day = this.date.get(Calendar.DAY_OF_MONTH);
		String dateString = day + "/" + month + "/" + year;
		return dateString;
	}
	
	public String toString() {
		return this.id + " - " + this.text + " - " + this.getDateString();
	}
	
	public void setDateFromString(String strDate) {
		String[] comp = strDate.split("/");
		this.date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(comp[0]));
		this.date.set(Calendar.MONTH, Integer.parseInt(comp[1]) - 1);
		this.date.set(Calendar.YEAR, Integer.parseInt(comp[2]));
	}
	
	@SuppressWarnings("deprecation")
	public void setDateFromDate(Date inp) {
		this.date.set(Calendar.DAY_OF_MONTH, inp.getDate());
		this.date.set(Calendar.MONTH, inp.getMonth());
		this.date.set(Calendar.YEAR, inp.getYear() + 1900);
	}
}
