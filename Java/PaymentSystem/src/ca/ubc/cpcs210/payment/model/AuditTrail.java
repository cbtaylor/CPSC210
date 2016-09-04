package ca.ubc.cpcs210.payment.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Maintain an audit trail of all processed payments. PaymentRecords can be added to the
 * audit trail but not deleted from it.
 * @author Gail Murphy
 */
public class AuditTrail implements Iterable<PaymentRecord> {
	
	/**
	 * INVARIANT: paymentsProcessed is not null
	 */
	
	// The payment records in the audit trail.
	private List<PaymentRecord> paymentsProcessed;
	
	/**
	 * Constructor
	 * 
	 * REQUIRES: nothing
	 * MODIFIES: this
	 * EFFECTS: audit trail is created with a list of payment records available
	 */
	public AuditTrail() {
		paymentsProcessed = new ArrayList<PaymentRecord>();
	}
	
	/**
	 * Add a payment record to the audit trail
	 * 
	 * REQUIRES: record is not null
	 * MODIFIES: this
	 * EFFECTS: payment record added to audit trail
	 */
	public void addPaymentRecord(PaymentRecord record) {
		paymentsProcessed.add(record);
	}

	@Override
	public Iterator<PaymentRecord> iterator() {
		return paymentsProcessed.iterator();
	}
	
	

}
