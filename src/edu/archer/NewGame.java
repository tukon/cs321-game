// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The “new game” window.
 * @author jonsh_000
 */
public class NewGame extends javax.swing.JFrame {

    /** Reference to the main game object. */
    private Archer game;
    
    /** Reference to the settings menu. */
    private SettingsMenu settings;
    
    /**
     * Creates new form NewGame2
     */
    public NewGame(Archer game) 
    {
        initComponents();
        this.game = game;
	settings = SettingsMenu.GetSettings();
	
	// Load and set frame icon
	ArrayList<BufferedImage> icons = new ArrayList<>();
	String name = "";
	// Load each size: 16, 32, 64, 128, 256
	// 1 << ii is 2^ii; 16 is 2^4; 256 is 2^8
	for (int ii = 4; ii <= 8; ++ii)
	{
		name = "/icons/" + Integer.toString(1 << ii)+ 
			".png";
		icons.add(ResourceLoader.loadImage(name));
	}
	if (!icons.isEmpty())  this.setIconImages(icons);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        P1NameField = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        P1Weapon = new javax.swing.JComboBox<>();
        P1Character = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        P2Weapon = new javax.swing.JComboBox<>();
        P2Character = new javax.swing.JComboBox<>();
        P2TextField = new javax.swing.JTextField();
        DuelButton = new javax.swing.JButton();
        P1TextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Game");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        P1NameField.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        P1NameField.setText("Player1 Name:");

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setText("P1 Character:");

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel3.setText("P1 Weapon:");

        P1Weapon.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        P1Weapon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bow", "Rock", "Laser", "Trident", "Batterang" }));
        P1Weapon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P1WeaponActionPerformed(evt);
            }
        });

        P1Character.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        P1Character.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stick Guy", "Hat Guy", "Cowboy", "Batman", "Superman", "Stormtrooper", "Luke Skywalker", "Roman" }));
        P1Character.setToolTipText("");
        P1Character.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P1CharacterActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel4.setText("Player2 Name:");

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel5.setText("P2 Weapon:");

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel6.setText("P2 Character:");

        P2Weapon.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        P2Weapon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bow", "Rock", "Laser", "Trident", "Batterang" }));
        P2Weapon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P2WeaponActionPerformed(evt);
            }
        });

        P2Character.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        P2Character.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stick Guy", "Hat Guy", "Cowboy", "Batman", "Superman", "Stormtrooper", "Luke Skywalker", "Roman" }));
        P2Character.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P2CharacterActionPerformed(evt);
            }
        });

        P2TextField.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N

        DuelButton.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        DuelButton.setText("Duel");
        DuelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DuelButtonActionPerformed(evt);
            }
        });

        P1TextField.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(P1NameField)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(P1Weapon, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P1Character, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 312, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(P2Weapon, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(P2Character, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGap(441, 441, 441)
                .addComponent(DuelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P1NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(P1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(P2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                            .addComponent(P1Character, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(102, 102, 102))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(P2Character, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(P1Weapon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(P2Weapon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(166, 166, 166)
                .addComponent(DuelButton)
                .addGap(89, 89, 89))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DuelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DuelButtonActionPerformed
        // TODO add your handling code here:
        
        //start the game
        String player1 = P1TextField.getText();
        String player2 = P2TextField.getText();
	game.setUpGame(player1,
		player2,
		P1Character.getSelectedIndex()+1,
		P2Character.getSelectedIndex()+1,
		Projectile.Type.values()[P1Weapon.getSelectedIndex()],
		Projectile.Type.values()[P2Weapon.getSelectedIndex()],
		false);
	this.setVisible(false);
    }//GEN-LAST:event_DuelButtonActionPerformed

    private void P1CharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P1CharacterActionPerformed
        // TODO add your handling code here:
        //System.out.println(P1Character.getSelectedIndex());
        if(P1Character.getSelectedIndex()==5 ||P1Character.getSelectedIndex()==3 || 
                P1Character.getSelectedIndex()==6||P1Character.getSelectedIndex()==4)//this disables the weapon select if a avatar has a preset weapon
        {
            //System.out.println("cool");
            P1Weapon.setEnabled(false);
        }
        else
        {
            P1Weapon.setEnabled(true);
        }
    }//GEN-LAST:event_P1CharacterActionPerformed

    private void P1WeaponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P1WeaponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_P1WeaponActionPerformed

    private void P2CharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P2CharacterActionPerformed
        // TODO add your handling code here:
        if(P2Character.getSelectedIndex()==5 ||P2Character.getSelectedIndex()==3 
                || P2Character.getSelectedIndex()==6 ||P2Character.getSelectedIndex()==4)//this disables the weapon select if a avatar has a preset weapon
        {
            //System.out.println("cool");
            P2Weapon.setEnabled(false);
        }
        else
        {
            P2Weapon.setEnabled(true);
        }
    }//GEN-LAST:event_P2CharacterActionPerformed

    private void P2WeaponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P2WeaponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_P2WeaponActionPerformed

        private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
                game.enableMenuButtons();
        }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DuelButton;
    private javax.swing.JComboBox<String> P1Character;
    private javax.swing.JLabel P1NameField;
    private javax.swing.JTextField P1TextField;
    private javax.swing.JComboBox<String> P1Weapon;
    private javax.swing.JComboBox<String> P2Character;
    private javax.swing.JTextField P2TextField;
    private javax.swing.JComboBox<String> P2Weapon;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
// EOF