package com.ducvt.subleasing;

import com.ducvt.subleasing.fw.utils.ResponseFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class BaseController {
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseFactory.success("This is Otaniemi Subleasing project");
    }
}
