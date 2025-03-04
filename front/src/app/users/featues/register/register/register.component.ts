import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthRequest } from 'app/users/data-access/auth-request.model';
import { User } from 'app/users/data-access/user.model';
import { UserService } from 'app/users/data-access/users.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  fb = inject(FormBuilder);
  registerForm !: FormGroup;
  messageError = "";
  isError = false;
  private userService = inject(UserService);
  private router = inject(Router);

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      firstname: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      password: ['', Validators.required]
    });
  }

  inscrire(): void {
    debugger;
    this.userService.register(this.registerForm.value).subscribe({
      next: (data) => { 
        this.router.navigateByUrl('/users/login');
      },error: (error) => {
        this.messageError = "L'email est dèja existe!";
        this.isError = true;
     }
    })
  }

  navByLogin(){
    this.router.navigateByUrl('/users/login')
  }
}
