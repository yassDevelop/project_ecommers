import { Routes } from "@angular/router";
import { HomeComponent } from "./shared/features/home/home.component";
import { CartComponent } from "./carts/data-access/cart/cart.component";
import { ContactComponent } from "./contact/contact/contact.component";
import { canActivate } from "./users/data-access/guard/application-guard.service";

export const APP_ROUTES: Routes = [
  {
    path: "home",
    component: HomeComponent,
  },
  {
    path: "products",
    loadChildren: () =>
      import("./products/products.routes").then((m) => m.PRODUCTS_ROUTES)
  },
  {
    path: "users",
    loadChildren: () =>
      import("./users/users.routes").then((m) => m.USERS_ROUTES)
  },
  {
    path: "cart",
    component: CartComponent,
    canActivate: [canActivate]
  },
  {
    path: "contact",
    component: ContactComponent,
    canActivate: [canActivate]
  },
  { path: "", redirectTo: "home", pathMatch: "full" },
];
