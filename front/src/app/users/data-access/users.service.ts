import { HttpClient } from '@angular/common/http';
import { Injectable, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { AuthRequest } from './auth-request.model';
import { AuthResponse } from './auth-response.model';
import { User } from './user.model';
import { CartService } from 'app/carts/data-access/cart.service';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private apiUrl = 'http://localhost:8096/auth';
    private http = inject(HttpClient);

    login(authRequest: AuthRequest): Observable<AuthResponse>{
        return this.http.post<AuthResponse>(`${this.apiUrl}/login`, authRequest);
    }

    register(user: User): Observable<User>{
        return this.http.post(`${this.apiUrl}/register`, user);
    }
}