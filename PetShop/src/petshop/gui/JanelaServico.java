/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JanelaServico.java
 *
 * Created on Apr 8, 2011, 3:40:20 PM
 */

package petshop.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import petshop.classes.BancoDeDados;
import petshop.classes.LetraMaiuscula;
import petshop.classes.Servico;

/**
 *
 * @author arthur
 */
public class JanelaServico extends javax.swing.JDialog {

    private TipoJanela tipo;

    /** Creates new form JanelaCliente */
    public JanelaServico(TipoJanela tipo) {
        this.tipo = tipo;
        initComponents();

        this.setLocationRelativeTo(this.getContentPane());

        KeyListener k1 = new KeyListener() {
                    public void keyTyped(KeyEvent e) {
                        if (!((e.getKeyChar() >= KeyEvent.VK_0 &&
                               e.getKeyChar() <= KeyEvent.VK_9) ||
                              (e.getKeyChar() == KeyEvent.VK_BACK_SPACE))) {
                            e.consume(); } }
                    public void keyPressed(KeyEvent e) { }
                    public void keyReleased(KeyEvent e) { } };

        KeyListener k2 = new KeyListener() {
                    public void keyTyped(KeyEvent e) {
                        if (!((e.getKeyChar() >= KeyEvent.VK_0 &&
                               e.getKeyChar() <= KeyEvent.VK_9) ||
                               (e.getKeyChar() <= KeyEvent.VK_PERIOD) ||
                              (e.getKeyChar() == KeyEvent.VK_BACK_SPACE))) {
                            e.consume(); } }
                    public void keyPressed(KeyEvent e) { }
                    public void keyReleased(KeyEvent e) { } };

        campoDuracao.addKeyListener(k1);
        campoCodigo.addKeyListener(k1);
        campoPreco.addKeyListener(k2);

        if(this.tipo == TipoJanela.ALTERACAO){
            botaoCadastrar.setText("Alterar");
        } else if(this.tipo == TipoJanela.INFORMACAO){
            botaoCadastrar.setVisible(false);
            botaoCancelar.setVisible(false);
            desabilitarCampos();
        }

        campoCodigo.setDocument(new LetraMaiuscula(9));
        campoNome.setDocument(new LetraMaiuscula(80));
        campoDuracao.setDocument(new LetraMaiuscula(14));
        areaInformacoes.setDocument(new LetraMaiuscula(400));

        reiniciar();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        campoNome = new javax.swing.JTextField();
        campoDuracao = new javax.swing.JTextField();
        scrollInformacoes = new javax.swing.JScrollPane();
        areaInformacoes = new javax.swing.JTextArea();
        botaoCadastrar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        campoPreco = new javax.swing.JTextField();
        campoCodigo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Serviço");
        setMinimumSize(new java.awt.Dimension(380, 270));
        setResizable(false);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        campoNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tirarEtiqueta(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                colocarEtiqueta(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(campoNome, gridBagConstraints);

        campoDuracao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tirarEtiqueta(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                colocarEtiqueta(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(campoDuracao, gridBagConstraints);

        areaInformacoes.setColumns(20);
        areaInformacoes.setLineWrap(true);
        areaInformacoes.setRows(5);
        areaInformacoes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tirarEtiqueta(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                colocarEtiqueta(evt);
            }
        });
        scrollInformacoes.setViewportView(areaInformacoes);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(scrollInformacoes, gridBagConstraints);

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrar(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(botaoCadastrar, gridBagConstraints);

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelar(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(botaoCancelar, gridBagConstraints);

        campoPreco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tirarEtiqueta(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                colocarEtiqueta(evt);
            }
        });
        campoPreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eventoDigitarPreco(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(campoPreco, gridBagConstraints);

        campoCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tirarEtiqueta(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                colocarEtiqueta(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(campoCodigo, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tirarEtiqueta(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tirarEtiqueta
        JTextComponent campo = (JTextComponent) evt.getComponent();

        if(campo.getText().equals(getEtiqueta(campo))){
            if(tipo != TipoJanela.INFORMACAO){
                if(campo.equals(campoDuracao))
                    campoDuracao.setDocument(new LetraMaiuscula(3));
                campo.setText("");
            }
        }
}//GEN-LAST:event_tirarEtiqueta

    private void colocarEtiqueta(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_colocarEtiqueta
        JTextComponent campo = (JTextComponent) evt.getComponent();

        if(campo.getText().equals("")){
            if(campo.equals(campoDuracao)) campoDuracao.setDocument(new LetraMaiuscula(14));
            campo.setText(getEtiqueta(campo));
        }
}//GEN-LAST:event_colocarEtiqueta

    private void cadastrar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrar
        
        Servico servico = gerarServico();
        if(!existemDependencias()){
            if(tipo == TipoJanela.CADASTRO){
                if(BancoDeDados.cadastrar(servico)){
                    int resp = JOptionPane.showConfirmDialog(this, "Cliente cadastrado com sucesso. Deseja realizar novo cadastro?", "Concluído", JOptionPane.YES_NO_OPTION);

                    this.reiniciar();
                    if(resp != JOptionPane.YES_OPTION){
                        this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this.getContentPane(), "Falha ao cadastrar servico!");
                }
            } else {
                if(BancoDeDados.alterar(servico)){
                    JOptionPane.showMessageDialog(this.getContentPane(), "Servico alterado com sucesso!");
                    reiniciar();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this.getContentPane(), "Falha ao alterar servico!");
                }
            }
        }
}//GEN-LAST:event_cadastrar

    private void cancelar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelar
        int resp = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja cancelar?", "Sair", JOptionPane.YES_NO_OPTION);

        if(resp == JOptionPane.YES_OPTION){
            this.dispose();
            reiniciar();
        }
}//GEN-LAST:event_cancelar

    private void eventoDigitarPreco(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eventoDigitarPreco
        JTextField campo = (JTextField) evt.getComponent();
        String t = campo.getText();

        if(t.length() == 8) evt.consume();

        //nao deixa ter mais de 1 ponto e coloca 0 automaticamente caso campo
        //seja vazio
        if(evt.getKeyChar() == KeyEvent.VK_PERIOD){
            if(t.contains("."))
                evt.consume();
            if(t.equals(""))
                campo.setText("0");
        }
        //Nao deixa decimal ser maior que 2
        if(t.length() > 3)
            if(t.substring(t.length()-3, t.length()-2).equals("."))
                evt.consume();
        //se tamanho do preco == 5 e nao for backspace e nao tiver outra virgula
        //coloca virgula automaticamente
        if(t.length() == 5 && evt.getKeyChar() != KeyEvent.VK_BACK_SPACE && !t.contains("."))
            campo.setText(t + ".");
    }//GEN-LAST:event_eventoDigitarPreco

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaServico(TipoJanela.CADASTRO).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaInformacoes;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoDuracao;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoPreco;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scrollInformacoes;
    // End of variables declaration//GEN-END:variables


    //Retorna a etiqueta certa pra cada campo
    private String getEtiqueta(JTextComponent campo){

        if(campo.equals(campoNome)) return "NOME";
        else if(campo.equals(campoCodigo)) return "CÓDIGO";
        else if(campo.equals(campoPreco)) return "PREÇO";
        else if(campo.equals(campoDuracao)) return "DURAÇÃO (MIN)";
        else if(campo.equals(areaInformacoes)) return "INFORMAÇÕES ADICIONAIS";

        return "";
    }

    //Volta todos os campos pras etiquetas padrões.
    private void reiniciar(){

        campoCodigo.setText(getEtiqueta(campoCodigo));
        campoNome.setText(getEtiqueta(campoNome));
        campoPreco.setText(getEtiqueta(campoPreco));
        campoDuracao.setText(getEtiqueta(campoDuracao));
        areaInformacoes.setText(getEtiqueta(areaInformacoes));
    }

    private boolean existemDependencias(){
        String msg = "Você esqueceu de preencher os \nseguintes campos obrigatórios:\n\n";

        boolean existeDependencias = false;

        if(campoNome.getText().equals("Nome")){ msg += "- NOME\n"; existeDependencias = true;}
        if(campoDuracao.getText().equals("Duração")){ msg += "- DURAÇÃO\n"; existeDependencias = true;}
        if(campoPreco.getText().equals("Preço")){ msg += "- PREÇO\n"; existeDependencias = true;}

        if(existeDependencias){
            JOptionPane.showMessageDialog(this.getContentPane(), msg);
        }

        return existeDependencias;
    }

    public JTextArea getAreaInformacoes() {
        return areaInformacoes;
    }

    public JTextField getCampoCodigo() {
        return campoCodigo;
    }

    public JTextField getCampoDuracao() {
        return campoDuracao;
    }

    public JTextField getCampoNome() {
        return campoNome;
    }

    public JTextField getCampoPreco() {
        return campoPreco;
    }

    private void desabilitarCampos() {
        campoCodigo.setEditable(false);
        campoNome.setEditable(false);
        campoDuracao.setEditable(false);
        campoPreco.setEditable(false);
        areaInformacoes.setEditable(false);
    }

    private Servico gerarServico(){
        Servico servico = new Servico();
        //Carregando dados do servico
        if(!campoCodigo.getText().equals(getEtiqueta(campoCodigo)))
            servico.setCodigo(Integer.valueOf(campoCodigo.getText()));
        servico.setNome(campoNome.getText());
        servico.setDuracao(Integer.valueOf(campoDuracao.getText()));
        servico.setPrecoVenda(Double.valueOf(campoPreco.getText()));
        if(!areaInformacoes.getText().equals(getEtiqueta(areaInformacoes)))
            servico.setInfo(areaInformacoes.getText());

        return servico;
    }
}
