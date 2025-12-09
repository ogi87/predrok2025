/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import domain.Nastavnik;
import domain.OblikNastave;
import domain.Predmet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ognjen
 */
public class DatabaseBroker {
    
    private Connection connection;
    
    public void connect() throws SQLException{  
        try {
            String url = "jdbc:mysql://localhost:3306/predmeti_nastavnici";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Konekcija sa bazom je uspesno uspostavljena");
            
        } catch (SQLException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno uspostavljena");
            ex.printStackTrace();
        }
    }
    
    public void disconnect() throws SQLException{
        try {
            if(connection != null && !connection.isClosed()){
                connection.close();
                System.out.println("Konekcija sa bazom je uspesno raskinuta");
            }
        } catch (SQLException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno raskinuta!");
            ex.printStackTrace();
        }
    }
    
    public Nastavnik login(String email, String sifra) throws Exception{
        String query = "SELECT id, ime, prezime, email FROM nastavnik WHERE email = ? AND sifra = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2,sifra);
        
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
           Nastavnik n = new Nastavnik();
           n.setId(rs.getLong("id"));
           n.setIme(rs.getString("ime"));
           n.setPrezime(rs.getString("prezime"));
           n.setEmail(rs.getString("email"));
           // sifru ne vracamo
           return n;
        }else{
            throw new Exception("Pogresan email ili sifra");
        }
    }
    
    public List<Predmet> getPredmetiZaNastavnika(Nastavnik nastavnik) throws SQLException{
        List<Predmet> predmeti = new ArrayList<>();
        
        String query = "SELECT DISTINCT p.id, p.naziv, p.espb FROM predmet p JOIN angazovanje a ON p.id = a.predmetId WHERE a.nastavnikId = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, nastavnik.getId());
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Predmet p = new Predmet();
            p.setId(rs.getLong("id"));
            p.setNaziv(rs.getString("naziv"));
            p.setEspb(rs.getInt("espb"));
            predmeti.add(p);
        }
        return predmeti;
    }
    
    public List<OblikNastave> getObliciNastave(Nastavnik nastavnik, Predmet predmet) throws SQLException{
        List<OblikNastave> oblici = new ArrayList<>();
        String query = "SELECT o.id, o.naziv FROM oblik_nastave o "
                + "JOIN angazovanje a ON o.id = a.oblikNastaveId WHERE "
                + "a.nastavnikId = ? AND a.predmetId = ?";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, nastavnik.getId());
        ps.setLong(2, predmet.getId());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            OblikNastave on = new OblikNastave();
            on.setId(rs.getLong("id"));
            on.setNaziv(rs.getString("naziv"));
            oblici.add(on);
        }
        return oblici;
    }
    
    public List<Predmet> getAllPredmeti() throws SQLException{
        List<Predmet> predmeti = new ArrayList<>();
        String query = "SELECT id, naziv, espb FROM predmet";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        
        while(rs.next()){
            Predmet p = new Predmet();
            p.setId(rs.getLong("id"));
            p.setNaziv(rs.getString("naziv"));
            p.setEspb(rs.getInt("espb"));
            predmeti.add(p);
        }
        return predmeti;
    }
    
    public List<OblikNastave> getAllObliciNastave() throws SQLException{
        List<OblikNastave> oblici = new ArrayList<>();
        String query = "SELECT id, naziv FROM oblik_nastave";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        
        while(rs.next()){
            OblikNastave on = new OblikNastave();
            on.setId(rs.getLong("id"));
            on.setNaziv(rs.getString("naziv"));
            oblici.add(on);
        }
        return oblici;
    }
    
    public void insertAngazovanje(Nastavnik n, Predmet p, OblikNastave on) throws SQLException{
        String query = "INSERT INTO angazovanje(nastavnikId, predmetId, oblikNastaveId) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, n.getId());
        ps.setLong(2, p.getId());
        ps.setLong(3, on.getId());
        
        ps.executeUpdate();
        
    }
    
    
}
