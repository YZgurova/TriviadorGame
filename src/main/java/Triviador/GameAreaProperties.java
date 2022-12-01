package Triviador;

import java.util.ArrayList;
import java.util.List;
public class GameAreaProperties {
    public static final List<Area> areaCollection=new ArrayList<>();
    private Area attackedArea;

    public Area getAttackedArea() {
        return attackedArea;
    }

    public void setAttackedArea(Area attackedArea) {
        this.attackedArea = attackedArea;
    }

}
