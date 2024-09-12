package Model;

public class Data {
    private int id;
    private String fineName;
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFineName() {
        return fineName;
    }

    public void setFineName(String fineName) {
        this.fineName = fineName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Data(int id, String fineName, String path, String email) {
        this.id = id;
        this.fineName = fineName;
        this.path = path;
        this.email = email;
    }
    public Data(int id, String fineName, String path) {
        this.id = id;
        this.fineName = fineName;
        this.path = path;
    }
    public Data(String fineName, String path, String email) {
        this.fineName = fineName;
        this.path = path;
        this.email = email;
    }


    private String email;
}
