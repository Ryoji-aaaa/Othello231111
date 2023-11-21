package Main;

public class Game {
	public static void main(String args[]) {
		FieldDB F = new FieldDB();
		GameOpe g1 = new GameOpe() ;
		g1.referee(F);
		System.out.println("ゲーム終了");
	}

}
