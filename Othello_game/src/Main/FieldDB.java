package Main;

public class FieldDB {
	private String[][] bord = new String[8][8];

	public FieldDB() {
		// 初期値の設定
		for (int i = 0; i < bord.length; i++) {
			for (int j = 0; j < bord[0].length; j++) {
				this.bord[i][j] = "|";
			}
		}
		// 中央4マスの白黒を初期配置する
		bord[3][3] = "●";
		bord[4][4] = "●";
		bord[3][4] = "○";
		bord[4][3] = "○";
	}

	public String getXY(int X, int Y) {
		return bord[X][Y];
	}

	public void setXY(int X, int Y, String str) {
		this.bord[X][Y] = str;
	}

	// 盤面確認用メソッド
	public void Display() {
		for (int i = 0; i < bord.length; i++) {
			for (int j = 0; j < bord[0].length; j++) {
				System.out.print("  " + this.bord[i][j]);
			}
			System.out.println("");
		}
	}

}