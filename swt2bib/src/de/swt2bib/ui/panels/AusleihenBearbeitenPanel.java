package de.swt2bib.ui.panels;

import de.swt2bib.Logger;
import de.swt2bib.datenlogik.dto.Ausleihe;
import de.swt2bib.datenlogik.dto.Kategorie;
import de.swt2bib.datenlogik.dto.Medien;
import de.swt2bib.fachlogik.languageverwaltung.PropertyName;
import de.swt2bib.ui.ElternPanel;
import de.swt2bib.ui.PanelHandler;
import java.util.List;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class AusleihenBearbeitenPanel extends ElternPanel {

    List<Ausleihe> ausleiheListe;

    /**
     * Creates new form AusleihenBearbeitenPanel
     * @param panelHandler Angabe Panelhandler
     */
    public AusleihenBearbeitenPanel(PanelHandler panelHandler) {
        super(panelHandler);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        entfernenButton = new javax.swing.JButton();
        sucheField = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AusleiheID", "MedienID", "Date", "UserID", "KategorieIDl"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        entfernenButton.setText("Entfernen");
        entfernenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entfernenButtonActionPerformed(evt);
            }
        });

        sucheField.setText("Titelsuchen...");
        sucheField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sucheFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(sucheField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(entfernenButton)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sucheField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(entfernenButton)
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Entfernt eine Ausleihe.
     *
     * @param evt
     */
    private void entfernenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entfernenButtonActionPerformed
        try {
            panelHandler.deleteAusleihe(getAusleihefromIndices(getListSelections()));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_entfernenButtonActionPerformed

    /**
     * F??hrt eine Suche aus.
     *
     * @param evt
     */
    private void sucheFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sucheFieldActionPerformed
        panelHandler.panelUnsichtbar();
        panelHandler.getSuchePanel().setSearchTitel(sucheField.getText());
        panelHandler.getSuchePanel().setVisible(true);
    }//GEN-LAST:event_sucheFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton entfernenButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField sucheField;
    // End of variables declaration//GEN-END:variables

    public void setAusleihenListe(List<Ausleihe> ausleihe) {
        ausleiheListe = ausleihe;
    }

    /**
     * Fragt die Ausleihe an einer bestimmten Position ab.
     *
     * @param position Index
     * @return Ausgew??hlte Element
     */
    private Ausleihe getAusleihefromIndices(int position) {
        Ausleihe selected = null;
        selected = ausleiheListe.get(position);
        return selected;
    }

    /**
     * Pr??ft welches Element in der Liste gew??hlt wurde.
     *
     * @return Index
     */
    private int getListSelections() {
        int[] selected = jTable1.getSelectedRows();
        for (int i = 0; i < selected.length; i++) {
            selected[i] = jTable1.convertRowIndexToModel(selected[i]);
        }
        return selected[0];
    }

    /**
     * F??llt die Tabelle mit Daten.
     */
    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int i = model.getRowCount() - 1; i > -1; i--) {
            model.removeRow(i);
        }
        for (int i = 0; i < ausleiheListe.size(); i++) {
            model.addRow(addObject(i));
        }
    }

    /**
     * F??gt ein neues Objekt hinzu
     *
     * @param i Indices
     * @return Neues Objekt
     */
    private Object[] addObject(int i) {
        String medienName = "";
        List<Medien> medienlist = panelHandler.returnMedien();
        for (int j = 0; j < medienlist.size(); j++) {
            if (ausleiheListe.get(i).getMedienid() == medienlist.get(j).getId()) {
                medienName = medienlist.get(j).getName();
            }
        }
        String kategorieName = "";
        List<Kategorie> kategorielist = panelHandler.getKategorieListe();
        for (int j = 0; j < kategorielist.size(); j++) {
            if (ausleiheListe.get(i).getKategorieid() == kategorielist.get(j).getId()) {
                medienName = kategorielist.get(j).getName();
            }
        }
        return new Object[]{ausleiheListe.get(i).getId(), medienName, ausleiheListe.get(i).getDate(), panelHandler.getAktuellerUser().getUsername(), kategorieName};
    }

    /**
     * Updatet die Informationen im aktuellen Panel.
     */
    @Override
    public void update() {
        Logger.info(this, "update");
        ausleiheListe = panelHandler.getAllAusleihen();
        fillTable();
    }

    /**
     * Setzt die Sprachkonfiguration anhand der Properties um.
     *
     * @param props Properties Datei
     */
    @Override
    public void updateLanguage(Properties props) {
        entfernenButton.setText((String) props.get(PropertyName.AUSLEIHENBEARBEITENPANEL_ENTFERNENBUTTON));
        sucheField.setText((String) props.get(PropertyName.SUCHEFIELD));
    }
}
