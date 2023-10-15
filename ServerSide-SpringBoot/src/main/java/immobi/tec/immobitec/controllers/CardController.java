package immobi.tec.immobitec.controllers;


import immobi.tec.immobitec.entities.Card;
import immobi.tec.immobitec.services.ICard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {
    @Autowired
    ICard iCard;
    @PostMapping("/addCard")
    @ResponseBody
    public Card addCard(@RequestBody Card c){
        return iCard.addCard(c);
    }


    @GetMapping("/getallCards")
    public List<Card> getAllCards(){
        return iCard.getAllCards();
    }

    @GetMapping("GetCardById/{id_Card}")
    public Card getCardById (@PathVariable("id_Card")int id_Card){
        return iCard.getCardByid(id_Card);
    }

    @DeleteMapping ("deletCard/{id_Card}")
    public void deleteCard(@PathVariable("id_Card")int id_Card){
        iCard.deleteCard(id_Card);
    }
    @PutMapping ("/updateCard")
    public Card updateCard(@RequestBody Card Card)
    {
        iCard.updateCard(Card);
        return  Card;
    }
}
