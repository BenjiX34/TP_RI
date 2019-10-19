/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5;

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

import tp2.BM11Similarity;
import tp2.TF_IDF;
import tp2.idf.*;
import tp2.tf.*;

/**
 *
 * @author moreno
 */
public class IndexCollection {

    String filename;
    String titleString;
    String indexPath;
    IndexWriter writer;

    public IndexCollection(String filename, String indexPath) {
        this.filename = filename;
        this.indexPath = indexPath;
    }

    public void index() throws IOException {
        File f = new File(filename);
        if (!f.exists()) {
            System.err.println("Filename " + filename + " does not exist");
            return;
        }
        process();
    }

    private void indexDoc(String title, String text) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new TextField("text", text, Field.Store.NO));
        writer.addDocument(doc);
    }

    public void process() throws IOException {
        Path path = new File(indexPath).toPath();
        Directory dir = FSDirectory.open(path);
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);       
        
        iwc.setSimilarity(new BM25Similarity(1.2f, 0.25f)); //Avec BM25
        //iwc.setSimilarity(new BM11Similarity(1.2f)); //Avec BM11
        //iwc.setSimilarity(new BM15Similarity(1.2f)); //Avec BM15
        
        //iwc.setSimilarity(new TF_IDF(new TF_Total(), new IDF_Total())); //Avec TFtotal, IDFtotal
        //iwc.setSimilarity(new TF_IDF(new TF_Total(), new IDF_Sum())); //Avec TFtotal, IDFsum
        //iwc.setSimilarity(new TF_IDF(new TF_Total(), new IDF_Sum_Smooth())); //Avec TFtotal, IDFsum,smooth
        //iwc.setSimilarity(new TF_IDF(new TF_Total(), new IDF_BIR())); //Avec TFtotal, IDFbir
        //iwc.setSimilarity(new TF_IDF(new TF_Total(), new IDF_BIR_Smooth())); //Avec TFtotal, IDFbir, smooth
        //iwc.setSimilarity(new TF_IDF(new TF_Log(), new IDF_Total())); //Avec TFlog, IDFtotal
        //iwc.setSimilarity(new TF_IDF(new TF_Log(), new IDF_Sum())); //Avec TFlog, IDFsum
        //iwc.setSimilarity(new TF_IDF(new TF_Log(), new IDF_Sum_Smooth())); //Avec TFlog, IDFsum,smooth
        //iwc.setSimilarity(new TF_IDF(new TF_Log(), new IDF_BIR())); //Avec TFlog, IDFbir
        //iwc.setSimilarity(new TF_IDF(new TF_Log(), new IDF_BIR_Smooth())); //Avec TFlog, IDFbir,smooth
        
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        boolean create = true;
        if (create) {
            iwc.setOpenMode(OpenMode.CREATE);
        } else {
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
        }
        writer = new IndexWriter(dir, iwc);
        try {
            CSVParser csvFileParser = CSVFormat.DEFAULT.parse(new FileReader(new File(filename)));
            for (CSVRecord csvRecord : csvFileParser) {
                indexDoc(csvRecord.get(1),csvRecord.get(2));
            }
        } catch (IOException e) {
            Logger.getLogger(IndexCollection.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            writer.close();
        }
    }
}
