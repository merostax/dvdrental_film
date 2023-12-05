package util;

public enum Hrefs {
    CUSTOMER(getPropertyWithWarning("customer.service.uri", "Customer")),
    STORE(getPropertyWithWarning("store.service.uri", "Store")),
    FILM(getPropertyWithWarning("film.service.uri", "Film"));

    private final String href;

    Hrefs(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    private static String getPropertyWithWarning(String propertyName, String serviceName) {
        String propertyValue = System.getProperty(propertyName);

        if (propertyValue == null || propertyValue.isEmpty()) {
            System.out.println("Warning: " + serviceName + " service URI not set. "
                    + "Set it using system property -D" + propertyName + "=http://your-service-uri");
        }

        return propertyValue;
    }
}
