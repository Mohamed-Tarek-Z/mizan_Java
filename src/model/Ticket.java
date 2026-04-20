package model;

public class Ticket {

    String productName;
    String palletNumber;
    String Color;
    String lot;
    String coneNumber;
    String TotalWeight;
    String NetWeight;

    public Ticket(String productName, String palletNumber, String Color, String lot, String coneNumber, String TotalWeight, String NetWeight) {
        this.productName = productName;
        this.palletNumber = palletNumber;
        this.Color = Color;
        this.lot = lot;
        this.coneNumber = coneNumber;
        this.TotalWeight = TotalWeight;
        this.NetWeight = NetWeight;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPalletNumber() {
        return palletNumber;
    }

    public void setPalletNumber(String palletNumber) {
        this.palletNumber = palletNumber;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getConeNumber() {
        return coneNumber;
    }

    public void setConeNumber(String coneNumber) {
        this.coneNumber = coneNumber;
    }

    public String getTotalWeight() {
        return TotalWeight;
    }

    public void setTotalWeight(String TotalWeight) {
        this.TotalWeight = TotalWeight;
    }

    public String getNetWeight() {
        return NetWeight;
    }

    public void setNetWeight(String NetWeight) {
        this.NetWeight = NetWeight;
    }

}
