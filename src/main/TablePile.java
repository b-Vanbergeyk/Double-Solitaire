package main;

import java.util.ArrayList;

public class TablePile extends CardPile {

    TablePile(int numCards, DeckPile deck){
        ArrayList<Card> cards = deck.select(numCards);

        setID("T"+numCards);

        super.addCard(cards);

        if (!this.empty())
            this.top().flip();
    }

    @Override
    void addCard(ArrayList<Card> aCardList){
        for (Card card: aCardList){
            card.setX(this.getX());
            card.setY(this.getY() + (this.getNoCards()-1) * GameSinglePlayer.STACKVOFFSET);
            card.setHeld(false);
        }

        this.getCardList().addAll(aCardList);
    }

    @Override
    ArrayList<Card> select() {
        ArrayList<Card> tempList = new ArrayList<>();

        if (!this.empty()) {
            if (!this.top().getFaceUp()) {
                this.top().flip();
                return tempList;
            }
            else {
                for (int i = 0; i < this.getNoCards(); i++) {
                    if (this.getCardList().get(i).getFaceUp()) {
                        tempList.add(this.getCardList().get(i));
                    }
                }
            }
        }
        return tempList;
    }

    /*@Override
    public boolean contains(double mouseX, double mouseY){
        if (this.getNoCards() == 1){
            super.contains(mouseX, mouseY);
        } else if (!this.empty()){

            int counter1 = this.select().size();
            int counter;
            if (counter1 != 0)
                counter = this.getNoCards() - counter1;
            else
                counter = 0;

            return (mouseX > this.getCardList().get(counter).getX() && mouseX < this.getCardList().get(counter).getX()
                    + Card.WIDTH && mouseY > this.getCardList().get(counter).getY() && mouseY < this.getCardList()
                    .get(counter).getY() + GameSinglePlayer.STACKVOFFSET*counter);
        }
        return false;
    }*/

    /*boolean hoveringOverPile(double x, double y){
        if (!this.empty())
            return (x > this.getX() && x < this.getX() + Card.WIDTH && y > this.getY() && y < this.getY() + Card.HEIGHT
                    + (this.getNoCards() - 1)* GameSinglePlayer.STACKVOFFSET);

        return super.contains(x, y);
    }*/

    @Override
    public boolean canAccept(Card card) {
        assert card != null;

        if (this.empty())
            return card.isKing();

        if (!this.top().getFaceUp())
            return false;

        return (this.top().getColor() != card.getColor()) && (this.top().getNumber() == card.getNumber() + 1);
    }

    void topDown(){
        if (!this.top().getFaceUp()){
            this.top().flip();
        }
    }


}
