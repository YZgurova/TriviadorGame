package Triviador;

public class Area {
    public final int areaNum;
    private String fileName;
    private String buttonFileName;
    private boolean isBlue;

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

    public String getButtonFileName() {
        return buttonFileName;
    }

    public void setButtonFileName(String buttonFileName) {
        this.buttonFileName = buttonFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isBlue() {
        return isBlue;
    }

    public void setBlue(boolean blue) {
        isBlue = blue;
    }
}
