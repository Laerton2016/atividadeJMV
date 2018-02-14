/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deadbolt.hack;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.ksoap.SoapObject;
import org.ksoap.transport.HttpTransport;
import org.netbeans.microedition.util.SimpleCancellableTask;

/**
 * @author Deadbolt
 */
public class BuscaCEP extends MIDlet implements CommandListener {

    String url = "http://www.byjg.com.br/xmlnuke-php/webservice.php/ws/cep";
    Endereco endereco;
    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private List list;
    private Form frmCEP;
    private TextField textField;
    private Form frmLogradouro;
    private TextField txLogradouro;
    private TextField txCidade;
    private TextField txUF;
    private Form frmResultadoCEP;
    private StringItem aguardeCEP;
    private Form frmResultadoLogradouro;
    private StringItem aguardeLogradouro;
    private Command cmBuscar;
    private Command cmPorLogradouro;
    private Command cmCEP;
    private Command exitCommand;
    private Command backCommand1;
    private Command backCommand;
    private Command cmBuscaPorCEP;
    private Command backCommand2;
    private Command cmBuscaPorLogradouro;
    private Command backCommand3;
    private Command cmBuscaPorLogradouro1;
    private Ticker ticker;
    private SimpleCancellableTask task;
    private SimpleCancellableTask task1;
    //</editor-fold>//GEN-END:|fields|0|

    // Adicionado por Deadbolt
    private StringItem rsLogradouro;
    private StringItem rsBairro;
    private StringItem rsCidade;
    private StringItem rsUf;
    private StringItem rsCEP;

    /**
     * The BuscaCEP constructor.
     */
    public BuscaCEP() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
        list = new List("Buscar por:", Choice.IMPLICIT);//GEN-BEGIN:|0-initialize|1|0-postInitialize
        list.append("CEP", null);
        list.append("Logradouro", null);
        list.setTicker(getTicker());
        list.addCommand(getExitCommand());
        list.setCommandListener(this);
        list.setSelectedFlags(new boolean[] { false, false });//GEN-END:|0-initialize|1|0-postInitialize
    // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, list);//GEN-LINE:|3-startMIDlet|1|3-postAction
    // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
    // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == frmCEP) {//GEN-BEGIN:|7-commandAction|1|45-preAction
            if (command == backCommand) {//GEN-END:|7-commandAction|1|45-preAction
                // write pre-action user code here
                switchDisplayable(null, list);//GEN-LINE:|7-commandAction|2|45-postAction
            // write post-action user code here
            } else if (command == cmBuscaPorCEP) {//GEN-LINE:|7-commandAction|3|52-preAction
                new Thread() {

                    public void run() {
                        String metodo = "obterLogradouroAuth";
                        SoapObject client = new SoapObject(url, metodo);
                        client.addProperty("cep", textField.getString());
                        client.addProperty("usuario", "");
                        client.addProperty("senha", "");
                        HttpTransport ht = new HttpTransport(url, metodo);
                        endereco = new Endereco();
                        String retorno = "";
                        try {
                            retorno = (String) ht.call(client);
                            String[] arr = split(retorno);

                            if (arr.length > 1) {
                                endereco.setLogradouro(arr[0].trim().trim());
                                endereco.setBairro(arr[1].trim().trim());
                                endereco.setCidade(arr[2].trim().trim());
                                endereco.setUf(arr[3].trim().trim());
                                rsLogradouro = new StringItem("Logradouro:", endereco.getLogradouro());
                                rsBairro = new StringItem("Bairro:", endereco.getBairro());
                                rsCidade = new StringItem("Cidade:", endereco.getCidade());
                                rsUf = new StringItem("UF:", endereco.getUf());

                                aguardeCEP.setText("Pronto!");
                                frmResultadoCEP.append(rsLogradouro);
                                frmResultadoCEP.append(rsBairro);
                                frmResultadoCEP.append(rsCidade);
                                frmResultadoCEP.append(rsUf);
                            }

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }.start();

                switchDisplayable(null, getFrmResultadoCEP());//GEN-LINE:|7-commandAction|4|52-postAction

            }//GEN-BEGIN:|7-commandAction|5|48-preAction
        } else if (displayable == frmLogradouro) {
            if (command == backCommand1) {//GEN-END:|7-commandAction|5|48-preAction
                // write pre-action user code here
                switchDisplayable(null, list);//GEN-LINE:|7-commandAction|6|48-postAction
            // write post-action user code here
            } else if (command == cmBuscaPorLogradouro1) {//GEN-LINE:|7-commandAction|7|96-preAction
                new Thread() {

                    public void run() {
                        String metodo = "obterCEPAuth";
                        SoapObject client = new SoapObject(url, metodo);
                        System.out.println(txLogradouro.getString());
                        client.addProperty("logradouro", txLogradouro.getString());
                        client.addProperty("localidade", txCidade.getString());
                        client.addProperty("UF", txUF.getString());
                        client.addProperty("usuario", "");
                        client.addProperty("senha", "");
                        HttpTransport ht = new HttpTransport(url, metodo);
                        endereco = new Endereco();
                        String retorno = "";
                        try {
                            retorno = (String) ht.call(client);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        if (retorno != null && retorno.equals("")) {
                            endereco.setCep(retorno.trim());
                            rsCEP = new StringItem("CEP:", endereco.getCep());

                            aguardeLogradouro.setText("Pronto!");
                            frmResultadoLogradouro.append(rsCEP);
                        }
                    }
                }.start();



                switchDisplayable(null, getFrmResultadoLogradouro());//GEN-LINE:|7-commandAction|8|96-postAction


            }//GEN-BEGIN:|7-commandAction|9|57-preAction
        } else if (displayable == frmResultadoCEP) {
            if (command == backCommand2) {//GEN-END:|7-commandAction|9|57-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCEP());//GEN-LINE:|7-commandAction|10|57-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|11|66-preAction
        } else if (displayable == frmResultadoLogradouro) {
            if (command == backCommand3) {//GEN-END:|7-commandAction|11|66-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmLogradouro());//GEN-LINE:|7-commandAction|12|66-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|13|26-preAction
        } else if (displayable == list) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|13|26-preAction
                // write pre-action user code here
                listAction();//GEN-LINE:|7-commandAction|14|26-postAction
            // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|15|42-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|16|42-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|17|7-postCommandAction
        }//GEN-END:|7-commandAction|17|7-postCommandAction
    // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|18|
    //</editor-fold>//GEN-END:|7-commandAction|18|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmBuscar ">//GEN-BEGIN:|16-getter|0|16-preInit
    /**
     * Returns an initiliazed instance of cmBuscar component.
     * @return the initialized component instance
     */
    public Command getCmBuscar() {
        if (cmBuscar == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
            cmBuscar = new Command("Buscar", Command.ITEM, 0);//GEN-LINE:|16-getter|1|16-postInit
        // write post-init user code here
        }//GEN-BEGIN:|16-getter|2|
        return cmBuscar;
    }
    //</editor-fold>//GEN-END:|16-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmPorLogradouro ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of cmPorLogradouro component.
     * @return the initialized component instance
     */
    public Command getCmPorLogradouro() {
        if (cmPorLogradouro == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            cmPorLogradouro = new Command("Por Logradouro", Command.ITEM, 0);//GEN-LINE:|18-getter|1|18-postInit
        // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return cmPorLogradouro;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listAction ">//GEN-BEGIN:|24-action|0|24-preAction
    /**
     * Performs an action assigned to the selected list element in the list component.
     */
    public void listAction() {//GEN-END:|24-action|0|24-preAction
        // enter pre-action user code here
        String __selectedString = list.getString(list.getSelectedIndex());//GEN-BEGIN:|24-action|1|28-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("CEP")) {//GEN-END:|24-action|1|28-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCEP());//GEN-LINE:|24-action|2|28-postAction
            // write post-action user code here
            } else if (__selectedString.equals("Logradouro")) {//GEN-LINE:|24-action|3|29-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmLogradouro());//GEN-LINE:|24-action|4|29-postAction
            // write post-action user code here
            }//GEN-BEGIN:|24-action|5|24-postAction
        }//GEN-END:|24-action|5|24-postAction
    // enter post-action user code here
    }//GEN-BEGIN:|24-action|6|
    //</editor-fold>//GEN-END:|24-action|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmCEP ">//GEN-BEGIN:|31-getter|0|31-preInit
    /**
     * Returns an initiliazed instance of cmCEP component.
     * @return the initialized component instance
     */
    public Command getCmCEP() {
        if (cmCEP == null) {//GEN-END:|31-getter|0|31-preInit
            // write pre-init user code here
            cmCEP = new Command("Item", Command.ITEM, 0);//GEN-LINE:|31-getter|1|31-postInit
        // write post-init user code here
        }//GEN-BEGIN:|31-getter|2|
        return cmCEP;
    }
    //</editor-fold>//GEN-END:|31-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmCEP ">//GEN-BEGIN:|33-getter|0|33-preInit
    /**
     * Returns an initiliazed instance of frmCEP component.
     * @return the initialized component instance
     */
    public Form getFrmCEP() {
        if (frmCEP == null) {//GEN-END:|33-getter|0|33-preInit
            // write pre-init user code here
            frmCEP = new Form("Busca por CEP", new Item[] { getTextField() });//GEN-BEGIN:|33-getter|1|33-postInit
            frmCEP.addCommand(getBackCommand());
            frmCEP.addCommand(getCmBuscaPorCEP());
            frmCEP.setCommandListener(this);//GEN-END:|33-getter|1|33-postInit
        // write post-init user code here
        }//GEN-BEGIN:|33-getter|2|
        return frmCEP;
    }
    //</editor-fold>//GEN-END:|33-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|34-getter|0|34-preInit
    /**
     * Returns an initiliazed instance of textField component.
     * @return the initialized component instance
     */
    public TextField getTextField() {
        if (textField == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            textField = new TextField("CEP:", "", 8, TextField.NUMERIC);//GEN-LINE:|34-getter|1|34-postInit
        // write post-init user code here
        }//GEN-BEGIN:|34-getter|2|
        return textField;
    }
    //</editor-fold>//GEN-END:|34-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmLogradouro ">//GEN-BEGIN:|36-getter|0|36-preInit
    /**
     * Returns an initiliazed instance of frmLogradouro component.
     * @return the initialized component instance
     */
    public Form getFrmLogradouro() {
        if (frmLogradouro == null) {//GEN-END:|36-getter|0|36-preInit
            // write pre-init user code here
            frmLogradouro = new Form("Busca por Logradouro", new Item[] { getTxLogradouro(), getTxCidade(), getTxUF() });//GEN-BEGIN:|36-getter|1|36-postInit
            frmLogradouro.addCommand(getBackCommand1());
            frmLogradouro.addCommand(getCmBuscaPorLogradouro1());
            frmLogradouro.setCommandListener(this);//GEN-END:|36-getter|1|36-postInit
        // write post-init user code here
        }//GEN-BEGIN:|36-getter|2|
        return frmLogradouro;
    }
    //</editor-fold>//GEN-END:|36-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txLogradouro ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initiliazed instance of txLogradouro component.
     * @return the initialized component instance
     */
    public TextField getTxLogradouro() {
        if (txLogradouro == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            txLogradouro = new TextField("Logradouro", "", 50, TextField.ANY);//GEN-LINE:|38-getter|1|38-postInit
        // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return txLogradouro;
    }
    //</editor-fold>//GEN-END:|38-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txCidade ">//GEN-BEGIN:|39-getter|0|39-preInit
    /**
     * Returns an initiliazed instance of txCidade component.
     * @return the initialized component instance
     */
    public TextField getTxCidade() {
        if (txCidade == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            txCidade = new TextField("Cidade", "", 32, TextField.ANY);//GEN-LINE:|39-getter|1|39-postInit
        // write post-init user code here
        }//GEN-BEGIN:|39-getter|2|
        return txCidade;
    }
    //</editor-fold>//GEN-END:|39-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txUF ">//GEN-BEGIN:|40-getter|0|40-preInit
    /**
     * Returns an initiliazed instance of txUF component.
     * @return the initialized component instance
     */
    public TextField getTxUF() {
        if (txUF == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
            txUF = new TextField("UF", "", 2, TextField.ANY);//GEN-LINE:|40-getter|1|40-postInit
        // write post-init user code here
        }//GEN-BEGIN:|40-getter|2|
        return txUF;
    }
    //</editor-fold>//GEN-END:|40-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|41-getter|0|41-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|41-getter|0|41-preInit
            // write pre-init user code here
            exitCommand = new Command("Sair", Command.EXIT, 0);//GEN-LINE:|41-getter|1|41-postInit
        // write post-init user code here
        }//GEN-BEGIN:|41-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|41-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|44-getter|0|44-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|44-getter|0|44-preInit
            // write pre-init user code here
            backCommand = new Command("Voltar", Command.BACK, 0);//GEN-LINE:|44-getter|1|44-postInit
        // write post-init user code here
        }//GEN-BEGIN:|44-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|44-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|47-getter|0|47-preInit
    /**
     * Returns an initiliazed instance of backCommand1 component.
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            backCommand1 = new Command("Voltar", Command.BACK, 0);//GEN-LINE:|47-getter|1|47-postInit
        // write post-init user code here
        }//GEN-BEGIN:|47-getter|2|
        return backCommand1;
    }
    //</editor-fold>//GEN-END:|47-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ticker ">//GEN-BEGIN:|50-getter|0|50-preInit
    /**
     * Returns an initiliazed instance of ticker component.
     * @return the initialized component instance
     */
    public Ticker getTicker() {
        if (ticker == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
            ticker = new Ticker("Busca por CEP e Logradouro - desenvolvido por deadbolt");//GEN-LINE:|50-getter|1|50-postInit
        // write post-init user code here
        }//GEN-BEGIN:|50-getter|2|
        return ticker;
    }
    //</editor-fold>//GEN-END:|50-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmBuscaPorCEP ">//GEN-BEGIN:|51-getter|0|51-preInit
    /**
     * Returns an initiliazed instance of cmBuscaPorCEP component.
     * @return the initialized component instance
     */
    public Command getCmBuscaPorCEP() {
        if (cmBuscaPorCEP == null) {//GEN-END:|51-getter|0|51-preInit
            // write pre-init user code here
            cmBuscaPorCEP = new Command("Buscar", Command.SCREEN, 0);//GEN-LINE:|51-getter|1|51-postInit
        // write post-init user code here
        }//GEN-BEGIN:|51-getter|2|
        return cmBuscaPorCEP;
    }
    //</editor-fold>//GEN-END:|51-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmResultadoCEP ">//GEN-BEGIN:|53-getter|0|53-preInit
    /**
     * Returns an initiliazed instance of frmResultadoCEP component.
     * @return the initialized component instance
     */
    public Form getFrmResultadoCEP() {
        if (frmResultadoCEP == null) {//GEN-END:|53-getter|0|53-preInit
            // write pre-init user code here
            frmResultadoCEP = new Form("Resultado da busca por CEP", new Item[] { getAguardeCEP() });//GEN-BEGIN:|53-getter|1|53-postInit
            frmResultadoCEP.addCommand(getBackCommand2());
            frmResultadoCEP.setCommandListener(this);//GEN-END:|53-getter|1|53-postInit
        // write post-init user code here
        }//GEN-BEGIN:|53-getter|2|
        return frmResultadoCEP;
    }
    //</editor-fold>//GEN-END:|53-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand2 ">//GEN-BEGIN:|56-getter|0|56-preInit
    /**
     * Returns an initiliazed instance of backCommand2 component.
     * @return the initialized component instance
     */
    public Command getBackCommand2() {
        if (backCommand2 == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
            backCommand2 = new Command("Voltar", Command.BACK, 0);//GEN-LINE:|56-getter|1|56-postInit
        // write post-init user code here
        }//GEN-BEGIN:|56-getter|2|
        return backCommand2;
    }
    //</editor-fold>//GEN-END:|56-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmBuscaPorLogradouro ">//GEN-BEGIN:|62-getter|0|62-preInit
    /**
     * Returns an initiliazed instance of cmBuscaPorLogradouro component.
     * @return the initialized component instance
     */
    public Command getCmBuscaPorLogradouro() {
        if (cmBuscaPorLogradouro == null) {//GEN-END:|62-getter|0|62-preInit
            // write pre-init user code here
            cmBuscaPorLogradouro = new Command("Buscar", Command.SCREEN, 0);//GEN-LINE:|62-getter|1|62-postInit
        // write post-init user code here
        }//GEN-BEGIN:|62-getter|2|
        return cmBuscaPorLogradouro;
    }
    //</editor-fold>//GEN-END:|62-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmResultadoLogradouro ">//GEN-BEGIN:|64-getter|0|64-preInit
    /**
     * Returns an initiliazed instance of frmResultadoLogradouro component.
     * @return the initialized component instance
     */
    public Form getFrmResultadoLogradouro() {
        if (frmResultadoLogradouro == null) {//GEN-END:|64-getter|0|64-preInit
            // write pre-init user code here
            frmResultadoLogradouro = new Form("Resultado da busca por Logradouro", new Item[] { getAguardeLogradouro() });//GEN-BEGIN:|64-getter|1|64-postInit
            frmResultadoLogradouro.addCommand(getBackCommand3());
            frmResultadoLogradouro.setCommandListener(this);//GEN-END:|64-getter|1|64-postInit
        // write post-init user code here
        }//GEN-BEGIN:|64-getter|2|
        return frmResultadoLogradouro;
    }
    //</editor-fold>//GEN-END:|64-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand3 ">//GEN-BEGIN:|65-getter|0|65-preInit
    /**
     * Returns an initiliazed instance of backCommand3 component.
     * @return the initialized component instance
     */
    public Command getBackCommand3() {
        if (backCommand3 == null) {//GEN-END:|65-getter|0|65-preInit
            // write pre-init user code here
            backCommand3 = new Command("Voltar", Command.BACK, 0);//GEN-LINE:|65-getter|1|65-postInit
        // write post-init user code here
        }//GEN-BEGIN:|65-getter|2|
        return backCommand3;
    }
    //</editor-fold>//GEN-END:|65-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: task ">//GEN-BEGIN:|77-getter|0|77-preInit
    /**
     * Returns an initiliazed instance of task component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {//GEN-END:|77-getter|0|77-preInit
            // write pre-init user code here
            task = new SimpleCancellableTask();//GEN-BEGIN:|77-getter|1|77-execute
            task.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|77-getter|1|77-execute
                    // write task-execution user code here
                }//GEN-BEGIN:|77-getter|2|77-postInit
            });//GEN-END:|77-getter|2|77-postInit
        // write post-init user code here
        }//GEN-BEGIN:|77-getter|3|
        return task;
    }
    //</editor-fold>//GEN-END:|77-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: task1 ">//GEN-BEGIN:|83-getter|0|83-preInit
    /**
     * Returns an initiliazed instance of task1 component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask1() {
        if (task1 == null) {//GEN-END:|83-getter|0|83-preInit
            // write pre-init user code here
            task1 = new SimpleCancellableTask();//GEN-BEGIN:|83-getter|1|83-execute
            task1.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|83-getter|1|83-execute
                    // write task-execution user code here
                }//GEN-BEGIN:|83-getter|2|83-postInit
            });//GEN-END:|83-getter|2|83-postInit
        // write post-init user code here
        }//GEN-BEGIN:|83-getter|3|
        return task1;
    }
    //</editor-fold>//GEN-END:|83-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmBuscaPorLogradouro1 ">//GEN-BEGIN:|95-getter|0|95-preInit
    /**
     * Returns an initiliazed instance of cmBuscaPorLogradouro1 component.
     * @return the initialized component instance
     */
    public Command getCmBuscaPorLogradouro1() {
        if (cmBuscaPorLogradouro1 == null) {//GEN-END:|95-getter|0|95-preInit
            // write pre-init user code here
            cmBuscaPorLogradouro1 = new Command("Buscar", Command.SCREEN, 0);//GEN-LINE:|95-getter|1|95-postInit
        // write post-init user code here
        }//GEN-BEGIN:|95-getter|2|
        return cmBuscaPorLogradouro1;
    }
    //</editor-fold>//GEN-END:|95-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: aguardeCEP ">//GEN-BEGIN:|101-getter|0|101-preInit
    /**
     * Returns an initiliazed instance of aguardeCEP component.
     * @return the initialized component instance
     */
    public StringItem getAguardeCEP() {
        if (aguardeCEP == null) {//GEN-END:|101-getter|0|101-preInit
            // write pre-init user code here
            aguardeCEP = new StringItem("", "Aguarde o resultado da busca por CEP...", Item.PLAIN);//GEN-BEGIN:|101-getter|1|101-postInit
            aguardeCEP.setLayout(ImageItem.LAYOUT_DEFAULT);
            aguardeCEP.setPreferredSize(-1, -1);//GEN-END:|101-getter|1|101-postInit
        // write post-init user code here
        }//GEN-BEGIN:|101-getter|2|
        return aguardeCEP;
    }
    //</editor-fold>//GEN-END:|101-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: aguardeLogradouro ">//GEN-BEGIN:|102-getter|0|102-preInit
    /**
     * Returns an initiliazed instance of aguardeLogradouro component.
     * @return the initialized component instance
     */
    public StringItem getAguardeLogradouro() {
        if (aguardeLogradouro == null) {//GEN-END:|102-getter|0|102-preInit
            // write pre-init user code here
            aguardeLogradouro = new StringItem("", "Aguarde o resultado da busca por Logradouro...");//GEN-BEGIN:|102-getter|1|102-postInit
            aguardeLogradouro.setLayout(ImageItem.LAYOUT_DEFAULT);//GEN-END:|102-getter|1|102-postInit
        // write post-init user code here
        }//GEN-BEGIN:|102-getter|2|
        return aguardeLogradouro;
    }
    //</editor-fold>//GEN-END:|102-getter|2|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }


    private static String[] split(String original) {
        Vector nodes = new Vector();
        String separator = ",";
        int index = original.indexOf(separator);
        while (index >= 0) {
            nodes.addElement(original.substring(0, index));
            original = original.substring(index + separator.length());
            index = original.indexOf(separator);
        }
        nodes.addElement(original);

        String[] result = new String[nodes.size()];
        if (nodes.size() > 0) {
            for (int loop = 0; loop < nodes.size(); loop++) {
                result[loop] = (String) nodes.elementAt(loop);
                System.out.println(result[loop]);
            }

        }

        return result;
    }


}
