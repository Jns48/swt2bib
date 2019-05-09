package de.swt2bib.ui.panels;

import de.swt2bib.fachlogik.accountverwaltung.Account;
import de.swt2bib.ui.ElternPanel;
import de.swt2bib.ui.PanelHandler;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class AccountsBearbeitenPanel extends ElternPanel {

    ArrayList<Account> accountListe;

    /** Creates new form AccountsBearbeiten */
    public AccountsBearbeitenPanel(PanelHandler panelHandler) {
        super(panelHandler);
        initComponents();
        //setAccountListe();
        //fillTable();
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

    private void sucheFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sucheFieldActionPerformed
        panelHandler.panelUnsichtbar();
        panelHandler.getSuchePanel().setSearchTitel(sucheField.getText());
        panelHandler.getSuchePanel().setVisible(true);
    }//GEN-LAST:event_sucheFieldActionPerformed

    private void bearbeitenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bearbeitenButtonActionPerformed
        try {
            Account a = getAccountfromIndices(getListSelections());
            panelHandler.panelUnsichtbar();
            panelHandler.getUi().add(panelHandler.getAccountBearbeitenPanel());
            panelHandler.getAccountBearbeitenPanel().setAccount(a);
            panelHandler.getAccountBearbeitenPanel().bearbeitenMitarbeiter();
            panelHandler.getAccountBearbeitenPanel().setVisible(true);
        } catch (Exception e) {
        }
        
        
    }//GEN-LAST:event_bearbeitenButtonActionPerformed

    private void anlegenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anlegenButtonActionPerformed
        if(!acountNameZulaessig(accountnameField.getText())){}
        else{
            panelHandler.panelUnsichtbar();
            panelHandler.getUi().add(panelHandler.getAccountBearbeitenPanel());
            panelHandler.getAccountBearbeitenPanel().bearbeitenMitarbeiter();
            panelHandler.getAccountBearbeitenPanel().setNewAccount(new Account(accountnameField.getText(), "todo", false, 0, "todo", "todo", 0, "todo", "todo", "todo"));
            panelHandler.getAccountBearbeitenPanel().setVisible(true);
        }
    }//GEN-LAST:event_anlegenButtonActionPerformed

    private void sucheAccountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sucheAccountFieldActionPerformed
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        
        for (int i = model.getRowCount() - 1; i > -1; i--) {
            model.removeRow(i);
        }
        if(sucheAccountField.getText().equals("Suche Account")||sucheAccountField.getText().equals(""))
        {
         for (int i = 0; i < accountListe.size(); i++) {
            model.addRow(new Object[]{accountListe.get(i).getUsername(),accountListe.get(i).getNachname(),accountListe.get(i).getVorname()});  
        }   
        }
        else{
        for (int i = 0; i < accountListe.size(); i++) {
            if(accountListe.get(i).getUsername().equals(sucheAccountField.getText()))
             model.addRow(new Object[]{accountListe.get(i).getUsername(),accountListe.get(i).getNachname(),accountListe.get(i).getVorname()});  
        }
        }
    }//GEN-LAST:event_sucheAccountFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountnameField;
    private javax.swing.JButton anlegenButton;
    private javax.swing.JButton bearbeitenButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField sucheAccountField;
    private javax.swing.JTextField sucheField;
    // End of variables declaration//GEN-END:variables

    
    public void setAccountListe(ArrayList<Account> account){
        accountListe = account;
    }
    
    public void fillTable(){
        panelHandler.loadAdminAccounts();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        for (int i = model.getRowCount() - 1; i > -1; i--) {
            model.removeRow(i);
        }
        for (int i = 0; i < accountListe.size(); i++) {
          model.addRow(new Object[]{accountListe.get(i).getUsername(),accountListe.get(i).getNachname(),accountListe.get(i).getVorname()});  
        }
    }
    
    private Account getAccountfromIndices(int position) {
		Account selected = null;
		selected = accountListe.get(position);
		return selected;
	}
    
    private int getListSelections() {
	int[] selected = jTable1.getSelectedRows();
	for (int i = 0; i < selected.length; i++) {
		selected[i] = jTable1.convertRowIndexToModel(selected[i]);
	}
	return selected[0];
    }

    private boolean acountNameZulaessig(String text) {
        if(text.length()<=8&&text.length()>0){

            for (int i = 0; i < accountListe.size(); i++) {
                if(accountListe.get(i).getUsername().equals(text))
                        return  false;
            }
            return true;
        } else {
            return false;
        }  
        
    }
}
