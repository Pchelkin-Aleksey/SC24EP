package controller;


import client.ProductsRestClient;
import controller.payload.NewProductPayload;
import entity.Product;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@Controller
@RequiredArgsConstructor // создает конструктор для final полей??
@RequestMapping("/products")
public class ProductsController{

    private final ProductsRestClient productsRestClient;

    @GetMapping("list")
    public String getProductsList(Model model) {
        model.addAttribute("products", this.productsRestClient.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload,
                                Model model) {
        try {
            Product product = this.productsRestClient.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (HttpClientErrorException.BadRequest exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getClass());
            return "catalogue/products/new_product";
        }
    }
}
