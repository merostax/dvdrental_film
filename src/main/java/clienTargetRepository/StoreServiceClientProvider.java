package clienTargetRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

@ApplicationScoped
public class StoreServiceClientProvider {
    private Client client;
    private WebTarget storeServiceTarget;
    public StoreServiceClientProvider() {
        client = ClientBuilder.newClient();
        this.storeServiceTarget = client.target("http://localhost:8082/");
    }

    public WebTarget getStoreServiceTarget() {
        return storeServiceTarget;
    }
}
