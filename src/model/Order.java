package model;

import java.util.Date;

public class Order {

    private int id;
    private double tot_wight;
    private Date date;

    public Order(int id, double tot_wight, Date date) {
        this.id = id;
        this.tot_wight = tot_wight;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public double getTot_wight() {
        return Math.round(tot_wight);
    }

    public Date getDate() {
        return date;
    }
    
    
    
    
}
