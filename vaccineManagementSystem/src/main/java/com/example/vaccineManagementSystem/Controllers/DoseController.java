package com.example.vaccineManagementSystem.Controllers;

import com.example.vaccineManagementSystem.Service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {
    @Autowired
    DoseService doseService;

    @PostMapping("/giveDose")
    public  String getDose1(@RequestParam String doseId, @RequestParam Integer userId){
        return doseService.giveDose(doseId, userId);
    }


}
