import { Component, inject } from "@angular/core";
import { Router, RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { CommonModule } from "@angular/common";
import { CartService } from "./carts/data-access/cart.service";
import { BadgeModule } from 'primeng/badge';
import { AuthService } from "./users/data-access/auth.service";
import { ToastModule } from "primeng/toast";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, CommonModule, BadgeModule, ToastModule],
})
export class AppComponent {
  public authService = inject(AuthService);
  public cartService = inject(CartService);
  title = "ALTEN SHOP";
  isLogged = false;
  router = inject(Router);

  sizeCart = this.cartService.sizeCart;

  constructor() {
    this.authService.isLogged$.subscribe((isLogged) => {
      this.isLogged = isLogged; 
    });
  }

  
}
