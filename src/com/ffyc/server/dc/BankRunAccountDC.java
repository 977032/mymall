package com.ffyc.server.dc;
import java.util.Date;
import com.ffyc.server.model.BankRunAccount;
public class BankRunAccountDC extends BankRunAccount {
	private Date min;
	private Date max;
	
	public Date getMin() {
		return min;
	}
	public void setMin(Date min) {
		this.min = min;
	}
	public Date getMax() {
		return max;
	}
	public void setMax(Date max) {
		this.max = max;
	}
	
	
}
