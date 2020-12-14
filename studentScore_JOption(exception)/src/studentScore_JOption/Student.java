package studentScore_JOption;

import java.text.DecimalFormat;

public class Student {
	private String name;
	private int num;
	private static int Fnum = 1001;
	private int mid;
	private int fin;
	private int sum;
	private double avg;
	private int rank;
	
	public Student() {;}
	
	public int getSum() {return sum;}
	public void setSum(int sum) {this.sum = sum;}
	public String getName() {return name;}
	public void setName(String name) {this.num = Fnum++;this.name = name;}
	public int getNum() {return num;}
	public int getMid() {return mid;}
	public void setMid(int mid) {this.mid = mid;}
	public int getFin() {return fin;}
	public void setFin(int fin) {this.fin = fin;}
	public double getAvg() {return avg;}
	public void setAvg(double avg) {this.avg = avg;}
	public int getRank() {return rank;}
	public void setRank(int rank) {this.rank = rank;}
	@Override
	public String toString() {
		DecimalFormat form = new DecimalFormat("#.##");
		String dcAvg = form.format(avg);
		return "이름: " + name + ", 학번: " + num + ", 중간고사: " + mid + ", 기말고사: " + fin + ", 평균: " + dcAvg + ", 등수: "
				+ rank + "\n";
	}
}
