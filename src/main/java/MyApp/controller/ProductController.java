package MyApp.controller;

import MyApp.entity.Product;
import MyApp.service.ImpProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/admin/product")
public class ProductController {
    @Autowired
    ImpProductService service;
    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("products", service.getAll(0, 1));
        return "admin/product/list";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getDetail(Model model, @PathVariable int id) {
        model.addAttribute("product", service.getProductById(id));
        return "admin/product/detail";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product/create";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute Product product) {
        service.save(product);
        return "redirect:/admin/product";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        Product product = service.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/edit";
    }
    @RequestMapping(method = RequestMethod.POST, path = "/{id}")
    public String update(@ModelAttribute Product product, @PathVariable int id) {
        service.update(product, id);
        return "redirect:/admin/product";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public String delete(@ModelAttribute Product product, @PathVariable int id) {
        service.delete(id);
            return "redirect:/admin/product";
    }
}
