package com.works.configs;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//Controller Advice Controllerlarda cikan sorunlari buraya getir diyor, basePackagesin icine yazilan yoldaki controllerlar icin kapsami daraltiyor
@ControllerAdvice(basePackages = "com.works.mvc")
public class MvcGlobalException {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException ex,
                                     RedirectAttributes redirectAttributes,
                                     HttpServletRequest request)
    {
        // full url -> http://localhost:8090/mvc/product/delete/ali
        // path -> /mvc/product/delete/ali
        // çıkarmak istediğim -> /mvc/product

        String path = request.getRequestURI();
        String[] paths = path.split("/");
        String newPath = "/" + paths[1] + "/" + paths[2];

        String paramName = ex.getName(); // Hangi parametrede patladi
        Object value = ex.getValue();    // Gelen yanlis veri ne idi
        String errorMessage = paramName + "sayisal bir deger olmalidir gelen deger : " + value;
        // Normal model ile gonderilen veriler requeste ozeldir, redirectAttributes sessiondadir.
        // addFlashAttribute ile yazariz oraya kullanildiktan hemen sonra sileriz ki cop olarak kalmasin
        redirectAttributes.addFlashAttribute("error",errorMessage);

        return "redirect:" + newPath;


    }

}
