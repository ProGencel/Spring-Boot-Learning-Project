package com.works.mvc;

import com.works.entity.Product;
import com.works.repository.ProductRepository;
import com.works.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("mvc")
@RequiredArgsConstructor
public class ProductController {

    final ProductService productService;
    final ProductRepository productRepository;

    @GetMapping("product")
    public String product(Model model ,@RequestParam(defaultValue = "1") int page)
    {
        Page<Product> productPage = productService.productList(page-1);
        model.addAttribute("productPage", productPage);
        return "product";
    }

    @GetMapping("product/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        productService.deleteOne(id);
        return "redirect:/mvc/product";
    }

    @GetMapping("product/edit/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product",product);
        return "productUpdate";
    }

}
