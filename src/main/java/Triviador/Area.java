package Triviador;

public class Area {
    public final int areaNum;
    public final String fileName;
    public final String buttonFileName;
    public final boolean isBlue;
    public final boolean isKingdom;

    public Area(int areaNum, boolean isBlue, boolean isKingdom) {
        this.isBlue = isBlue;
        this.isKingdom=isKingdom;
        this.areaNum=areaNum;
        if(isBlue) {
            fileName="area"+areaNum+"blue.png";
            buttonFileName="blueArcher.png";
        } else {
            fileName="area"+areaNum+"red.png";
            buttonFileName="redArcher.png";
        }
    }
}
