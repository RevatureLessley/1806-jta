package com.crypt.lambda;

public class LambdaPackage {
	//Generics allow for very fluid lambda expressions
	//Naming Convention - You give G; You take T
	//IE You give Give G to Give and you take T from Take
	//G = parameter T = return type
	public interface keep { public void run(); }
	public interface Give<G> { public void run(G g); }
	public interface Take<T> { public T run(); } 
	public interface GiveAndTake<G, T> { public T run(G g); }
	public interface GiveTwo<G1, G2> { public void run(G1 g1, G2 g2); }
	public interface GiveTwoTakeOne< G1, G2, T> { public T run(G1 g1, G2 g2); }
	public interface GiveManyTakeNone<G> { public void run(G ...gs); }
	public interface GiveManyTakeOne<G, T> { public T run (G ...gs); }
}
