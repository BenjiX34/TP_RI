/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

/**
 *
 * @author jgmorenof
 */
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author moreno
 * 
 * La classe a été modifiée pour permettre l'indexation de plusieurs champs
 * ainsi que l'indexation de plusieurs fichiers dans un répertoire
 * 
 */

public class IndexCollection {

    String[] filenames;
    String titleString;
    String indexPath;
    IndexWriter writer;

    public IndexCollection(String[] filenames, String indexPath) {
        this.filenames = filenames;
        this.indexPath = indexPath;
    }


    public void index() throws IOException { //Indexation
    	for(String filename : filenames) {
	        File f = new File(filename);
	        if (!f.exists()) {
	            System.err.println("Filename " + filenames + " does not exist");
	            return;
	        }
    	}
        process();
    }

    private void indexDoc(String title, String text) throws IOException { //Indexation
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new TextField("text", text, Field.Store.NO)); //Pour indexer le champ "texte"
        writer.addDocument(doc);
    }

    public void process() throws IOException {
        Path path = new File(indexPath).toPath();
        Directory dir = FSDirectory.open(path);
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        boolean create = true;
        if (create) {
            iwc.setOpenMode(OpenMode.CREATE);
        } else {
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
        }
        writer = new IndexWriter(dir, iwc); //Indexation
        try {
        	for(String filename : filenames) {
	            CSVParser csvFileParser = CSVFormat.DEFAULT.parse(new FileReader(new File(filename))); //Lecture du CSV
	            for (CSVRecord csvRecord : csvFileParser) {											 	//Lecture du CSV
	            	indexDoc(csvRecord.get(1),csvRecord.get(2));	//Permet d'indexer plusieurs champs	//Lecture du CSV
	            }
        	}
        } catch (IOException e) {
            Logger.getLogger(IndexCollection.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            writer.close();
        }
    }
}
