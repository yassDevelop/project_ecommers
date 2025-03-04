import { Routes } from "@angular/router";
import { ProductListComponent } from "./features/product-list/product-list.component";
import { canActivate } from "app/users/data-access/guard/application-guard.service";

export const PRODUCTS_ROUTES: Routes = [
	{
		path: "all",
		component: ProductListComponent,
		canActivate: [canActivate]
	},
	{ path: "**", redirectTo: "all" },
];
