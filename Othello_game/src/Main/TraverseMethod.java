package Main;

public interface TraverseMethod {
	//ToDo:駒の上下左右、斜めに走査（探査）する
	
	/*場所の走査　方角の8分割と対応させた名前*/
	int go0(int X , int Y , int n ) ;//北
	int go1(int X , int Y , int n ) ;//北東
	int go2(int X , int Y , int n ) ;//東
	int go3(int X , int Y , int n ) ;//南東
	int go4(int X , int Y , int n ) ;//南
	int go5(int X , int Y , int n ) ;//南西
	int go6(int X , int Y , int n ) ;//西
	int go7(int X , int Y , int n ) ;//北西
	
	/*走査の複合型*/
	int goDIR(int dir , int X , int Y , int n );//各方位の呼び出し
	boolean scanAround(int X , int Y);//全方向に1マスだけ行く
	
	/*Traverse*/
	boolean traverse(int X,int Y ,int attribute);
	//boolean TraverseOne(int dir , int X ,int Y);
}
