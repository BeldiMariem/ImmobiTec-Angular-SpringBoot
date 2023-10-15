package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Card;

import java.util.List;

public interface ICard {
    public Card addCard(Card c);
    public List<Card> getAllCards();
    public Card getCardByid(int id_Card);
    public void deleteCard(int id_Card);

    public Card updateCard(Card c);
}
