/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgmorenof
 */
public class TPRI2 {

    /**
     * @param args the command line arguments
     */
    String nameFileXML;
    String nameFolderIndex;

    public TPRI2() {
        nameFileXML = "/home/benjamin/eclipse-workspace/TP_RI/simplewiki.csv";
        nameFolderIndex = "indexRI/";
    }
    
    public static void main(String[] args) {
        TPRI2 wfr = new TPRI2();
        wfr.index();  // pas besoin de recréer l'index si on n'a pas modifié l'algo de recherche
        wfr.query("the white house");
    }
    
    public void index() {
        IndexCollection mywikipedia = new IndexCollection(nameFileXML,nameFolderIndex);
        try {
            mywikipedia.index();
        } catch (Exception ex) {
            Logger.getLogger(TPRI2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void query(String query) {
        QuerySimple qs = new QuerySimple(nameFolderIndex);
        try {
            qs.process(query);
        } catch (Exception ex) {
            Logger.getLogger(TPRI2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
