package admin.example.demosql;

public class DeMo {
    private  int id;
    private String ten;

    public DeMo(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public DeMo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
