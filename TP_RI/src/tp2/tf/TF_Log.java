package tp2.tf;

public class TF_Log extends TF{

	@Override
	public float tf(float freq) {
		return (float) Math.log(1.0+freq);
	}
	
}
