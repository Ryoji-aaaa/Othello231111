package Main;

import java.util.ArrayList;

//おいた場所から裏返せる場所をArraylistで返す用クラス
public class Player {
	private int X , Y ;
	private final int attribute; //属性：playerが白か黒か
	
	public Player(int attribute) {
		this.X = 0;
		this.Y = 0;
		if(attribute == 0 ||attribute == 1) {//白:0　, 黒:1
		this.attribute = attribute;
		}else {
			throw new IllegalArgumentException("不正な入力です（0か1以外NG）");
		}
	}
	public int getX() {return this.X; }
	public int getY() {return this.Y; }
	public int getAtt() {return this.attribute;}
	
	public void setXY(int X,int Y) {
		this.X = X;
		this.Y = Y;
	}
	
	//裏返す座標をArrayに格納。
	//ToDo:バグの温床。きれいにする。
	public ArrayList<int[]> FlipList(FieldDB F , Player P){
		ArrayList<int[]> xy = new ArrayList<>();
		int target = 2;//裏返す対称(初期値に意味はなし)
		if(P.getAtt()==0) {target=1;}//黒を探す
		if(P.getAtt()==1) {target=0;}//白を探す
		//8方向に走査
		for(int i=0 ; i<8 ;i++) {
			int x=P.getX(),y=P.getY();
			try {
				int n = 1;
				while(true) {
					int temp = F.goDIR(i ,x ,y ,n);
					if(temp==P.getAtt()) {
						while(n>1) {
							n--;
							xy.add(coordinate(i ,x ,y ,n));
						}
						break;
					}else if(temp==target){
						n++;
						continue;
					}else{break;}
				}
			}catch(Exception e){//進んだ方向が盤面外だった時は飛ばす
				continue;
			}
		}
		return xy;
	}
	//ToDo:方向に三角関数を入れるべきだった+FieldDBにも似たメソッドがある。
	private int[] coordinate(int i ,int x ,int y ,int n) {
		int[] xy = new int[2];
		switch(i) {
		case 0:
			xy[0]=x;
			xy[1]=y+n;
			break;
		case 1:
			xy[0]=x+n;
			xy[1]=y+n;
			break;
		case 2:
			xy[0]=x+n;
			xy[1]=y;
			break;
		case 3:
			xy[0]=x+n;
			xy[1]=y-n;
			break;
		case 4:
			xy[0]=x;
			xy[1]=y-n;
			break;
		case 5:
			xy[0]=x-n;
			xy[1]=y-n;
			break;
		case 6:
			xy[0]=x-n;
			xy[1]=y;
			break;
		case 7:
			xy[0]=x-n;
			xy[1]=y+n;
			break;
		}
		return xy;
	}
	
	
}
