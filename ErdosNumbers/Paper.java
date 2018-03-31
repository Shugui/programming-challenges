package ErdosNumbers;

import java.util.HashSet;

public class Paper {
	private HashSet<Researcher> authors;
	private String title;
	

	public Paper() {
		authors = new HashSet<Researcher>();
		title = "";
	}
	public Paper(HashSet<Researcher> authors, String title) {
		this.authors = authors;
		this.title = title;
	}
	
	public HashSet<Researcher> getAuthors() {
		return authors;
	}
	public void setAuthors(HashSet<Researcher> authors) {
		this.authors = authors;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean equals(Object o) {
		if(o instanceof Paper){
			Paper p = (Paper)o;
			return this.title.equals(p.title);
		}else{
			return false;	
		}
	}
	public int hashCode() {
		return this.title.hashCode();
	}
	public String toString() {
		return this.title + "{" + authors.toString() + "}";
	}
	

}
