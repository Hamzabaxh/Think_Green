package entity;

import java.sql.Timestamp;

public class Consomation {
	private int id  ; 
	
	private Timestamp temp ;
	private String userName ; 
	private String need ;
	private float consomation  ;
	
	
	public Consomation(int id,String need ,  Timestamp jour, float consomation , String userId) {
		this.setNeed(need); 
		this.id = id;
		this.temp = jour;
		this.userName =userId; 
		this.consomation = consomation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getTemp() {
		return temp;
	}
	public void setTemp(Timestamp jour) {
		this.temp = jour;
	}
	public float getConsomation() {
		return consomation;
	}
	public void setConsomation(float consomation) {
		this.consomation = consomation;
	}
	@Override
	public String toString() {
		return "consomation [id=" + id + ", jour=" + temp + ", consomation=" + consomation + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userId) {
		this.userName = userId;
	}
	public String getNeed() {
		return need;
	}
	public void setNeed(String need) {
		this.need = need;
	} 
	

}
