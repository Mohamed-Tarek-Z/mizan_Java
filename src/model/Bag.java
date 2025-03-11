package model;

import java.util.Date;

public class Bag {

    private int id, pro_id, num_of_con, pallet_numb;
    private double tot_wight, weight;
    private String lot;
    private boolean used;
    private Date date;

    public Bag(int id, int pro_id, double tot_wight, double weight, String lot, int num_of_con, int pallet_numb, boolean used, Date date) {
        this.id = id;
        this.pro_id = pro_id;
        this.num_of_con = num_of_con;
        this.pallet_numb = pallet_numb;
        this.tot_wight = tot_wight;
        this.weight = weight;
        this.lot = lot;
        this.used = used;
        this.date = date;
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

    @Override
    public String toString() {
        return "Bag{" + "id=" + id + ", pro_id=" + pro_id + ", num_of_con=" + num_of_con + ", pallet_numb=" + pallet_numb + ", tot_wight=" + tot_wight + ", weight=" + weight + ", lot=" + lot + ", used=" + used + ", date=" + date + '}';
    }

}
