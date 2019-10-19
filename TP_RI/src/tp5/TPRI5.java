/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;

/**
 *
 * @author jgmorenof
 */
public class TPRI5 {

    /**
     * @param args the command line arguments
     */
    String nameFileXML;
    String nameFolderIndex;

    public TPRI5() {
        nameFileXML = "simplewiki.csv";
        nameFolderIndex = "indexRI/";
    }
    
    public static void main(String[] args) {
        TPRI5 wfr = new TPRI5();
        //wfr.index();  // pas besoin de recréer l'index si on n'a pas modifié l'algo de recherche
        
        /* Thomas and Mario are strikers playing in Munich */
        //wfr.query(new String[]{"title:Thomas", "Mario", "Munich"});
        
        /* Leo scored two goals and assisted Puyol to ensure a 4–0 quarter-final victory over Bayern */
        //wfr.query(new String[]{"Leo", "Puyol", "title:Bayern"});
        
        /* Skype software for Mac */
        //wfr.query(new String[]{"Skype", "title:Mac"});
        
        /* Cowboys fans petition Obama to oust Jones */
        //wfr.query(new String[]{"Cowboys", "Obama", "title:Jones"});
        
        /* Kate and Henry are known for being devoted to the Anglican church */
        //wfr.query(new String[]{"Kate", "Henry", "title:Anglican"});
        
        wfr.multiQuery(new String[][]{
        								{"Thomas", "Mario", "Munich"},
        								{"Leo", "Puyol", "Bayern"},
        								{"Skype", "Mac"},
        								{"Cowboys", "Obama", "Jones"},
        								{"Kate", "Henry", "Anglican"}
        							});
    }
    
    
    public void index() {
        IndexCollection mywikipedia = new IndexCollection(nameFileXML,nameFolderIndex);
        try {
            mywikipedia.index();
        } catch (Exception ex) {
            Logger.getLogger(TPRI5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void multiQuery(String[][] queries) {
        QuerySimple qs = new QuerySimple(nameFolderIndex);
        try {
            qs.process(queries);
        } catch (Exception ex) {
            Logger.getLogger(TPRI5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
