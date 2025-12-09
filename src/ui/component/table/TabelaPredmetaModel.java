/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.component.table;

import domain.Nastavnik;
import domain.OblikNastave;
import domain.Predmet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import logic.Controller;

/**
 *
 * @author Ognjen
 */
public class TabelaPredmetaModel extends AbstractTableModel{
    
    private final Nastavnik nastavnik;
    private List<Predmet> predmeti = new ArrayList<>();
    private final String[] kolone = {"Naziv Predmeta", "Šifra Predmeta", "ESPB", "Oblik Angažovanja"};

    public TabelaPredmetaModel(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    // Metoda za asinhrono učitavanje predmeta od strane kontrolera
    public void ucitajPredmete() throws Exception {
        // Koristimo kontroler da učita DISTINCT predmete za ulogovanog nastavnika
        predmeti = Controller.getInstance().ucitajAngazovanePredmete(nastavnik); 
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return predmeti.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    // Ključna metoda: formatira podatke za prikaz u ćeliji
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Predmet p = predmeti.get(rowIndex);
        
        switch (columnIndex) {
            case 0: // Naziv Predmeta
                return p.getNaziv();
            case 1: // Šifra Predmeta
                // Koristi ID kao šifru (pretpostavljamo da je ID jedinstven i da je to šifra)
                return p.getId(); 
            case 2: // ESPB
                return p.getEspb();
            case 3: // Oblik Angažovanja (kombinuje oblike nastave)
                try {
                    // Za svaki predmet, posebno povlačimo oblike nastave iz baze
                    // Tvoj DatabaseBroker.getObliciNastave je savršen za ovo
                    List<OblikNastave> oblici = Controller.getInstance().ucitajObliciNastave(nastavnik, p);
                    
                    // Spoji oblike nastave u jedan string (npr. "Predavanja, Vežbe, Lab vežbe")
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < oblici.size(); i++) {
                        sb.append(oblici.get(i).getNaziv());
                        if (i < oblici.size() - 1) {
                            sb.append(", ");
                        }
                    }
                    return sb.toString();
                    
                } catch (Exception ex) {
                    return "Greška pri učitavanju oblika";
                }
            default:
                return "N/A";
        }
    }
    
    // Sprečava uređivanje ćelija
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
}
