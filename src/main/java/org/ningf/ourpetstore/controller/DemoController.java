package org.ningf.ourpetstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: Lenovo
 * @time: 2024/3/19 14:09
 */

@Controller
public class DemoController {

    @GetMapping("/demo")
    @ResponseBody
    public String demo() {
        return "demo";
    }

    @GetMapping("/demo1")
    public String demo1(Model model){
        model.addAttribute("message","Hello Thymeleaf");
        return "demo1";
    }
}
