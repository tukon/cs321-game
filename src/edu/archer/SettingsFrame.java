// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

/**
 * The settings window.
 * @author blasek0
 */
public class SettingsFrame extends javax.swing.JFrame {

    /** Reference to the main game object. */
    Archer game;
	
    /**
     * Creates new form SettingsFrame
     */
    public SettingsFrame(Archer game) {
        
        initComponents();
	
	this.game = game;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                BackgroundFieldLabel = new javax.swing.JLabel();
                SaveChangesButton = new javax.swing.JButton();
                RevertToDefaultButton = new javax.swing.JButton();
                SettingsBackgroundComboBox = new javax.swing.JComboBox<>();
                GravityFieldLabel = new javax.swing.JLabel();
                GravityStrengthTextField = new javax.swing.JTextField();
                TraceShotCheckbox = new javax.swing.JCheckBox();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowClosed(java.awt.event.WindowEvent evt) {
                                formWindowClosed(evt);
                        }
                });

                SettingsMenu tempSettings = SettingsMenu.GetSettings();
                BackgroundFieldLabel.setText("Background");

                SaveChangesButton.setText("Save Changes");
                SaveChangesButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                SaveChangesButtonActionPerformed(evt);
                        }
                });

                RevertToDefaultButton.setText("Revert to Default");
                RevertToDefaultButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                RevertToDefaultButtonActionPerformed(evt);
                        }
                });

                SettingsBackgroundComboBox.setMaximumRowCount(6);
                SettingsBackgroundComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Peaceful Meadow", "Night In The Wilderness", "Wonderland", "Fantasy Castle", "Beach At Sunrise", "Gates Of Atlantis" }));
                SettingsBackgroundComboBox.setSelectedIndex(tempSettings.getBackgroundIndex());
                SettingsBackgroundComboBox.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                SettingsBackgroundComboBoxItemStateChanged(evt);
                        }
                });
                SettingsBackgroundComboBox.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                SettingsBackgroundComboBoxActionPerformed(evt);
                        }
                });

                GravityFieldLabel.setText("Gravity");

                GravityStrengthTextField.setText(Double.toString(tempSettings.getGravity()));
                GravityStrengthTextField.addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusLost(java.awt.event.FocusEvent evt) {
                                GravityStrengthTextFieldFocusLost(evt);
                        }
                });
                GravityStrengthTextField.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                GravityStrengthTextFieldMouseClicked(evt);
                        }
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                GravityStrengthTextFieldMouseEntered(evt);
                        }
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                GravityStrengthTextFieldMouseExited(evt);
                        }
                });
                GravityStrengthTextField.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                GravityStrengthTextFieldActionPerformed(evt);
                        }
                });
                GravityStrengthTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                        public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                GravityStrengthTextFieldPropertyChange(evt);
                        }
                });

                TraceShotCheckbox.setText("Trace Shot");
                if (tempSettings.getTraceShotEnabled())
                {
                        //System.out.println(tempSettings.getTraceShotEnabled());
                        TraceShotCheckbox.setSelected(true);
                }
                else
                {
                        //System.out.println(tempSettings.getTraceShotEnabled());
                        TraceShotCheckbox.setSelected(false);
                }
                TraceShotCheckbox.addChangeListener(new javax.swing.event.ChangeListener() {
                        public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                TraceShotCheckboxStateChanged(evt);
                        }
                });
                TraceShotCheckbox.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                TraceShotCheckboxActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(RevertToDefaultButton)
                                        .addComponent(BackgroundFieldLabel)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(GravityFieldLabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(GravityStrengthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(SettingsBackgroundComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(TraceShotCheckbox)
                                        .addComponent(SaveChangesButton))
                                .addContainerGap(44, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(GravityFieldLabel)
                                        .addComponent(GravityStrengthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TraceShotCheckbox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BackgroundFieldLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SettingsBackgroundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SaveChangesButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RevertToDefaultButton)
                                .addContainerGap(24, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

    private void TraceShotCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TraceShotCheckboxActionPerformed
        // TODO add your handling code here:
        //System.out.println(TraceShotCheckbox.isSelected());
        //System.out.println("Checkbox state: " + TraceShotCheckbox.isSelected());
        //System.out.println("Pre-Call state: " + tempSettings.getTraceShotEnabled());
        //tempSettings.setTraceShot(TraceShotCheckbox.isSelected());
        //System.out.println("Post-Call state: " + tempSettings.getTraceShotEnabled());
    }//GEN-LAST:event_TraceShotCheckboxActionPerformed
    private void TraceShotCheckboxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TraceShotCheckboxStateChanged
        // TODO add your handling code here:
        SettingsMenu tempSettings = SettingsMenu.GetSettings();
        tempSettings.setTraceShot(TraceShotCheckbox.isSelected());
    }//GEN-LAST:event_TraceShotCheckboxStateChanged
    private void GravityStrengthTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_GravityStrengthTextFieldPropertyChange
        // TODO add your handling code here:
        double temp = Double.parseDouble(GravityStrengthTextField.getText());
        SettingsMenu tempSettings = SettingsMenu.GetSettings();
        tempSettings.setGravity(temp);

    }//GEN-LAST:event_GravityStrengthTextFieldPropertyChange
    private void GravityStrengthTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GravityStrengthTextFieldActionPerformed
        // TODO add your handling code here:
        double temp = Double.parseDouble(GravityStrengthTextField.getText());
        SettingsMenu tempSettings = SettingsMenu.GetSettings();
        tempSettings.setGravity(temp);
    }//GEN-LAST:event_GravityStrengthTextFieldActionPerformed
    private void SettingsBackgroundComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsBackgroundComboBoxActionPerformed
    // TODO add your handling code here:
    }//GEN-LAST:event_SettingsBackgroundComboBoxActionPerformed
    private void SettingsBackgroundComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SettingsBackgroundComboBoxItemStateChanged
    // TODO add your handling code here:
/*
    String selectedItemStr = null;
    Object selectedItem = SettingsBackgroundComboBox.getSelectedItem();
    if (selectedItem != null)
    {
        selectedItemStr = selectedItem.toString();
    }
    tempSettings.setBackground(selectedItemStr);
    */

    int temp = SettingsBackgroundComboBox.getSelectedIndex();
    SettingsMenu tempSettings = SettingsMenu.GetSettings();
    tempSettings.setBackground(temp);
    }//GEN-LAST:event_SettingsBackgroundComboBoxItemStateChanged
    private void RevertToDefaultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RevertToDefaultButtonActionPerformed
        // TODO add your handling code here:
        SettingsMenu tempSettings = SettingsMenu.GetSettings();
        tempSettings.revertToDefaultSettings();
        SettingsBackgroundComboBox.setSelectedIndex(tempSettings.getBackgroundIndex());
        GravityStrengthTextField.setText(Double.toString(tempSettings.getGravity()));
        TraceShotCheckbox.setSelected(tempSettings.getTraceShotEnabled());
    }//GEN-LAST:event_RevertToDefaultButtonActionPerformed
    private void SaveChangesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveChangesButtonActionPerformed
        // TODO add your handling code here:
        SettingsMenu tempSettings = SettingsMenu.GetSettings();
        tempSettings.saveSettings();
    }//GEN-LAST:event_SaveChangesButtonActionPerformed
    private void GravityStrengthTextFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GravityStrengthTextFieldMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_GravityStrengthTextFieldMouseEntered
    private void GravityStrengthTextFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GravityStrengthTextFieldMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_GravityStrengthTextFieldMouseExited
    private void GravityStrengthTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GravityStrengthTextFieldMouseClicked
        // TODO add your handling code here:
        double temp = Double.parseDouble(GravityStrengthTextField.getText());
        SettingsMenu tempSettings = SettingsMenu.GetSettings();
        tempSettings.setGravity(temp);
    }//GEN-LAST:event_GravityStrengthTextFieldMouseClicked
    private void GravityStrengthTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_GravityStrengthTextFieldFocusLost
        // TODO add your handling code here:
        double temp = Double.parseDouble(GravityStrengthTextField.getText());
        SettingsMenu tempSettings = SettingsMenu.GetSettings();
        tempSettings.setGravity(temp);
    }//GEN-LAST:event_GravityStrengthTextFieldFocusLost

        private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
                game.enableMenuButtons();
        }//GEN-LAST:event_formWindowClosed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel BackgroundFieldLabel;
        private javax.swing.JLabel GravityFieldLabel;
        private javax.swing.JTextField GravityStrengthTextField;
        private javax.swing.JButton RevertToDefaultButton;
        private javax.swing.JButton SaveChangesButton;
        private javax.swing.JComboBox<String> SettingsBackgroundComboBox;
        private javax.swing.JCheckBox TraceShotCheckbox;
        // End of variables declaration//GEN-END:variables
}
// EOF