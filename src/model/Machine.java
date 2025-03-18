package model;

import java.util.Date;
import java.util.Objects;

public class Machine {
    private int machId;
    private String machName;
    private int proId;
    private String lot;
    private Date updatedAt;

    public Machine(int machId, String machName, int proId, String lot, Date updatedAt) {
        this.machId = machId;
        this.machName = machName;
        this.proId = proId;
        this.lot = lot;
        this.updatedAt = updatedAt;
    }

    public int getMachId() {
        return machId;
    }

    public String getMachName() {
        return machName;
    }

    public int getProId() {
        return proId;
    }

    public String getLot() {
        return lot;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.machName);
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
        final Machine other = (Machine) obj;
        return Objects.equals(this.machName, other.machName);
    }
    
    
}
