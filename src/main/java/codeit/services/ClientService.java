package codeit.services;

import codeit.dao.ClientDao;
import codeit.dao.DaoFactory;
import codeit.models.entities.Client;

import java.util.List;

public class ClientService {

    private static final ClientService INSTANCE = new ClientService(DaoFactory.getDaoFactory());
    private final DaoFactory daoFactory;
    ClientService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    public static ClientService getInstance() {
        return ClientService.INSTANCE;
    }

    public List<Client> getAllClients() {
        try (ClientDao dao = daoFactory.createClientDao()) {
            return dao.getAll();
        }
    }

    public Client getClientById(String clientId) {
        try (ClientDao dao = daoFactory.createClientDao()) {
            return dao.getById(clientId);
        }
    }

    public void createClient(Client client) {
        try (ClientDao dao = daoFactory.createClientDao()) {
            dao.create(client);
        }
    }

    public void updateClient(Client client) {
        try (ClientDao dao = daoFactory.createClientDao()) {
            dao.update(client);
        }
    }

    public void deleteClient(String clientId) {
        try (ClientDao dao = daoFactory.createClientDao()) {
            dao.delete(clientId);
        }
    }
}
