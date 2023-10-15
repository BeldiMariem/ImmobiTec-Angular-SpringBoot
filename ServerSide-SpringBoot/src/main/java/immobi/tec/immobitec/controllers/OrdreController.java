package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.Ordre;
import immobi.tec.immobitec.services.IOrdre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrdreController {
    @Autowired
    IOrdre iOrdre;

    @PostMapping("/addOrdre")
    @ResponseBody
    public Ordre addOrdre(@RequestBody Ordre o){
        return iOrdre.addOrdre(o);
    }


    @GetMapping("/getallOrdres")
    public List<Ordre> getAllOrdres(){
        return iOrdre.getAllOrdres();
    }

    @GetMapping("GetOrdreById/{id_ordre}")
    public Ordre getOrdreById (@PathVariable("id_ordre")int id_ordre){
        return iOrdre.getOrdreByid(id_ordre);
    }

    @DeleteMapping ("deletOrdre/{id_ordre}")
    public void deleteOrdre(@PathVariable("id_ordre")int id_ordre){
        iOrdre.deleteOrdre(id_ordre);
    }
    @PutMapping ("/updateOrdre")
    public Ordre updateOrdre(@RequestBody Ordre ordre)
    {
        iOrdre.updateOrdre(ordre);
        return  ordre;
    }
}
