/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JanelaAdicionaProduto.java
 *
 * Created on Apr 8, 2011, 7:41:36 PM
 */

package petshop.gui;

/**
 *
 * @author arthur
 */
public class JanelaAdicionaProduto extends JanelaAdiciona {


    /** Creates new form JanelaAnimais */
    public JanelaAdicionaProduto() {

        setTitle("Adicionar Produto");
        setModelo(new String[] { "Código", "Nome", "Preço de Custo", "Preço de Venda" });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaAdicionaProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
