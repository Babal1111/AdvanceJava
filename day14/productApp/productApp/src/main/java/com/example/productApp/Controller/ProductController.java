package com.example.productApp.Controller;


import com.example.productApp.Model.Product;
import com.example.productApp.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @ModelAttribute("product")
    public Product getProduct(){
        return  new Product();
    }


    @GetMapping("/list")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "product-list";
    }
    ///  adding method for postman testing
    // responsebody for get
    @GetMapping("/list2")
    @ResponseBody
    public List<Product> showProducts(){
        return productService.getAllProduct();
    }


    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }
    // adding method for postman testing
    @PostMapping("/add2")
    @ResponseBody
    public Product addProduct(@RequestBody Product product){
        return productService.returnSavedProduct(product);
    }


    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult result,
                              SessionStatus status) {
        if(result.hasErrors()){
            return "product-form";
        }
        productService.saveProduct(product);
        status.setComplete();
        return "redirect:/products/list";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("product", productService.getProductById(id));
        Product product = productService.getProductById(id)
                        .orElseThrow(()-> new RuntimeException("Product not found"));

        model.addAttribute("product",product);
        return "product-form";
    }

//    @GetMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable("id") Long id) {
//        productService.deleteProuductById(id);
//        return "redirect:/products/list";
//    }

    @GetMapping("/count")
    public String getProductCount(){
        return "Total products: "+ productService.getAllProduct().size();
    }

    @DeleteMapping("/{id}")
    public  String deleteProduct(@PathVariable Long id){
        productService.deleteProuductById(id);
        return "Product Deleted";
    }

    //controller level exceptional handeling
    @ExceptionHandler(RuntimeException.class)
    public  String handelRuntimeException(RuntimeException ex, Model model){
        model.addAttribute("errorMessage","Something went wrong");
//        model.addAttribute("errorMessage",ex.getMessage());

        return "error-page";
    }


}