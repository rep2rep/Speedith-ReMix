/*
 *   Project: Speedith
 * 
 * File name: SpeedithMainForm.java
 *    Author: Matej Urbas [matej.urbas@gmail.com]
 * 
 *  Copyright © 2011 Matej Urbas
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/*
 * SpeedithMainForm.java
 *
 * Created on 07-Nov-2011, 10:47:11
 */
package speedith.ui;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import speedith.core.lang.*;
import speedith.core.reasoning.*;
import speedith.core.reasoning.args.RuleArg;
import speedith.core.reasoning.args.SpiderRegionArg;
import speedith.core.reasoning.rules.AddFeet;
import speedith.core.reasoning.rules.SplitSpiders;

/**
 *
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
public class SpeedithMainForm extends javax.swing.JFrame {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    private static final String[] SpeedithIcons = {
        "SpeedithIconVennDiagram-16.png",
        "SpeedithIconVennDiagram-32.png",
        "SpeedithIconVennDiagram-48.png",
        "SpeedithIconVennDiagram-64.png",
        "SpeedithIconVennDiagram-128.png"
    };
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /** Creates new form SpeedithMainForm */
    public SpeedithMainForm() {
        initComponents();
        try {
            ArrayList<Image> icons = new ArrayList<Image>();
            // Set the icon of this window:
            for (String path : SpeedithIcons) {
                InputStream imgStream = this.getClass().getResourceAsStream(path);
                icons.add(ImageIO.read(imgStream));
            }
            setIconImages(icons);
        } catch (IOException ex) {
            Logger.getLogger(SpeedithMainForm.class.getName()).log(Level.WARNING, "Speedith's icons could not have been loaded.", ex);
        }
        
        initGoals();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Auto-generated code">
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        scrlPnlGoals = new javax.swing.JScrollPane();
        pnlGoals = new javax.swing.JPanel();
        goalsPanel1 = new speedith.ui.GoalsPanel();
        goalsPanel2 = new speedith.ui.GoalsPanel();
        goalsPanel3 = new speedith.ui.GoalsPanel();
        goalsPanel4 = new speedith.ui.GoalsPanel();
        goalsPanel5 = new speedith.ui.GoalsPanel();
        lblGoals = new javax.swing.JLabel();
        pnlRulesSidePane = new javax.swing.JPanel();
        lblAppliedRules = new javax.swing.JLabel();
        scrlPnlAppliedRules = new javax.swing.JScrollPane();
        lstAppliedRules = new javax.swing.JList();
        lblApplyRule = new javax.swing.JLabel();
        cmbxApplyRule = new javax.swing.JComboBox();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        drawMenu = new javax.swing.JMenu();
        rulesMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Speedith");

        scrlPnlGoals.setBackground(new java.awt.Color(255, 255, 255));

        pnlGoals.setLayout(new java.awt.GridBagLayout());

        goalsPanel1.setPreferredSize(new java.awt.Dimension(0, 250));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        pnlGoals.add(goalsPanel1, gridBagConstraints);

        goalsPanel2.setPreferredSize(new java.awt.Dimension(0, 250));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        pnlGoals.add(goalsPanel2, gridBagConstraints);

        goalsPanel3.setPreferredSize(new java.awt.Dimension(0, 250));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        pnlGoals.add(goalsPanel3, gridBagConstraints);

        goalsPanel4.setPreferredSize(new java.awt.Dimension(0, 250));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        pnlGoals.add(goalsPanel4, gridBagConstraints);

        goalsPanel5.setPreferredSize(new java.awt.Dimension(0, 250));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        pnlGoals.add(goalsPanel5, gridBagConstraints);

        scrlPnlGoals.setViewportView(pnlGoals);

        lblGoals.setLabelFor(scrlPnlGoals);
        lblGoals.setText("Goals:");

        pnlRulesSidePane.setPreferredSize(new java.awt.Dimension(115, 325));

        lblAppliedRules.setLabelFor(lstAppliedRules);
        lblAppliedRules.setText("Applied rules:");

        lstAppliedRules.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Split spider", "Add feet", "Add feet", "Add feet" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        scrlPnlAppliedRules.setViewportView(lstAppliedRules);

        lblApplyRule.setLabelFor(cmbxApplyRule);
        lblApplyRule.setText("Apply rule:");

        cmbxApplyRule.setModel(getRulesComboList());

        javax.swing.GroupLayout pnlRulesSidePaneLayout = new javax.swing.GroupLayout(pnlRulesSidePane);
        pnlRulesSidePane.setLayout(pnlRulesSidePaneLayout);
        pnlRulesSidePaneLayout.setHorizontalGroup(
            pnlRulesSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlPnlAppliedRules)
            .addComponent(cmbxApplyRule, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblAppliedRules, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
            .addComponent(lblApplyRule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlRulesSidePaneLayout.setVerticalGroup(
            pnlRulesSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRulesSidePaneLayout.createSequentialGroup()
                .addComponent(lblAppliedRules)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrlPnlAppliedRules, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblApplyRule)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbxApplyRule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        drawMenu.setMnemonic('D');
        drawMenu.setText("Draw");
        menuBar.add(drawMenu);

        rulesMenu.setMnemonic('R');
        rulesMenu.setText("Rules");
        menuBar.add(rulesMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrlPnlGoals)
                    .addComponent(lblGoals, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRulesSidePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblGoals)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrlPnlGoals, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                    .addComponent(pnlRulesSidePane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SpeedithMainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SpeedithMainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SpeedithMainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SpeedithMainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SpeedithMainForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbxApplyRule;
    private javax.swing.JMenu drawMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private speedith.ui.GoalsPanel goalsPanel1;
    private speedith.ui.GoalsPanel goalsPanel2;
    private speedith.ui.GoalsPanel goalsPanel3;
    private speedith.ui.GoalsPanel goalsPanel4;
    private speedith.ui.GoalsPanel goalsPanel5;
    private javax.swing.JLabel lblAppliedRules;
    private javax.swing.JLabel lblApplyRule;
    private javax.swing.JLabel lblGoals;
    private javax.swing.JList lstAppliedRules;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel pnlGoals;
    private javax.swing.JPanel pnlRulesSidePane;
    private javax.swing.JMenu rulesMenu;
    private javax.swing.JScrollPane scrlPnlAppliedRules;
    private javax.swing.JScrollPane scrlPnlGoals;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Spider Diagram Examples">
    /**
     * s1: A, B
     * s2: AB
     * @return
     */
    public static PrimarySpiderDiagram getSDExample1() {
        PrimarySpiderDiagram emptyPSD = SpiderDiagrams.createPrimarySD(null, null, null, null);
        Region s1Region = regionA_B__B_A();
        Region s2Region = regionAB();
        emptyPSD = emptyPSD.addSpider("s1", s1Region);
        return emptyPSD.addSpider("s2", s2Region);
    }
    
    /**
     * s1: A
     * s2: AB
     * @return
     */
    public static PrimarySpiderDiagram getSDExample5() {
        PrimarySpiderDiagram emptyPSD = SpiderDiagrams.createPrimarySD(null, null, null, null);
        Region s1Region = regionA_B();
        Region s2Region = regionAB();
        emptyPSD = emptyPSD.addSpider("s1", s1Region);
        return emptyPSD.addSpider("s2", s2Region);
    }
    
    /**
     * s1: B
     * s2: AB
     * @return
     */
    public static PrimarySpiderDiagram getSDExample6() {
        PrimarySpiderDiagram emptyPSD = SpiderDiagrams.createPrimarySD(null, null, null, null);
        Region s1Region = regionB_A();
        Region s2Region = regionAB();
        emptyPSD = emptyPSD.addSpider("s1", s1Region);
        return emptyPSD.addSpider("s2", s2Region);
    }
    
    /**
     * s1: A, AB
     * s2: B, AB
     * @return
     */
    public static PrimarySpiderDiagram getSDExample7() {
        PrimarySpiderDiagram emptyPSD = SpiderDiagrams.createPrimarySD(null, null, null, null);
        Region s1Region = regionA_B__AB();
        Region s2Region = regionB_A__AB();
        emptyPSD = emptyPSD.addSpider("s1", s1Region);
        return emptyPSD.addSpider("s2", s2Region);
    }
    
    /**
     * s1: B, AB
     * s2: AB
     * @return
     */
    public static PrimarySpiderDiagram getSDExample8() {
        PrimarySpiderDiagram emptyPSD = SpiderDiagrams.createPrimarySD(null, null, null, null);
        Region s1Region = regionB_A__AB();
        Region s2Region = regionAB();
        emptyPSD = emptyPSD.addSpider("s1", s1Region);
        return emptyPSD.addSpider("s2", s2Region);
    }
    
    /**
     * s1: B, AB
     * s2: A, AB
     * @return
     */
    public static PrimarySpiderDiagram getSDExample9() {
        PrimarySpiderDiagram emptyPSD = SpiderDiagrams.createPrimarySD(null, null, null, null);
        Region s1Region = regionB_A__AB();
        Region s2Region = regionA_B__AB();
        emptyPSD = emptyPSD.addSpider("s1", s1Region);
        return emptyPSD.addSpider("s2", s2Region);
    }
    
    /**
     * s1: A, AB
     * s2: AB
     * @return
     */
    public static PrimarySpiderDiagram getSDExample10() {
        PrimarySpiderDiagram emptyPSD = SpiderDiagrams.createPrimarySD(null, null, null, null);
        Region s1Region = regionA_B__AB();
        Region s2Region = regionAB();
        emptyPSD = emptyPSD.addSpider("s1", s1Region);
        return emptyPSD.addSpider("s2", s2Region);
    }

    public static Goals getStep0() {
        PrimarySpiderDiagram psd1 = getSDExample1();
        PrimarySpiderDiagram psd2 = getSDExample7();
        CompoundSpiderDiagram csd = SpiderDiagrams.createCompoundSD(Operator.Implication, psd1, psd2);
        return Goals.createGoalsFrom(csd);
    }

    public static Goals getStep1() {
        RuleArg ruleArg = new SpiderRegionArg(0, 1, "s1", regionA_B());
        return applyInferenceRule(SplitSpiders.InferenceRuleName, ruleArg, getStep0());
//        PrimarySpiderDiagram psd1 = getSDExample5();
//        PrimarySpiderDiagram psd2 = getSDExample6();
//        PrimarySpiderDiagram psd3 = getSDExample7();
//        CompoundSpiderDiagram csd1 = SpiderDiagrams.createCompoundSD(Operator.Disjunction, psd1, psd2);
//        CompoundSpiderDiagram csd2 = SpiderDiagrams.createCompoundSD(Operator.Implication, csd1, psd3);
//        return csd2;
    }

    public static Goals getStep2() {
        RuleArg ruleArg = new SpiderRegionArg(0, 2, "s1", regionAB());
        return applyInferenceRule(AddFeet.InferenceRuleName, ruleArg, getStep1());
//        PrimarySpiderDiagram psd1 = getSDExample10();
//        PrimarySpiderDiagram psd2 = getSDExample6();
//        PrimarySpiderDiagram psd3 = getSDExample7();
//        CompoundSpiderDiagram csd1 = SpiderDiagrams.createCompoundSD(Operator.Disjunction, psd1, psd2);
//        CompoundSpiderDiagram csd2 = SpiderDiagrams.createCompoundSD(Operator.Implication, csd1, psd3);
//        return csd2;
    }

    public static Goals getStep3() {
        RuleArg ruleArg = new SpiderRegionArg(0, 3, "s1", regionAB());
        return applyInferenceRule(AddFeet.InferenceRuleName, ruleArg, getStep2());
//        PrimarySpiderDiagram psd1 = getSDExample10();
//        PrimarySpiderDiagram psd2 = getSDExample8();
//        PrimarySpiderDiagram psd3 = getSDExample7();
//        CompoundSpiderDiagram csd1 = SpiderDiagrams.createCompoundSD(Operator.Disjunction, psd1, psd2);
//        CompoundSpiderDiagram csd2 = SpiderDiagrams.createCompoundSD(Operator.Implication, csd1, psd3);
//        return csd2;
    }

    public static Goals getStep4() {
        RuleArg ruleArg = new SpiderRegionArg(0, 2, "s2", regionB_A());
        return applyInferenceRule(AddFeet.InferenceRuleName, ruleArg, getStep3());
//        PrimarySpiderDiagram psd1 = getSDExample7();
//        PrimarySpiderDiagram psd2 = getSDExample8();
//        PrimarySpiderDiagram psd3 = getSDExample7();
//        CompoundSpiderDiagram csd1 = SpiderDiagrams.createCompoundSD(Operator.Disjunction, psd1, psd2);
//        CompoundSpiderDiagram csd2 = SpiderDiagrams.createCompoundSD(Operator.Implication, csd1, psd3);
//        return csd2;
    }

    public static CompoundSpiderDiagram getSDExample2() {
        PrimarySpiderDiagram psd1 = getSDExample1();
        PrimarySpiderDiagram psd2 = getSDExample1();
        CompoundSpiderDiagram csd = SpiderDiagrams.createCompoundSD(Operator.Equivalence, psd1, psd2);
        return csd;
    }
    
    public static NullSpiderDiagram getSDExample3() {
        return SpiderDiagrams.createNullSD();
    }

    public static CompoundSpiderDiagram getSDExample4() {
        PrimarySpiderDiagram sd1 = getSDExample1();
        SpiderDiagram sd2 = getSDExample2();
        CompoundSpiderDiagram csd = SpiderDiagrams.createCompoundSD(Operator.Conjunction, sd1, sd2);
        return csd;
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="UI Refresh Methods">
    private ComboBoxModel getRulesComboList() {
        Set<String> knownInferenceRules = InferenceRules.getKnownInferenceRules();
        Object[] prettyNames = new Object[knownInferenceRules.size()];
        int i = 0;
        for (String providerName : knownInferenceRules) {
            prettyNames[i++] = InferenceRules.getProvider(providerName).getPrettyName();
        }
        Arrays.sort(prettyNames);
        return new DefaultComboBoxModel(prettyNames);
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Data Initialisation">
    private void initGoals() {
        /////////////////
        /// Initial goal
        /////////////////
        this.goalsPanel1.setGoals(getStep0());
        this.goalsPanel1.setReasoningStep(0);
        /////////////////
        /// Step 1
        /////////////////
//        goals = Goals.createGoalsFrom();
        this.goalsPanel2.setGoals(getStep1());
        this.goalsPanel2.setReasoningStep(1);
        /////////////////
        /// Step 2
        /////////////////
        this.goalsPanel3.setGoals(getStep2());
        this.goalsPanel3.setReasoningStep(2);
        /////////////////
        /// Step 3
        /////////////////
        this.goalsPanel4.setGoals(getStep3());
        this.goalsPanel4.setReasoningStep(3);
        /////////////////
        /// Step 4
        /////////////////
        this.goalsPanel5.setGoals(getStep4());
        this.goalsPanel5.setReasoningStep(4);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Helper Methods">
    private static Goals applyInferenceRule(String infRuleName, RuleArg ruleArg, Goals goals0) {
        InferenceRule<? extends RuleArg> infRule = InferenceRules.getInferenceRule(infRuleName);
        try {
            RuleApplicationResult rar = infRule.apply(ruleArg, goals0);
            goals0 = rar.getGoals();
        } catch (RuleApplicationException ex) {
            throw new RuntimeException(ex);
        }
        return goals0;
    }

    private static Region regionA_B() {
        return new Region(zoneA_B());
    }

    private static Region regionA_B__AB() {
        return new Region(zoneA_B(), zoneAB());
    }

    private static Region regionA_B__B_A() {
        return new Region(zoneA_B(), zoneB_A());
    }

    private static Region regionB_A() {
        return new Region(zoneB_A());
    }

    private static Region regionB_A__AB() {
        return new Region(zoneB_A(), zoneAB());
    }

    private static Region regionAB() {
        return new Region(zoneAB());
    }

    private static Zone zoneAB() {
        return Zone.fromInContours("A", "B");
    }

    private static Zone zoneA_B() {
        return Zone.fromInContours("A").withOutContours("B");
    }

    private static Zone zoneB_A() {
        return Zone.fromInContours("B").withOutContours("A");
    }
    // </editor-fold>
}
