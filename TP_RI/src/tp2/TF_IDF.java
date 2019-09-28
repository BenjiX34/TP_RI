package tp2;

import org.apache.lucene.search.similarities.ClassicSimilarity;

import tp2.idf.IDF;
import tp2.tf.TF;

public class TF_IDF extends ClassicSimilarity {
	
	private final TF selected_tf;
	private final IDF selected_idf;
	
	public TF_IDF(TF tf, IDF idf) {
		this.selected_tf = tf; 
		this.selected_idf = idf;
	}
	
	@Override
	public float idf(long docFreq, long docCount) {
		return selected_idf.idf(docFreq, docCount);
	}

	@Override
	public float tf(float freq) {
		return selected_tf.tf(freq);
	}

}
