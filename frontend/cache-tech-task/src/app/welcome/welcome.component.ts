import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from "@angular/material/button";
import { ActivatedRoute, Router } from '@angular/router';
import { SurveyService } from '../survey.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
}) 
export class WelcomeComponent implements OnInit {

  public username: string = "";

  protected route: ActivatedRoute;

  constructor
  (
    private surveyService: SurveyService, 
    route: ActivatedRoute,
    private router: Router
  ) 
  { 
    this.route = route;
  }

  ngOnInit(): void {
  }

  getUser()
  {
    console.log("here");
    this.surveyService.getUser(this.username)
      .subscribe((response) => {
        this.router.navigate([`../survey/${response}`], { relativeTo: this.route });
      });
  }

}
