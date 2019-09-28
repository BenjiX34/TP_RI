package tp2.idf;

public class IDF_BIR_Smooth extends IDF{

	@Override
	public float idf(long docFreq, long docCount) {
		return (float) -Math.log((docFreq+0.5)/(docCount-docFreq+1.0));
	}
	
}
