package ca.ubc.cpsc210.alarm.model;

/**
 * Represents alarm codes.  Note that an AlarmCode
 * is not necessarily valid.
 */
public class AlarmCode {
	public static final int NUM_DIGITS = 4;
	private String theCode;
	
	/**
	 * Constructor creates an alarm code.  
	 * 
	 * @param code  the alarm code (not necessarily valid)
	 */
	public AlarmCode(String code) {
		theCode = code;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		
		if (other.getClass() != this.getClass())
			return false;
		
		AlarmCode otherCode = (AlarmCode) other;
		return theCode.equals(otherCode.theCode);
	}
	
	@Override
	public int hashCode() {
		return theCode.hashCode();
	}
	
	@Override
	public String toString() {
		if (theCode.length() == 0)
			return "<empty>";
		else
			return theCode;
	}
	
	/**
	 * Determines if code is valid (4 digit, numeric)
	 * @return  true if code is valid, false otherwise
	 */
	public boolean isValid() {
		return theCode != null && theCode.length() == 4 && isNumeric();
	}
	
	/**
	 * Determines if code is numeric.
	 * @return  true if code is numeric, false otherwise
	 */
	private boolean isNumeric() {
		try {
			Integer.parseInt(theCode);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
