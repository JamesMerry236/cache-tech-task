import { Component, OnInit } from '@angular/core';
import { MatRadioChange } from '@angular/material/radio';
import { ActivatedRoute } from '@angular/router';
import { Answer, Question } from '../back-end-types';
import { SurveyService } from '../survey.service';

@Component({
  selector: 'app-survey', 
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.css']
})
export class SurveyComponent implements OnInit {

  public isReady: boolean = false;

  protected route: ActivatedRoute;

  public userId: string = "";

  public questions: Array<Question> = Array<Question>();

  public answers: Array<Answer> = Array<Answer>();

  constructor(private surveyService: SurveyService, route: ActivatedRoute) 
  { 
    this.route = route;
  }

  ngOnInit(): void 
  {
    //required to prevent strict null checking error from occuring
    this.userId = this.route.snapshot.paramMap.get("userId") || "0";

    this.surveyService.getQuestions().subscribe((response) =>
      {
          this.questions = response;
          this.isReady = true;
      });
}

  radioChecked($event: MatRadioChange, qId: number)
  {
    var isUpdatedAnswer = false;
    var answerText: string = $event.value;

    for(let answer of this.answers)
    {
      if(answer.qId == qId)
      {
        let index = this.answers.indexOf(answer);
        answer.answer = answerText;
        this.answers[index] = answer;
        isUpdatedAnswer = true;
        break;
      }
    }

    if(!isUpdatedAnswer)
    {
      let answer = { qId: qId, answer: answerText };
      this.answers.push(answer);
    }
  }

  allQuestionsAnswered(): boolean
  {
    return this.answers.length < this.questions.length;
  }

  submitAnswers()
  {
    this.surveyService.submitAnswers(this.answers, +this.userId).subscribe((response) => 
    {
      if(response)
      {
        console.log("ok!");
      }
    });
  }

}
