package Tasks.RevatureAccounts;

public static enum StandardActions implements AccountActions {
	SIGN_OUT(1) {
		@override
		public void display() {
			System.out.println("Signing out.");
		}
	};

	Integer index;

	StandardActions(Integer i) {
		index = i;
	}
}
