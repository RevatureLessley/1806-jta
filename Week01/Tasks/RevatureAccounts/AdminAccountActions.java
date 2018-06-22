package Tasks.RevatureAccounts;

public static enum AdminAccountActions implements AccountActions {
	APPROVE(2) {
		@override
		public void display() {
			System.out.println("Approving.");
		}
	};

	Integer i;

	AdminAccountActions(Integer i) {
		Integer = i;
	}
}
