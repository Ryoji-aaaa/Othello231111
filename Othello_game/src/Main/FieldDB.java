package Main;

public class FieldDB implements SweepMethod{
	private final int row=8 , column=8 ;
	private String[][] bord = new String[row][column];//画面用
	boolean[][] bordCanPut = new boolean[row][column];//裏方用データ（おける場所）
	
	public FieldDB() {
		// 初期値の設定
		for (int i = 0; i < bord.length; i++) {
			for (int j = 0; j < bord[i].length; j++) {
				this.bord[i][j] 		= "|";
				this.bordCanPut[i][j] 	= false ;
			}
		}
		// 中央4マスの白黒を初期配置する
		bord[3][3] = "●";
		bord[4][4] = "●";
		bord[3][4] = "○";
		bord[4][3] = "○";
	}
	/*ゲッターとセッター*/
	public String getXY(int X, int Y) {return bord[Y][X];}
	public boolean[][] getBCP(){return bordCanPut;}
	public boolean getBCPXY(int X , int Y) {return bordCanPut[Y][X];}
	
	public void setXY(int X, int Y, String str) {
		this.bord[Y][X] = str;
	}
	public void setBCP(boolean[][] BCP) {
		this.bordCanPut = BCP;
	}

	// 盤面確認用メソッド
	public void Display() {
		System.out.print("  \\");
		for (int j = 0; j < bord[0].length; j++) {
			System.out.print("  " + j);
		}
		for (int i = 0; i < bord.length; i++) {
			System.out.print("\n  " + i);
			for (int j = 0; j < bord[0].length; j++) {
				if(bordCanPut[i][j]) {
					System.out.print("  x");
				}else {
				System.out.print("  " + this.bord[i][j]);
				}
			}
		}
	}
	
	
	/*以下Sweep（走査)系method*/
	@Override
	public int go0(int X, int Y, int n) {
		String temp = this.getXY(X,Y+n) ;
		switch (temp) {
		case "○":
			return 0;
		case "●":
			return 1;
		case "|":
			return 2;
		default:
			throw new IllegalArgumentException("bordに異物を検知　: "+ temp);
		}
	}

	@Override
	public int go1(int X, int Y, int n) {
		String temp = this.getXY(X+n,Y+n) ;
		switch (temp) {
		case "○":
			return 0;
		case "●":
			return 1;
		case "|":
			return 2;
		default:
			throw new IllegalArgumentException("bordに異物を検知　: "+ temp);
		}
	}

	@Override
	public int go2(int X, int Y, int n) {
		String temp = this.getXY(X+n,Y) ;
		switch (temp) {
		case "○":
			return 0;
		case "●":
			return 1;
		case "|":
			return 2;
		default:
			throw new IllegalArgumentException("bordに異物を検知　: "+ temp);
		}
	}

	@Override
	public int go3(int X, int Y, int n) {
		String temp = this.getXY(X+n,Y-n) ;
		switch (temp) {
		case "○":
			return 0;
		case "●":
			return 1;
		case "|":
			return 2;
		default:
			throw new IllegalArgumentException("bordに異物を検知　: "+ temp);
		}
	}

	@Override
	public int go4(int X, int Y, int n) {
		String temp = this.getXY(X,Y-n) ;
		switch (temp) {
		case "○":
			return 0;
		case "●":
			return 1;
		case "|":
			return 2;
		default:
			throw new IllegalArgumentException("bordに異物を検知　: "+ temp);
		}
	}

	@Override
	public int go5(int X, int Y, int n) {
		String temp = this.getXY(X-n,Y-n) ;
		switch (temp) {
		case "○":
			return 0;
		case "●":
			return 1;
		case "|":
			return 2;
		default:
			throw new IllegalArgumentException("bordに異物を検知　: "+ temp);
		}
	}

	@Override
	public int go6(int X, int Y, int n) {
		String temp = this.getXY(X-n,Y) ;
		switch (temp) {
		case "○":
			return 0;
		case "●":
			return 1;
		case "|":
			return 2;
		default:
			throw new IllegalArgumentException("bordに異物を検知　: "+ temp);
		}
	}

	@Override
	public int go7(int X, int Y, int n) {
		String temp = this.getXY(X-n,Y+n) ;
		switch (temp) {
		case "○":
			return 0;
		case "●":
			return 1;
		case "|":
			return 2;
		default:
			throw new IllegalArgumentException("bordに異物を検知　: "+ temp);
		}
	}

	@Override
	public int goDIR(int dir, int X, int Y, int n) {//DIR=direction
		switch(dir) {
		case 0:
			return go0(X,Y,n);
		case 1:
			return go1(X,Y,n);
		case 2:
			return go2(X,Y,n);
		case 3:
			return go3(X,Y,n);
		case 4:
			return go4(X,Y,n);
		case 5:
			return go5(X,Y,n);
		case 6:
			return go6(X,Y,n);
		case 7:
			return go7(X,Y,n);
		default:
			throw new IllegalArgumentException("方角は0~7までの数字　: "+ dir);
		}
	}

	@Override
	public boolean scanAround(int X, int Y) {//周りに駒があるかの判定
		for(int i=0 ; i<8 ; i++) {
			try {
			int temp = goDIR(i , X , Y , 1);
			if(temp==0 ||temp==1){return true; }
			}catch(Exception e){//進んだ方向が盤面外だった時は飛ばす
				continue;
			}
		}
		return false;
	}

	@Override
	public boolean Sweep(int X, int Y ,int attribute) {//GameOpeのscanPutPoint用
		for(int i=0 ; i<8 ;i++) {
			try {
				int temp = goDIR(i , X , Y , 1);
				if(temp==attribute||temp==2) {continue;}
				//駒を挟めるかの走査
				int n = 2;
				while(true) {
					temp = goDIR(i , X , Y , n);
					if(temp==attribute) {
						return true ;
					}else if(temp == 2) {//マス=空白のとき
						break;
					}else {n++;}
				}
			}catch(Exception e){//進んだ方向が盤面外だった時は飛ばす
				continue;
			}
		}
		return false;
	}
	
}