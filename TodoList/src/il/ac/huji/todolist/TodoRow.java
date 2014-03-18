package il.ac.huji.todolist;

public class TodoRow {
	protected String text;
	protected String date;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString() {
		return this.text + " - " + this.date;
	}
}
