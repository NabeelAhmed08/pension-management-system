import { AuthService } from './login/login/auth.service';
import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'pension-management-portal';

  public loggedIn: boolean = false;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
  .pipe(
    map(result => result.matches),
    shareReplay()
  );

constructor(private breakpointObserver: BreakpointObserver,private authService: AuthService) {}

ngOnInit(): void {

if(this.authService.isLoggedIn()) {

  this.loggedIn= true;
}
else {

 this.loggedIn= false;}


}

logoutUser(){
  this.authService.logout();
  location.reload();
}



}
