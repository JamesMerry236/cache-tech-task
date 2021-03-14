package ctt.back.end.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import ctt.back.end.service.Answer;
import ctt.back.end.service.Question;
import ctt.back.end.service.SurveyService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SurveyController 
{
	
	@GetMapping("/questions")
	public List<Question> getQuestions()
	{
		return SurveyService.getQuestions();
	}
	
	@PostMapping("/user/{username}")
	public long getUser(@PathVariable String username)
	{
		return SurveyService.getUser(username);
	}
	
	@PostMapping("/submitAnswers/{userId}")
	public boolean submitAnswers(@PathVariable long userId, @RequestBody List<Answer> answers)
	{
		return SurveyService.submitAnswers(userId, answers);
	}
	
}
