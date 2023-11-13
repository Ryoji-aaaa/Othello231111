package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class GameOpe  {
	public GameOpe() {}
	 //おいた場所から裏返せる場所をArraylistで返すクラス
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
				try {
					if(!F.getBCPXY(X, Y)) {
						System.out.print("その場所は置けません\n");
						System.out.print("白○(X,Y)　--->");
						X = sc.nextInt();
						Y = sc.nextInt();
					}
				}catch(Exception e){
					System.out.print("その場所は置けません\n");
					System.out.print("白○(X,Y)　--->");
					X = sc.nextInt();
					Y = sc.nextInt();
				}
				p1.setXY(X, Y);
				Flip(F,p1);
			}else {System.out.println("-----白○(X,Y)　Skip -----");}
			if(scanPutPoint(F,p2)) {
				F.Display();
				System.out.print("黒●(X,Y)　--->");
				int X = sc.nextInt();
				int Y = sc.nextInt();
				try {
					if(!F.getBCPXY(X, Y)) {
						System.out.print("その場所は置けません\n");
						System.out.print("黒●(X,Y)　--->");
						X = sc.nextInt();
						Y = sc.nextInt();
					}
				}catch(Exception e){
					System.out.print("その場所は置けません\n");
					System.out.print("黒●(X,Y)　--->");
					X = sc.nextInt();
					Y = sc.nextInt();
				}
				p2.setXY(X, Y);
				Flip(F,p2);
			}else {System.out.println("-----黒●(X,Y)　Skip -----");}
		}
		F.Display();
		EndProcessing(F);
		sc.close();
	}
	
	//盤面でPlayer:Pがおける場所をtrueで返す
	public boolean scanPutPoint(FieldDB F,Player P) {
		boolean[][] BCP = F.getBCP();//BCP:bordCanPutの略
		boolean PutPoint = false;	//1つでもおける場所があればtrueになる
		for(int y=0 ; y<BCP.length ; y++) {
			for(int x=0 ; x<BCP[y].length ; x++) { 
				if(F.scanAround(x, y) && F.getXY(x, y).equals("|")) {
					boolean temp = F.Traverse(x,y,P.getAtt()); 
					BCP[y][x] = temp;
					PutPoint  = (temp||PutPoint);	
				}else {BCP[y][x] = false;}
			}
		}
		F.setBCP(BCP);
		return PutPoint;
	}
	
	//ToDo:右下に置くと配列のエラーが発生する。
	public void Flip(FieldDB F ,Player P) {
		ArrayList<int[]> xy = P.FlipList(F, P);
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
	
	public void EndProcessing(FieldDB F) {
		int count_W=0 ,count_B=0;
		for (int y = 0; y < F.getRow(); y++) {
			for (int x = 0; x < F.getCol(); x++) {
				String temp = F.getXY(x ,y);
				switch (temp) {
				case "○":
					count_W++;
					break;
				case "●":
					count_B++;
					break;
				default:
					break;
				}
			}
		}
		System.out.printf("白: %d 黒：%d\n",count_W,count_B);
		if(count_W>count_B) {
			System.out.println("白の勝ち");
		}else if(count_W<count_B) {
			System.out.println("黒の勝ち");
		}else {
			System.out.println("引き分け");
		}
		
		
	}


}
