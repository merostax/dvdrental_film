package clienTargetRepository;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

@RequestScoped
public class StoreServiceClientProvider {

    private static final String STORE_SERVICE_URI_PROPERTY = "store.service.uri";

    private Client client;
    private WebTarget storeServiceTarget;
      public StoreServiceClientProvider() {
        client = ClientBuilder.newClient();
        this.storeServiceTarget = initializeStoreServiceTarget();
        System.out.println("Store service URI: " + getStoreServiceUri());
    }

    private WebTarget initializeStoreServiceTarget() {
        String storeServiceUri = getStoreServiceUri();

        if (storeServiceUri == null || storeServiceUri.isEmpty()) {
            System.out.println("Warning: Store service URI not set. "
                    + "Set it using system property -Dstore.service.uri=http://your-store-service-uri");
        }

        return client.target(storeServiceUri);
    }

    public WebTarget getStoreServiceTarget() {
        return storeServiceTarget;
    }

    private String getStoreServiceUri() {
        return System.getProperty(STORE_SERVICE_URI_PROPERTY);
    }
}
