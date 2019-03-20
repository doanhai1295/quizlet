package com.quizlet.web.app.up.test.controller;

import com.quizlet.core.app.dto.TestDTO;
import com.quizlet.core.app.service.TestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
 
    @Autowired
    private TestService testService;
    
    @GetMapping("/")
    public ModelAndView testWeb(ModelAndView mov) throws Exception {
        List<TestDTO> categories = testService.findAll();
        mov.addObject("categories",categories);
        mov.setViewName("/index");
        return mov;
    }
    
}
