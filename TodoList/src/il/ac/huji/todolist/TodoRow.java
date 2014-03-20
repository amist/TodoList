package il.ac.huji.todolist;

import java.util.Calendar;

public class TodoRow {
	protected String text;
	protected Calendar date = Calendar.getInstance();
	
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
		int year = this.date.get(Calendar.YEAR);
		int month = this.date.get(Calendar.MONTH) + 1;
		int day = this.date.get(Calendar.DAY_OF_MONTH);
		String dateString = day + "/" + month + "/" + year;
		return dateString;
	}
	
	public String toString() {
		return this.text + " - " + this.date;
	}
	
	public void setDateFromString(String strDate) {
		String[] comp = strDate.split("/");
		this.date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(comp[0]));
		this.date.set(Calendar.MONTH, Integer.parseInt(comp[1]) - 1);
		this.date.set(Calendar.YEAR, Integer.parseInt(comp[2]));
	}
}
