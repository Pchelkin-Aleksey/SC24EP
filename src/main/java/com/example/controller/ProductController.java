package com.example.controller;

import com.example.entity.Product;
import com.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequestMapping(("catalogue/products/{productId:\\d+}"))
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        return this.productService.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    @GetMapping
    public String getProduct() {
        return "catalogue/products/product";
    }

    @GetMapping("edit")
    public String getProductEditPage() {
        return "catalogue/products/edit";
    }

//    @PostMapping("edit")
//    public String updateProduct(@ModelAttribute(name = "product", binding = false) {
//        this.productService.updateProduct();
//    }

//    @PostMapping("delete")
//    public String deleteProduct(@ModelAttribute("product") Product product) {
//        this.productService.deleteProduct(product.getId());
//        return "redirect:/catalogue/products/list";
//    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public String handleNoSuchElementException(NoSuchElementException exception, Model model,
//                                               HttpServletResponse response, Locale locale) {
//        response.setStatus(HttpStatus.NOT_FOUND.value());
//        model.addAttribute("error",
//                this.messageSource.getMessage(exception.getMessage(), new Object[0],
//                        exception.getMessage(), locale));
//        return "errors/404";
//    }
}