package model;

import java.util.Date;

public class Export {

    private int id, pro_id, cli_id, num_of_con, pallet_numb, ord_id;
    private double tot_wight, weight;
    private String lot;
    private boolean used;
    private Date inserted_date, exported_date;

    public Export(int id, int pro_id, int cli_id, int ord_id, int num_of_con, int pallet_numb, double tot_wight, double weight, String lot, boolean used, Date inserted_date, Date exported_date) {
        this.id = id;
        this.pro_id = pro_id;
        this.cli_id = cli_id;
        this.ord_id = ord_id;
        this.num_of_con = num_of_con;
        this.pallet_numb = pallet_numb;
        this.tot_wight = tot_wight;
        this.weight = weight;
        this.lot = lot;
        this.used = used;
        this.inserted_date = inserted_date;
        this.exported_date = exported_date;
    }

    public int getOrd_id() {
        return ord_id;
    }

    public int getId() {
        return id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public int getCli_id() {
        return cli_id;
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

    public Date getInserted_date() {
        return inserted_date;
    }

    public Date getExported_date() {
        return exported_date;
    }

}
