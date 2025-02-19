import java.util.*;
class Card {
    private String symbol;
    private String value;
    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }
    public String getSymbol() {
        return symbol;
    }
    public String getValue() {
        return value;
    }
    public String toString() {
        return value + " of " + symbol;
    }
}
class CardCollection {
    private Collection<Card> cards;

    public CardCollection() {
        this.cards = new ArrayList<>();
    }
    public void addCard(String symbol, String value) {
        cards.add(new Card(symbol, value));
    }
    public List<Card> getCardsBySymbol(String symbol) {
        List<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                result.add(card);
            }
        }
        return result;
    }
}
public class CardCollector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection cardCollection = new CardCollection();
        cardCollection.addCard("Hearts", "Ace");
        cardCollection.addCard("Hearts", "King");
        cardCollection.addCard("Spades", "Queen");
        cardCollection.addCard("Diamonds", "Jack");
        cardCollection.addCard("Hearts", "10");
        System.out.print("Enter the symbol to find cards (Hearts, Spades, Diamonds, Clubs): ");
        String symbol = scanner.nextLine();
        List<Card> foundCards = cardCollection.getCardsBySymbol(symbol);
        if (foundCards.isEmpty()) {
            System.out.println("No cards found for the symbol " + symbol);
        } else {
            System.out.println("Cards found: " + foundCards);
        }
        scanner.close();
    }
}
