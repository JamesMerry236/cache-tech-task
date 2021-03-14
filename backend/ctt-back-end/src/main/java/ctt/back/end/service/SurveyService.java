package ctt.back.end.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SurveyService 
{
	private static final String dbUrl = "jdbc:postgresql://localhost:5432/survey";
	private static final String dbUname = "postgres";
	private static final String dbPswd = "techtask";

	public static List<Question> getQuestions()
	{
		Connection c = null;
		Statement stmt = null;
	
		try
		{
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbUrl, dbUname, dbPswd);
			c.setAutoCommit(false);
			
			String sql = "SELECT * FROM QUESTIONS;";
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			List<Question> questions = new ArrayList<>();
			while(rs.next())
			{
				Question question = new Question();
				question.setId(rs.getLong("id"));
				question.setqType(rs.getInt("id"));
				question.setqText(rs.getString("question_text"));
				
				questions.add(question);
			}
			
			c.close();
			return questions;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	public static Long getUser(String username)
	{
		Connection c = null;
		Statement stmt = null;
		
		try
		{
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbUrl, dbUname, dbPswd);
			c.setAutoCommit(true);
			
			String sqlSelectUser = "SELECT id FROM users WHERE users.username=\'" + username + "\';";
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelectUser);
			
			if(rs.next())
			{
				c.close();
				return rs.getLong("id");
			}
			else
			{
				String sqlInsertUser = "INSERT INTO users(username) values (\'" + username + "\');";
				stmt.executeUpdate(sqlInsertUser);
				rs = stmt.executeQuery(sqlSelectUser);
				
				rs.next();
				c.close();
				return rs.getLong("id");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	public static boolean submitAnswers(long userId, List<Answer> answers)
	{
		Connection c = null;
		Statement stmt = null;
		
		try
		{
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbUrl, dbUname, dbPswd);
			c.setAutoCommit(true);
			
			int surveyAttempt = 1;
			String sqlGetNumberOfSurveyAttempts = "SELECT COUNT(DISTINCT survey_attempt) FROM answers WHERE answers.user_id=" + userId + ";";
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sqlGetNumberOfSurveyAttempts);
			
			while(rs.next())
			{
				surveyAttempt = surveyAttempt + rs.getInt("count");
			}
			
			String sqlInsertAnswers = "INSERT INTO answers(user_id, question_id, answer, survey_attempt) values ";
			for(Answer answer : answers)
			{
				sqlInsertAnswers = sqlInsertAnswers + "(" + userId + ", " + answer.getqId() + ", " + answer.getAnswer() + ", " + surveyAttempt +"),";
			}
			sqlInsertAnswers = sqlInsertAnswers.substring(0, sqlInsertAnswers.length()-1);
			sqlInsertAnswers = sqlInsertAnswers + ";";
			
			stmt.executeUpdate(sqlInsertAnswers);
			c.close();
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	
}
