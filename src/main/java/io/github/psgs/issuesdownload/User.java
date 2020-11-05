package io.github.psgs.issuesdownload;

public class User {
	public String name;
	public int total=0, resolved=0;
	public double reputationA=0.0;
	 
	public User(String name) {
		 this.name=name;
	 }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getResolved() {
		return resolved;
	}
	public void setResolved(int resolved) {
		this.resolved = resolved;
	}
	public double getReputationA() {
		return reputationA;
	}
	public void setReputationA(double reputationA) {
		this.reputationA = reputationA;
	}

}
