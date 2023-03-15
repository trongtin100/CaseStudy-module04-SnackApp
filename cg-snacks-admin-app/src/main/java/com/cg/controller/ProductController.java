package com.cg.controller;

import com.cg.model.entity.Cart;
import com.cg.model.entity.Category;
import com.cg.model.entity.Product;
import com.cg.model.dto.ProductDto;
import com.cg.service.category.CategoryService;
import com.cg.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Optional;




@Controller
@SessionAttributes("cart")

public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }

    @Value("${upload.path}")
    private String fileUpload;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/products")
    public ModelAndView listProducts(@RequestParam("search") Optional<String> search, @PageableDefault(value=5) Pageable pageable){
        Page<Product> products;
        if(search.isPresent()){
            products = productService.findAllByNameContaining(search.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }


    @GetMapping("/create-product")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("productDto", new ProductDto());
        return modelAndView; 
    }

    @PostMapping("/save")
    public ModelAndView save2Product(@Valid @ModelAttribute("productDto") Product product, BindingResult bindingResult) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        if(bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("product/create");
            return modelAndView;
        }
        modelAndView.addObject("message", "New product created successfully");
        return modelAndView;
    }


    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit-product")
    public ModelAndView updateCustomer(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Product updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return "redirect:products";
    }

//    @GetMapping("findAll")
//    public ModelAndView findAllPage(@PageableDefault(value=3) Pageable pageable){
//        ModelAndView modelAndView = new ModelAndView("list");
//        Page<Product> products = productService.findAllPage(pageable);
//        modelAndView.addObject("products", products);
//        return modelAndView;
//    }

//    @PostMapping("/save")
//    public ModelAndView saveProduct(@ModelAttribute ProductDto productDto) {
////        MultipartFile multipartFile;
////        multipartFile = productDto.getImage();
////        String fileName = multipartFile.getOriginalFilename();
////        try {
////            FileCopyUtils.copy(productDto.getImage().getBytes(), new File(fileUpload + fileName));
////        } catch (IOException ex) {
////            ex.printStackTrace();
////        }
////        Product product = new Product(productDto.getId(), productDto.getName(),fileName,
////                productDto.getDescription(), productDto.getPrice(),productDto.getQuantity(),productDto.getNotes()
////                ,productDto.getCategory());
//        productService.save(product);
//        ModelAndView modelAndView = new ModelAndView("/create");
//        modelAndView.addObject("productDto", productDto);
//        modelAndView.addObject("message", "Created new product successfully !");
//        return modelAndView;
//    }


    @GetMapping("/shop")
    public ModelAndView showShop() {
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }
//    @GetMapping("/shopping-cart")
//    public ModelAndView showCart (@SessionAttribute("cart") Cart cart){
//        ModelAndView modelAndView = new ModelAndView("/cart");
//        modelAndView.addObject("cart",cart);
//        return modelAndView;
//
//    }
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error.404";
        }
        if (action.equals("show")) {
            cart.addProduct(productOptional.get());
            return "redirect:/shopping";
        }
        if (action.equals("show2")) {
            cart.substractProduct(productOptional.get());
            return "redirect:/shopping";
        }
        if (action.equals("list")){
            cart.addProduct(productOptional.get());
            return "product/cart";
        }
        return  null;
    }

}
