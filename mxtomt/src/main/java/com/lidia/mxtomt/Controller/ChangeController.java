package com.lidia.mxtomt.Controller;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class ChangeController {
    
    @GetMapping("path")
    public String getMethodName(@RequestBody String message) {
        
        
        return "";
    }
    
}
