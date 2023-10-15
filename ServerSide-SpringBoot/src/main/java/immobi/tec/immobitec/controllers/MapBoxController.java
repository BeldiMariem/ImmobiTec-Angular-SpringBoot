package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.services.IServiceMapbox;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/map")

public class MapBoxController {
    private IServiceMapbox iServiceMapBox;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/MapBox/{x1}/{y1}/{x2}/{y2}")
    public Map<String , String> getInfo(@PathVariable String x1 ,@PathVariable String y1 ,@PathVariable String x2 , @PathVariable String y2) throws IOException{
        return iServiceMapBox.getInfo(x1,y1,x2,y2);
    }

}
