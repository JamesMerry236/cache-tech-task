package ctt.back.end.service;

public class Question 
{
	
	private long id;
	private int qType;
	private String qText;
	
	public long getId() 
	{
		return id;
	}
	
	public void setId(long id) 
	{
		this.id = id;
	}
	
	public int getqType() 
	{
		return qType;
	}
	
	public void setqType(int qType) 
	{
		this.qType = qType;
	}
	
	public String getqText() 
	{
		return qText;
	}
	
	public void setqText(String qText) 
	{
		this.qText = qText;
	}

}
