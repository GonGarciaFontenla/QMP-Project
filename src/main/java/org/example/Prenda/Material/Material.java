package org.example.Prenda.Material;

import static java.util.Objects.requireNonNull;

public class Material {
    private TypeMaterial typeMaterial;
    private Weft weft;
    private Color mainColor;
    private Color secondaryColor;

    public Material(TypeMaterial typeMaterial, Weft weft, Color mainColor, Color secondaryColor) {
        this.typeMaterial = requireNonNull(typeMaterial, "TypeMaterial cannot be null");
        this.weft = (weft != null)? weft : Weft.LISA;
        this.mainColor = requireNonNull(mainColor, "Main color cannot be null");
        this.secondaryColor = secondaryColor;
    }

    public TypeMaterial getTypeMaterial() {
        return typeMaterial;
    }

    public void setTypeMaterial(TypeMaterial typeMaterial) {
        this.typeMaterial = typeMaterial;
    }

    public Weft getWeft() {
        return weft;
    }

    public void setWeft(Weft weft) {
        this.weft = weft;
    }

    public Color getMainColor() {
        return mainColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setMainColor(Color color) {
        this.mainColor = color;
    }

    public void setSecondaryColor(Color color) {
        this.secondaryColor = color;
    }
}
