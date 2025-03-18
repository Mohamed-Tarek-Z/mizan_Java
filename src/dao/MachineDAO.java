package dao;

import model.sqlcon;

import exceptions.DatabaseException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Machine;

public class MachineDAO {

    private final sqlcon dbConnection;

    public MachineDAO(sqlcon dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Machine> getMachines() throws DatabaseException {
        try {
            List<Machine> machines = new ArrayList<>();

            ResultSet rs = dbConnection.dataRead("MachID, MachName, ProductId, Lot, UpdatedAt", "machine");
            while (rs.next()) {
                machines.add(new Machine(rs.getInt("MachID"), rs.getString("MachName"), rs.getInt("ProductId"), rs.getString("Lot"), rs.getDate("UpdatedAt")));
            }

            return machines;
        } catch (SQLException ex) {
            Logger.getLogger(MachineDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء طلب البيانات", ex);
        }
    }

    public void editMachine(Machine machine) throws DatabaseException {
        try {
            dbConnection.update("machine",
                    "MachName=N'" + machine.getMachName() + "' ,ProductId=" + machine.getProId()
                    + " ,Lot=N'" + machine.getLot() + "'",
                    "MachID=" + machine.getMachId());
        } catch (SQLException ex) {
            Logger.getLogger(MachineDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء تعديل بيانات الصنف: " + machine.getMachName(), ex);
        }
    }

    public void addMachine(Machine machine) throws DatabaseException {
        try {
            dbConnection.inData("machine", "MachName, ProductId, Lot",
                    "N'" + machine.getMachName() + "',"
                    + machine.getProId() + ",N'" + machine.getLot() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(MachineDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء إضافة البيانات", ex);
        }
    }

}
