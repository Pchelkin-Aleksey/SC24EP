package com.example.controller;

import com.example.entity.Product;
import com.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor // создает конструктор для final полей??
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getProductsList(Model model){
        model.addAttribute("products", productService.findAll());
        return "/catalogue/products/list";
    }
    @GetMapping("create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(Product product){
        productService.createProduct(product);
        return "redirect:/catalogue/products/%d".formatted(product.getId());
    }

    @GetMapping(value = "{productId:\\d+}")
    public String editPage(@PathVariable("productId") int productId, Model model) {
        model.addAttribute("product", this.productService.findById(productId).orElseThrow());
        return "catalogue/products/product";
    }


}
