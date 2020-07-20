package config;

public enum DeckSize {

    REGULAR_DECK_SIZE(52),
    JOKER_DECK_SIZE(54),
    EXCEEDING_DECK_SIZE((int )(Math.random() * 100 + 55)),
    RANDOM_DECK_SIZE((int )(Math.random() * 52 + 1)),
    NEGATIVE_DECK_SIZE(-10),
    ZERO_DECK_SIZE(0),
    NULL_DECK_SIZE(null);

    private Integer size;

    DeckSize(Integer size){
        this.size = size;
    }

    public Integer getSize(){
        return size;
    }
}

