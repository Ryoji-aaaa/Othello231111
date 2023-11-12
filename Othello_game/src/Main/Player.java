package Main;

import java.util.ArrayList;

public class Player {
	private int X , Y ;
	private final int attribute; //属性：playerが白か黒か
	
	public Player(int attribute) {
		this.X = 0;
		this.Y = 0;
		if(attribute == 0 ||attribute == 1) {//白:0　, 黒:1
		this.attribute = attribute;
		}else {
			throw new IllegalArgumentException("不正な入力です（○か●以外NG）");
		}
	}
	public int getX() {return this.X; }
	public int getY() {return this.Y; }
	public int getAtt() {return this.attribute;}
	
	public void setXY(int X,int Y) {
		this.X = X;
		this.Y = Y;
	}
	
	//裏返す座標をArrayに格納。未完成。
	public ArrayList<Integer> FlipXY(){
		ArrayList<Integer> xy = new ArrayList<>();
		
		
		
		
		return xy ;
	}
	
	
	
}
