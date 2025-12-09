/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import domain.Nastavnik;
import domain.OblikNastave;
import domain.Predmet;
import java.sql.SQLException;
import java.util.List;
import repository.db.DatabaseBroker;

/**
 *
 * @author Ognjen
 */
public class Controller {
    private static Controller instance;
    private DatabaseBroker dbbr;

    public Controller() {
        dbbr = new DatabaseBroker();
    }
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    
    public Nastavnik prijavaNastavnika(String email, String sifra) throws Exception{
        dbbr.connect();
        try{
            return dbbr.login(email, sifra);
        } finally{
            dbbr.disconnect();
        }
    }
    
    public List<Predmet> ucitajAngazovanePredmete(Nastavnik nastavnik) throws Exception{
        dbbr.connect();
        try{
            return dbbr.getPredmetiZaNastavnika(nastavnik);
        }finally{
            dbbr.disconnect();
        }
    }
    
    public List<OblikNastave> ucitajObliciNastave(Nastavnik nastavnik, Predmet predmet) throws Exception{
        dbbr.connect();
        try{
            return dbbr.getObliciNastave(nastavnik, predmet);
        }finally{
            dbbr.disconnect();
        }
    }
    
    public List<Predmet> ucitajSvePredmete() throws Exception{
        dbbr.connect();
        try {
            return dbbr.getAllPredmeti();
        } finally{
            dbbr.disconnect();
        }
    }
    
    public List<OblikNastave> ucitajSveObliciNastave() throws Exception{
        dbbr.connect();
        try{
            return dbbr.getAllObliciNastave();
        }finally{
            dbbr.disconnect();
        }
    }
    
    public void sacuvajAngazovanje(Nastavnik n, Predmet p, OblikNastave on) throws Exception{
        dbbr.connect();
        try{
            dbbr.insertAngazovanje(n, p, on);
        }finally{
            dbbr.disconnect();
        }
    }
    
    
    
}
