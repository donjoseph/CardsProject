package models;

public class Card {

    private String image = null;
    private String value = null;
    private String suit = null;
    private String code = null;
    private Image images = null;

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Constructors ////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public Card(){}

    public Card(String image,
                String value,
                String suit,
                String code,
                Image images) {
        this.image = image;
        this.value = value;
        this.suit = suit;
        this.code = code;
        this.images = images;
    }


    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Getters and Setters//////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }
}
