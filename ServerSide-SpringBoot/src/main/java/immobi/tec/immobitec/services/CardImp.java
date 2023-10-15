package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Card;
import immobi.tec.immobitec.repositories.CardRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CardImp implements  ICard{

    @Autowired
    CardRepo cardRepo;
    @Override
    public Card addCard(Card c) {
        return cardRepo.save(c);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepo.findAll();
    }

    @Override
    public Card getCardByid(int id_Card) {
        return cardRepo.findById(id_Card).orElse(null);
    }

    @Override
    public void deleteCard(int id_Card) {
        cardRepo.deleteById(id_Card);
    }

    @Override
    public Card updateCard(Card c) {
        return cardRepo.save(c);
    }
}
