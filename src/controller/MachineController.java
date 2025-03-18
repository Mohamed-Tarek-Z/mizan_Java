package controller;

import exceptions.DatabaseException;
import java.util.List;
import model.Machine;
import dao.MachineDAO;
import dao.ProductDAO;
import exceptions.BusinessException;
import java.util.Date;
import model.Product;

public class MachineController {

    private final MachineDAO machineDAO;
    private final ProductDAO productDAO;

    public MachineController(MachineDAO machineDAO, ProductDAO productDAO) {
        this.machineDAO = machineDAO;
        this.productDAO = productDAO;
    }

    public List<Machine> getMachines() throws DatabaseException {
        return machineDAO.getMachines();
    }

    public void editMachine(Machine machine) throws DatabaseException {
        machineDAO.editMachine(machine);
    }

    public void addMachine(String machineName, String productName, String lot) throws DatabaseException, BusinessException {
        Product product = productDAO.getProductByName(productName);
        machineDAO.addMachine(new Machine(0, machineName, product.getId(), lot, new Date()));
    }
}
