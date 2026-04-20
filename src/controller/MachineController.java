package controller;

import exceptions.DatabaseException;
import java.util.List;
import model.Machine;
import repository.MachineRepository;
import repository.ProductRepository;
import exceptions.BusinessException;
import java.util.Date;
import model.Product;

public class MachineController {

    private final MachineRepository machineRepo;
    private final ProductRepository productRepo;

    public MachineController(MachineRepository machineRepo, ProductRepository productRepo) {
        this.machineRepo = machineRepo;
        this.productRepo = productRepo;
    }

    public List<Machine> getMachines() throws DatabaseException {
        return machineRepo.getMachines();
    }

    public void editMachine(Machine machine) throws DatabaseException {
        machineRepo.editMachine(machine);
    }

    public void addMachine(String machineName, String productName, String lot) throws DatabaseException, BusinessException {
        Product product = productRepo.getProductByName(productName);
        machineRepo.addMachine(new Machine(0, machineName, product.getId(), lot, new Date()));
    }

    public boolean removeMachine(int machId) throws DatabaseException {
        return machineRepo.deleteMachine(machId);
    }
}
