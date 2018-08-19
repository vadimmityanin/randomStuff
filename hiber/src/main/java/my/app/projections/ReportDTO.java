package my.app.projections;

public class ReportDTO {

    private long id;

    private String city;


    public ReportDTO() {
    }

    public ReportDTO(long id, String city) {
        this.id = id;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "id=" + id +
                ", city='" + city + '\'' +
                '}';
    }
}
