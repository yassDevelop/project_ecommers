import { Component, OnInit, inject, signal } from "@angular/core";
import { Product } from "app/products/data-access/product.model";
import { ProductsService } from "app/products/data-access/products.service";
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component";
import { ButtonModule } from "primeng/button";
import { CommonModule } from '@angular/common';
import { CardModule } from "primeng/card";
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import { CartService } from "app/carts/data-access/cart.service";
import { MessageService } from "primeng/api";
import { AuthService } from "app/users/data-access/auth.service";

const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [DataViewModule, CardModule, ButtonModule, DialogModule, ProductFormComponent, CommonModule],
})
export class ProductListComponent implements OnInit {
  private readonly productsService = inject(ProductsService);
  private cartService = inject(CartService);
  private messageService = inject(MessageService);
  private authService = inject(AuthService);

  public readonly products = this.productsService.products;

  public isDialogVisible = false;
  public isCreation = false;
  public isAdmin = false;
  public readonly editedProduct = signal<Product>(emptyProduct);

  ngOnInit() {
    this.productsService.getAllProducts().subscribe();
     this.isAdmin= this.authService.checkEmail();
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    if (product.id && product.id > 0) {  
      this.productsService.delete(product.id).subscribe();
  } else {
      console.warn("La suppression est invalide :", product.id);
  }
  }

  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe();
    } else {
      this.productsService.update(product).subscribe();
    }
    this.closeDialog();
  }

  public onCancel() {
    this.closeDialog();
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }

  public addCart(product: Product) {
    this.cartService.addToCart(product);
    this.messageService.add({
      summary: "a été ajouté au panier !",
      detail: "Produit a été ajouté au panier avec succès",
      icon: "pi pi-shopping-cart",
      severity: "success"
  });
  }
}
