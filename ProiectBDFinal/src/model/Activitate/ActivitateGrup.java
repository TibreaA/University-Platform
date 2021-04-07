package model.Activitate;

import java.sql.Date;

public class ActivitateGrup {
    private int _id;
    private String name;
    private int minPart;
    private Date termenLimita;
    private Date data;
    private String profesor;
    private int idGrup;

    public int get_id() {
        return _id;
    }

    public int getIdGrup() {
        return idGrup;
    }

    public void setIdGrup(int idGrup) {
        this.idGrup = idGrup;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinPart() {
        return minPart;
    }

    public void setMinPart(int minPart) {
        this.minPart = minPart;
    }

    public Date getTermenLimita() {
        return termenLimita;
    }

    public void setTermenLimita(Date termenLimita) {
        this.termenLimita = termenLimita;
    }

    @Override
    public String toString() {
        return "ActivitateGrup{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", minPart=" + minPart +
                ", termenLimita=" + termenLimita +
                ", data=" + data +
                ", profesor='" + profesor + '\'' +
                '}';
    }

    public String toString2() {
        return "ActivitateGrup{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", minPart=" + minPart +
                ", termenLimita=" + termenLimita +
                ", data=" + data +
                ", profesor='" + profesor + '\'' +
                '}';
    }
}
