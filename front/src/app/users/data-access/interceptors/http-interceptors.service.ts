import { HttpHeaders, HttpInterceptorFn, HttpRequest } from "@angular/common/http";
import { AuthResponse } from "../auth-response.model";

export const httpInterceptor: HttpInterceptorFn = (req: HttpRequest<any>, next) => {
    const storedResponse = localStorage.getItem("authenticationResponse");
    if (storedResponse) {
      try {
        const authenticationResponse = JSON.parse(storedResponse);
        if (authenticationResponse.token) {
          const authReq = req.clone({
            setHeaders: {
              Authorization: `Bearer ${authenticationResponse.token}`
            },
            withCredentials: true
          });
          return next(authReq);
        }
      } catch (error) {
        localStorage.removeItem("authenticationResponse");
      }
    }
    return next(req);
  };


