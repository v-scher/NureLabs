package ua.nure.bespalov.pz3;

public class PieceWorker extends Employee {
	int pieces = 0;
	double paymentPerPiece = 0;
	
	public PieceWorker(String _name, String _lastName, int _pieces, double _paymentPerPiece) {
		super(_name, _lastName);
		pieces = _pieces;
		paymentPerPiece = _paymentPerPiece;
	}
	
	public int getPieces() {
		return pieces;
	}
	
	public double getPaymentPerPiece() {
		return paymentPerPiece;
	}
	
	public void setPieces(int pieces) {
		this.pieces = pieces;
	}
	
	public void setPaymentPerPiece(double paymentPerPiece) {
		this.paymentPerPiece = paymentPerPiece;
	}
	
	@Override
	public double getPayment() {
		return pieces * paymentPerPiece;
	}

	@Override
	public String getFullInfo() {
		return super.getFullInfo() + ", pieces: " + getPieces() + ", payment per piece: " + getPaymentPerPiece();
	}
}
