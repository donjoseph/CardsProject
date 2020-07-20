package models;

public class Image {

    private String svg = null;
    private String png = null;

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Constructors ////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public Image() {}

    public Image(String svg, String png){
        this.svg = svg;
        this.png = png;
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Getters and Setters//////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }
}
