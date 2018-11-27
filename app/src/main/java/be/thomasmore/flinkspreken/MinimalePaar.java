package be.thomasmore.flinkspreken;

public class MinimalePaar {
    private long id;
    private String woord1;
    private String woord2;
    private String doelKlank;
    private String finaalInitiaal;
    private String fonologischProces;

    public MinimalePaar(){}

    public MinimalePaar(long id, String woord1, String woord2, String doelKlank, String finaalInitiaal, String fonologischProces) {
        this.id = id;
        this.woord1 = woord1;
        this.woord2 = woord2;
        this.doelKlank = doelKlank;
        this.finaalInitiaal = finaalInitiaal;
        this.fonologischProces = fonologischProces;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWoord1() {
        return woord1;
    }

    public void setWoord1(String woord1) {
        this.woord1 = woord1;
    }

    public String getWoord2() {
        return woord2;
    }

    public void setWoord2(String woord2) {
        this.woord2 = woord2;
    }

    public String getDoelKlank() {
        return doelKlank;
    }

    public void setDoelKlank(String doelKlank) {
        this.doelKlank = doelKlank;
    }

    public String getFinaalInitiaal() {
        return finaalInitiaal;
    }

    public void setFinaalInitiaal(String finaalInitiaal) {
        this.finaalInitiaal = finaalInitiaal;
    }

    public String getFonologischProces() {
        return fonologischProces;
    }

    public void setFonologischProces(String fonologischProces) {
        this.fonologischProces = fonologischProces;
    }

    @Override
    public String toString() {
        return "database werkt" + woord1 + " " + woord2;
    }
}
