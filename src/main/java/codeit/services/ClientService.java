package codeit.services;

import codeit.dao.ClientDao;
import codeit.dao.DaoFactory;
import codeit.dao.EmployeeDao;
import codeit.dto.CredentialsDto;
import codeit.models.entities.Client;
import codeit.models.entities.Employee;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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

            byte[] salt = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int iterations = 10000;
            int keyLength = 256;

            String hashedPassword = hashPassword(client.getPassword().toCharArray(), salt, iterations, keyLength);
            client.setPassword(hashedPassword);

            dao.create(client);
        }
    }

    public void updateClient(Client client) {
        try (ClientDao dao = daoFactory.createClientDao()) {

            byte[] salt = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int iterations = 10000;
            int keyLength = 256;

            String hashedPassword = hashPassword(client.getPassword().toCharArray(), salt, iterations, keyLength);
            client.setPassword(hashedPassword);

            dao.update(client);
        }
    }

    public void deleteClient(String clientId) {
        try (ClientDao dao = daoFactory.createClientDao()) {
            dao.delete(clientId);
        }
    }

    public Client getClientByCredentials(CredentialsDto credentials) {
        try (ClientDao clientDao = daoFactory.createClientDao()) {

            byte[] salt = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int iterations = 10000;
            int keyLength = 256;

            String hashedPassword = hashPassword(credentials.getPassword().toCharArray(), salt, iterations, keyLength);

            return clientDao.getByCredentials(credentials.getEmail(), hashedPassword);
        }
    }

    public Client getClientByEmail(String email) {
        try (ClientDao clientDao = daoFactory.createClientDao()) {
            return clientDao.getByEmail(email);
        }
    }

    private static String hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while hashing a password", e);
        }
    }
}
