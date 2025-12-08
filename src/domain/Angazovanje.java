/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Ognjen
 */
public class Angazovanje {
    
    private Nastavnik nastavnik;
    private Predmet predmet;
    private OblikNastave oblikNastave;

    public Angazovanje() {
    }

    public Angazovanje(Nastavnik nastavnik, Predmet predmet, OblikNastave oblikNastave) {
        this.nastavnik = nastavnik;
        this.predmet = predmet;
        this.oblikNastave = oblikNastave;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public OblikNastave getOblikNastave() {
        return oblikNastave;
    }

    public void setOblikNastave(OblikNastave oblikNastave) {
        this.oblikNastave = oblikNastave;
    }
    
}
