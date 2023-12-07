package util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;

import jakarta.annotation.PostConstruct;

@ApplicationScoped
public class Hrefs {

    private String customerHref;
    private String storeHref;
    private String filmHref;

    @PostConstruct
    public void initialize() {
        this.customerHref = getPropertyWithWarning("customer.service.uri", "Customer");
        this.storeHref = getPropertyWithWarning("store.service.uri", "Store");
        this.filmHref = getPropertyWithWarning("film.service.uri", "Film");
    }

    private String getPropertyWithWarning(String propertyName, String serviceName) {
        String propertyValue = System.getProperty(propertyName);

        if (propertyValue == null || propertyValue.isEmpty()) {
            System.out.println("Warning: " + serviceName + " service URI not set. "
                    + "Set it using system property -D" + propertyName + "=http://your-service-uri");
        }

        return propertyValue;
    }

    public String getCustomerHref() {
        return customerHref;
    }

    public String getStoreHref() {
        return storeHref;
    }

    public String getFilmHref() {
        return filmHref;
    }
}
