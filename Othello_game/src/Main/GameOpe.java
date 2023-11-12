package Main;

public class GameOpe  {
	public GameOpe() {}
	 //裏返せる場所を裏返し処理するクラス
	Player p1 = new Player(0);//白
	Player p2 = new Player(1);//黒
	
	public void Referee(FieldDB F) {//Gameからレフェリーに進行が渡される
		while(scanPutPoint(F,p2)||scanPutPoint(F,p1)) {
			F.Display();
			System.out.print("\n(X,Y)　--->");
			break;
		}
	}
	
	//盤面でPlayer:Pがおける場所をtrueで返す
	public static boolean scanPutPoint(FieldDB F,Player P) {
		boolean[][] BCP = F.getBCP();//BCP:bordCanPutの略
		boolean PutPoint = false;	//1つでもおける場所があればtrueになる
		for(int y=0 ; y<BCP.length ; y++) {
			for(int x=0 ; x<BCP[y].length ; x++) { 
				if(F.scanAround(x, y) && F.getXY(x, y).equals("|")) {
					boolean temp = F.Sweep(x,y,P.getAtt()); //あとでSweepメソッドに入れ替え
					BCP[y][x] = temp;
					PutPoint  = (temp||PutPoint);	
				}else {BCP[y][x] = false;}
			}
		}
		F.setBCP(BCP);
		return PutPoint;
	}
	
	
	public void scanFlipPoint() {//
		
	}




}
