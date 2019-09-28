package tp2;

import org.apache.lucene.search.similarities.BM25Similarity;

public class BM15Similarity extends BM25Similarity {

	public BM15Similarity(float k1) {
		super(k1, 0.f);
	}
	
}
