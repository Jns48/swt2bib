package de.swt2bib.ui.panels;

import de.swt2bib.fachlogik.genreverwaltung.Genre;
import de.swt2bib.fachlogik.kategorieverwaltung.Kategorie;
import de.swt2bib.fachlogik.medienverwaltung.Medien;
import de.swt2bib.ui.ElternPanel;
import de.swt2bib.ui.PanelHandler;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class SuchePanel extends ElternPanel {

    ArrayList<Medien> medienListe;

    /**
     * Creates new form SuchePanel
     */
    public SuchePanel(PanelHandler panelHandler) {
        super(panelHandler);
        initComponents();
        setComboboxKategorie(kategorieComboBox, panelHandler.getKategorieListe());
        setComboboxGenre(genreComboBox, panelHandler.getGenreListe());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed
        try {
            panelHandler.getSelectPanel().setMedium(getMediumfromIndices(getListSelections()));
            if (panelHandler.getAktuellerUser().isMitarbeiter()) {
                panelHandler.getSelectPanel().setMitarbeiter();
            }
            panelHandler.panelUnsichtbar();
            panelHandler.getUi().add(panelHandler.getSelectPanel());
            panelHandler.getSelectPanel().setVisible(true);
        } catch (Exception e) {
            System.err.println("problem beim aufrufen vom selectpanel");
        }

    }//GEN-LAST:event_selectButtonActionPerformed

    private void sucheFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sucheFieldActionPerformed
        setSearchTitel(sucheField.getText());
    }//GEN-LAST:event_sucheFieldActionPerformed

    private void kategorieComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategorieComboBoxActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String text = kategorieComboBox.getItemAt(0);
        for (int i = model.getRowCount() - 1; i > -1; i--) {
            model.removeRow(i);
        }

        int kategorieid = -1;
        for (int i = 0; i < panelHandler.getKategorieListe().size(); i++) {
            if (panelHandler.getKategorieListe().get(i).getBezeichnung().equals(text)) {
                kategorieid = (int) panelHandler.getKategorieListe().get(i).getId();
            }
        }

        for (int i = 0; i < medienListe.size(); i++) {
            if (medienListe.get(i).getKategorien().getId() == kategorieid) {
                model.addRow(addObject(i));
            }
        }
    }//GEN-LAST:event_kategorieComboBoxActionPerformed

    private void genreComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreComboBoxActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String text = genreComboBox.getItemAt(0);
        for (int i = model.getRowCount() - 1; i > -1; i--) {
            model.removeRow(i);
        }

        int genreid = -1;
        for (int i = 0; i < panelHandler.getKategorieListe().size(); i++) {
            if (panelHandler.getGenreListe().get(i).getBezeichnung().equals(text)) {
                genreid = (int) panelHandler.getGenreListe().get(i).getId();
            }
        }

        for (int i = 0; i < medienListe.size(); i++) {
            if (medienListe.get(i).getGenre().getId() == genreid) {
                model.addRow(addObject(i));
            }
        }
    }//GEN-LAST:event_genreComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> genreComboBox;
    private javax.swing.JLabel genreLable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> kategorieComboBox;
    private javax.swing.JLabel kategorieLable;
    private javax.swing.JButton selectButton;
    private javax.swing.JTextField sucheField;
    // End of variables declaration//GEN-END:variables

    void setSearchTitel(String text) {
        medienListe = panelHandler.returnMedien();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        for (int i = model.getRowCount() - 1; i > -1; i--) {
            model.removeRow(i);
        }

        if (text.equals("") || text.equals("Suche") || text.equals("Titelsuchen...") || text.equals("Titelsuche...")) {
            for (int i = 0; i < medienListe.size(); i++) {
                model.addRow(addObject(i));
            }
            return;
        }

        for (int i = 0; i < medienListe.size(); i++) {
            if (medienListe.get(i).getName().equals(text)) {
                model.addRow(addObject(i));
            }
        }
    }

    public void setMedienListe(ArrayList<Medien> medien) {
        medienListe = medien;
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < medienListe.size(); i++) {
            model.addRow(addObject(i));
        }
    }

    private Medien getMediumfromIndices(int position) {
        Medien selected = null;
        selected = medienListe.get(position);
        return selected;
    }

    private int getListSelections() {
        int[] selected = jTable1.getSelectedRows();
        for (int i = 0; i < selected.length; i++) {
            selected[i] = jTable1.convertRowIndexToModel(selected[i]);
        }
        return selected[0];
    }

    private void setComboboxKategorie(JComboBox combobox, List<Kategorie> list) {
        String[] tmp = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tmp[i] = list.get(i).getBezeichnung();
        }
        combobox = new JComboBox(tmp);
        //combobox.setModel(new DefaultComboBoxModel(tmp));
    }

    private void setComboboxGenre(JComboBox combobox, List<Genre> list) {
        String[] tmp = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tmp[i] = list.get(i).getBezeichnung();
        }
        combobox = new JComboBox(tmp);
        //combobox.setModel(new DefaultComboBoxModel(tmp));
    }

    private Object[] addObject(int i) {
        String kategorie = medienListe.get(i).getKategorien().getBezeichnung();
        String genre = medienListe.get(i).getGenre().getBezeichnung();
        return new Object[]{medienListe.get(i).getName(), kategorie, genre, medienListe.get(i).getIsbn(), medienListe.get(i).getVerfuegbare()};
    }
}
