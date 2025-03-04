import { inject, Injectable } from "@angular/core";
import { AuthResponse } from "./auth-response.model";
import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";
import { CartService } from "app/carts/data-access/cart.service";
import { BehaviorSubject } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private router = inject(Router);
    private cartService = inject(CartService);
    isLogin = false;
    private isLoggedSubject = new BehaviorSubject<boolean>(false);
    isLogged$ = this.isLoggedSubject.asObservable();

    setConnectedUser(data: AuthResponse) {
        if (data.token) { 
            localStorage.setItem('authenticationResponse', JSON.stringify({ token: data.token }));
            console.log("Token enregistré :", data.token);
        } else {
            console.warn("Aucun token reçu !");
        }
    }

    isUserLoggedAndTokenAccessValid(): boolean {
        if (localStorage.getItem('authenticationResponse')) {
            return true;
        }
        this.router.navigate(['users/login']);
        return false;
    }

    isLoggedIn(): boolean {
        const storedResponse = localStorage.getItem("authenticationResponse");
        return !!storedResponse; 
    }
    
      
    logout() {
        localStorage.removeItem("authenticationResponse"); 
        localStorage.removeItem("email"); 
        this.cartService.clearCart();
        this.updateLoginStatus()
        this.router.navigate(['/users/login']); 
    }

    updateLoginStatus() {
        this.isLoggedSubject.next(this.isLoggedIn());
    }

    checkEmail(): boolean {
        const email = localStorage.getItem("email"); 
        console.log("Email récupéré :", email); 
    
        if (email !== null && JSON.parse(email) === "admin@admin.com") { 
            return false;
        }
    
        return true;
    }
}