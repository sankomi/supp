package sanko.suppserver;

public class SomeObject {
	
	private String someId;
	private float someNumber;
	private int someInt;
	
	public SomeObject(String id, float number, int integer) {
		someId = id;
		someNumber = number;
		someInt = integer;
	}
	
	public void setSomeId(String value) {
		someId = value;
	}
	
	public String getSomeId() {
		return someId;
	}
	
	public void setSomeNumber(float value) {
		someNumber = value;
	}
	
	public float getSomeNumber() {
		return someNumber;
	}
	
	public void setSomeInt(int value) {
		someInt = value;
	}
	
	public int getSomeInt() {
		return someInt;
	}
	
}