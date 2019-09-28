package tp2.idf;

public class IDF_Sum extends IDF{

	@Override
	public float idf(long docFreq, long docCount) {
		return (float) -Math.log(docFreq/docCount);
	}
	
}
