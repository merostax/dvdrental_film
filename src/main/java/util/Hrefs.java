package util;

public enum Hrefs {
    CUSTOMER("http://localhost:8083/"),
    STORE("http://localhost:8082/"),
    FILM("http://localhost:8081/");

    private final String href;

    Hrefs(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }
}
