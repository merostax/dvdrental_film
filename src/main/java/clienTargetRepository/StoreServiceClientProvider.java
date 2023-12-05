package clienTargetRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

@ApplicationScoped
public class StoreServiceClientProvider {
    private Client client;
    private WebTarget storeServiceTarget;

    @Inject
    public StoreServiceClientProvider() {
        client = ClientBuilder.newClient();
        this.storeServiceTarget = initializeStoreServiceTarget();
    }

    private WebTarget initializeStoreServiceTarget() {
        String storeServiceUri = System.getProperty("store.service.uri");

        if (storeServiceUri == null || storeServiceUri.isEmpty()) {
            System.out.println("Warning: Store service URI not set. "
                    + "Set it using system property -Dstore.service.uri=http://your-store-service-uri");
        }

        return client.target(storeServiceUri);
    }

    public WebTarget getStoreServiceTarget() {
        return storeServiceTarget;
    }
}
