package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class GameOpe  {
	public GameOpe() {}
	 //裏返せる場所を裏返し処理するクラス
	Player p1 = new Player(0);//白
	Player p2 = new Player(1);//黒
	
	public void Referee(FieldDB F) {//Gameからレフェリーに進行が渡される
		Scanner sc = new Scanner(System.in);
		while(scanPutPoint(F,p1)||scanPutPoint(F,p2)) {
			if(scanPutPoint(F,p1)) {
				F.Display();
				System.out.print("白○(X,Y)　--->");
				int X = sc.nextInt();
				int Y = sc.nextInt();
				p1.setXY(X, Y);
				Flip(F,p1);
			}
			if(scanPutPoint(F,p2)) {
				F.Display();
				System.out.print("黒●(X,Y)　--->");
				int X = sc.nextInt();
				int Y = sc.nextInt();
				p2.setXY(X, Y);
				Flip(F,p2);
			}
		}
		//ToDo:置けない場所を指定した場合の条件分岐。終了後の処理。
		sc.close();
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
	
	
	public void Flip(FieldDB F ,Player P) {//
		ArrayList<int[]> xy = P.FlipXY(F, P);
		String str ="Flip";
		if(P.getAtt()==0) {str="○";}//白に反す
		if(P.getAtt()==1) {str="●";}//黒に反す
		F.setXY(P.getX(), P.getY(), str);
		while(!xy.isEmpty()) {
			int x = xy.get(0)[0];
			int y = xy.get(0)[1];
			F.setXY(x, y, str);
			xy.remove(0);
		}
	}




}
