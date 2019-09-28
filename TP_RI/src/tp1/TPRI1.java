/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgmorenof
 * 
 * La classe a été modifiée pour permettre l'indexation de plusieurs champs
 * ainsi que l'indexation de plusieurs fichiers dans un répertoire
 * 
 */
public class TPRI1 {

    /**
     * @param args the command line arguments
     */
    String[] nameFileXML;
    String nameFolderIndex;

    public TPRI1() {
        nameFileXML = new String[] {"simplewiki.csv"};
        nameFolderIndex = "indexRI/";
    }
    
    public static void main(String[] args) {
        TPRI1 wfr = new TPRI1();
        //wfr.index();  // pas besoin de recréer l'index si on n'a pas modifié l'algo de recherche
        wfr.query("title:France"); //Exec. de la requête
    }
    
    public void index() { //Indexation
        IndexCollection mywikipedia = new IndexCollection(nameFileXML,nameFolderIndex); //Indexation
        try {
            mywikipedia.index(); //Indexation
        } catch (Exception ex) {
            Logger.getLogger(TPRI1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void query(String query) { //Exec de la requête
        QuerySimple qs = new QuerySimple(nameFolderIndex); //Exec. de la requête
        try {
            qs.process(query); //Exec. de la requête
        } catch (Exception ex) {
            Logger.getLogger(TPRI1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
