package Project1.Beans;

public class GradingFormat {
	private boolean confirmed;
	private Double cutoff;
	private Attachment proof;
	
	/**
	 * @param confirmed
	 * @param filename
	 * @param file
	 */
	public GradingFormat(boolean confirmed, Double cutoff, Attachment proof) {
		this.confirmed = confirmed;
		this.cutoff = cutoff;
		this.proof = proof;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	public Double getCutoff() {
		return cutoff;
	}

	public void setCutoff(Double cutoff) {
		this.cutoff = cutoff;
	}
	
	public Attachment getProof() {
		return proof;
	}

	public void setCutoff(Attachment proof) {
		this.proof = proof;
	}
}