package ErdosNumbers;

public class Researcher {
	private String name;
	private int score;
	

	public Researcher() {
		this.name = "";
		this.score = 0;
	}
	public Researcher(String name, int score) {
		this.name = name;
		this.score = score;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean equals(Object o) {
		if(o instanceof Researcher){
			Researcher p = (Researcher)o;
			return this.name.equals(p.name);
		}else{
			return false;	
		}
	}
	public int hashCode() {
		return this.name.hashCode();
	}
	public String toString() {
		return this.name + ": " + this.score;
	}
	
}
