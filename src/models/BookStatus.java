package models;

public enum BookStatus {
    ODUNC("odunc"),
    RAFTA("rafta"),
    KAYIP("kayip");

    private String status;

    BookStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
