package com.project12306.general;

import com.project12306.model.TestModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public TestModel test() {
        TestModel model = new TestModel();
        model.setX(1);
        model.setY(1);
        return model;
    }
}
