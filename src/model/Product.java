package model;

import java.util.Objects;

public class Product {

    private final int id;
    private final String name, weight_of_con, Color;
    private final boolean IsBox;

    public Product(int id, String name, String weight_of_con, String Color, boolean IsBox) {
        this.id = id;
        this.name = name;
        this.weight_of_con = weight_of_con;
        this.Color = Color;
        this.IsBox = IsBox;
    }

    public boolean isIsBox() {
        return IsBox;
    }

    public String getWeight_of_con() {
        return weight_of_con;
    }

    public String getColor() {
        return Color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name; // This allows easy use in combo boxes or lists
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.weight_of_con);
        hash = 89 * hash + Objects.hashCode(this.Color);
        hash = 89 * hash + (this.IsBox ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.IsBox != other.IsBox) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.weight_of_con, other.weight_of_con)) {
            return false;
        }
        return Objects.equals(this.Color, other.Color);
    }
    
}
