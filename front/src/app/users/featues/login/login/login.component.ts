import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'app/users/data-access/auth.service';
import { User } from 'app/users/data-access/user.model';
import { UserService } from 'app/users/data-access/users.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  fb = inject(FormBuilder);
  authService = inject(AuthService);
  userService = inject(UserService);
  islogged = false;
  loginForm !: FormGroup;
  messageError: string = "";
  registerLink: string = "Veuillez s'inscrire";
  isMessageLogin: boolean = false;
  private router = inject(Router);
  
    ngOnInit(): void {
      this.loginForm = this.fb.group({
        email: ['', Validators.compose([Validators.required, Validators.email])],
        password: ['', Validators.required]
      });
    }
  
  login(): void {
      this.userService.login(this.loginForm.value)
          .subscribe({
              next: (data) => {
                  localStorage.setItem('email', JSON.stringify(this.loginForm.value.email));
                  this.authService.setConnectedUser(data);
                  this.authService.updateLoginStatus()
                  this.router.navigate(['home']);
            },
              error: (error) => {
                  this.messageError = "Votre email ou votre mot de passe n'existe pas!";
                  this.isMessageLogin = true
          }
      });
    }
  
    navByRegister(){
      debugger
      this.authService.logout();
      this.router.navigateByUrl('users/register')
    }

}
