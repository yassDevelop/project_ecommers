import { inject } from "@angular/core";
import { UserService } from "../users.service";
import { AuthService } from "../auth.service";

export const canActivate = () =>{
    const authService = inject(AuthService);
    return authService.isUserLoggedAndTokenAccessValid();
}