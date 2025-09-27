package model;

import java.util.Date;
import java.util.Objects;

public class Bag {

    private int id, pro_id, num_of_con, pallet_numb;
    private double tot_wight, weight, empty_pack;
    private String lot;
    private boolean used;
    private Date date;

    public Bag(int id, int pro_id, double tot_wight, double weight, String lot, int num_of_con, int pallet_numb, boolean used, Date date, double empty_pack) {
        this.id = id;
        this.pro_id = pro_id;
        this.num_of_con = num_of_con;
        this.pallet_numb = pallet_numb;
        this.tot_wight = tot_wight;
        this.weight = weight;
        this.lot = lot;
        this.used = used;
        this.date = date;
        this.empty_pack = empty_pack;
    }

    public int getId() {
        return id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public int getNum_of_con() {
        return num_of_con;
    }

    public int getPallet_numb() {
        return pallet_numb;
    }

    public double getTot_wight() {
        return tot_wight;
    }

    public double getWeight() {
        return weight;
    }

    public String getLot() {
        return lot;
    }

    public boolean isUsed() {
        return used;
    }

    public Date getDate() {
        return date;
    }

    public double getEmpty_pack() {
        return empty_pack;
    }

    public void setEmpty_pack(double empty_pack) {
        this.empty_pack = empty_pack;
    }

    @Override
    public String toString() {
        return "Bag{" + "id=" + id + ", pro_id=" + pro_id + ", num_of_con=" + num_of_con + ", pallet_numb=" + pallet_numb + ", tot_wight=" + tot_wight + ", weight=" + weight + ", empty_pack=" + empty_pack + ", lot=" + lot + ", used=" + used + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        hash = 29 * hash + Objects.hashCode(this.lot);
        hash = 29 * hash + Objects.hashCode(this.date);
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
        final Bag other = (Bag) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.pro_id != other.pro_id) {
            return false;
        }
        if (this.num_of_con != other.num_of_con) {
            return false;
        }
        if (this.pallet_numb != other.pallet_numb) {
            return false;
        }
        if (Double.doubleToLongBits(this.tot_wight) != Double.doubleToLongBits(other.tot_wight)) {
            return false;
        }
        if (Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(other.weight)) {
            return false;
        }
        if (Double.doubleToLongBits(this.empty_pack) != Double.doubleToLongBits(other.empty_pack)) {
            return false;
        }
        if (this.used != other.used) {
            return false;
        }
        if (!Objects.equals(this.lot, other.lot)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

}
