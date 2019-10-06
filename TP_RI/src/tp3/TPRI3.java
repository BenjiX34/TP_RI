/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;

/**
 *
 * @author jgmorenof
 */
public class TPRI3 {

    /**
     * @param args the command line arguments
     */
    String nameFileXML;
    String nameFolderIndex;

    public TPRI3() {
        nameFileXML = "simplewiki.csv";
        nameFolderIndex = "indexRI/";
    }
    
    public static void main(String[] args) {
        TPRI3 wfr = new TPRI3();
        //wfr.index();  // pas besoin de recréer l'index si on n'a pas modifié l'algo de recherche
        
        wfr.query("title:Munich", "text:Mario", "text:Thomas");
    }
    
    public void index() {
        IndexCollection mywikipedia = new IndexCollection(nameFileXML,nameFolderIndex);
        try {
            mywikipedia.index();
        } catch (Exception ex) {
            Logger.getLogger(TPRI3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void query(String query, String query2, String query3) {
        QuerySimple qs = new QuerySimple(nameFolderIndex);
        try {
            qs.process(query, query2, query3);
        } catch (Exception ex) {
            Logger.getLogger(TPRI3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
