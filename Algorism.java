public class SoraUno
{
	static int[][] map = new int[100][100];
	static int[][] c_map = new int[100][100];
	public static void main(String[] args)
	{	
	
		//＜　変　数　宣　言　＞
		int[] value;
		value = new int[10];
		int 	mode = 1,
			old_mode = 1,
			turn = 0,
			item = 0,
			x = 50,
			y = 50,
			i = 0,
			i1= 0,
			i2= 0,
			j = 0,
			count = 0,
			count2 = 0,
			a = 0;
			
		int move_mode = 2;
		/****競技サーバに接続する****/
		edu.procon.Connect2010 target;
		target = new edu.procon.Connect2010("サンプル",args[0],Integer.parseInt(args[1]));
		
		if(Integer.parseInt(args[1])==2009)
		{
			mode = 1;
			System.out.print("クールで競技に接続しました。");
		}
		else
		{
			mode = 3;
			System.out.print("ホットで競技に接続しました。");
		}
		//map配列を初期化
		for(i2= 0;i2< 100;i2++)
		{
			for(i = 0;i < 100;i++)
			{
				map[i][i2] = 10;
			}
		}
		
		while(true)
		{
			//map配列にmap情報を記憶
			value=target.getReady();
			if(value[0]==0)break;
			map[x-1][y+1] = value[1];
			map[x][y+1]   = value[2];
			map[x+1][y+1] = value[3];
			map[x-1][y]   = value[4];
			map[x][y]     = value[5];
			map[x+1][y]   = value[6];
			map[x-1][y-1] = value[7];
			map[x][y-1]   = value[8];
			map[x+1][y-1] = value[9];

			//mode変数初期化
			old_mode = mode;
			mode = 0;
			
			
			//ターン数を加算
			turn++;
			
			System.out.print("\n\n");
			
			//＜変数の状態をチェック＞
			System.out.print("\nturn="+turn+",mode="+mode+",item="+item+",x="+x+",y="+y);
			System.out.print("\n");
			
			//makeRouteを扱うための情報の初期化
			makeMap(x, y);
			
			//周囲の情報を出力
			for(i2=y+3; i2>y-3; i2--)
			{
				for(i=x-3; i<x+3; i++)
				{
					if (i == x && i2 == y){
						System.out.print("  p");
					}
					else{
						System.out.print(String.format("%3d",map[i][i2]));
					}
				}
				System.out.println();
			}

//□■□■□■□■□■□■□■□■□■□■□ここから上は変更しないでください■□■□■□■□■□■□■□■□■□■□■□
			int get_item = 0;
//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
//＝　　　　　　動作プログラム　　　　　　＝
//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝


/*	Chaser offline　マップアイテム一覧

			記号	  意味		値
			C     自分		(ない)
			□     床			(0)
			H     相手		(1)
			■　    壁			(2)
			★　    アイテム		(3)
			?     不明		(10)			*/

				
/*			□　H　□
				□　C　□
				□　□　□			*/
				//自分の上が敵の場合 ok　ok
				if(value[2] == 1)
				{
					mode = 12;
				}
				
				
/*			□　□　□
				H　C　□
				□　□　□			*/
				//自分の左が敵の場合 ok ok
				else if(value[4] == 1)
				{
					mode = 14;
				}
				
				
/*			□　□　□
				□　C　H
				□　□　□			*/
				//自分の右が敵の場合 ok ok
				else if(value[6] == 1)
				{
					mode = 16;
				}
				
				
/*			□　□　□
				□　C　□
				□　H　□			*/
				//自分の下が敵の場合 ok ok
				else if(value[8] == 1)
				{
					mode = 18;
				}
				
				
/*			H □ □
				□　C　□
				□　□　□			*/
				//自分の左上が敵の場合 ok
				else if(value[1] == 1)
	      {
					// TODO　後ほど思考(アイテム優先)
				}

/*			□　□　H
				□　C　□
				□　□　□			*/				
				//自分の右上が敵の場合 ok
				else if(value[3] == 1)
				{
					// TODO　後ほど思考(アイテム優先)
				}

/*			□　□　□
				□　C　□
				H　□　□			*/					
		    //自分の左下が敵の場合 ok
				else if(value[7] == 1)
				{
					// TODO　後ほど思考(アイテム優先)
				}

/*			□　□　□
				□　C　□
				□　□　H			*/					
		    //自分の右下が敵の場合 ok
	      else if(value[9] == 1)
	      {
					// TODO　後ほど思考(アイテム優先)
				}


/*			□　★　□
				□　C　□
				□　□　□			*/
				//自分の上がアイテムの場合 ok　ok
	      else if(value[2] == 3)
	      {
					mode = 2;
	/*			□　?　□
					■　★ ■
					□　C　□
					□　□　□	*/
					//アイテムの左右がカベで奥が分からないとき ok
					if(value[1] == 2 && value[3] == 2 && map[x][y+2] == 10)
					{
						mode = 32;
					}	
	/*			□　■　□
					■　★ ■
					□　C　□
					□　□　□	*/
					//アイテムの左右がカベで奥がカベのとき
					else if(value[1] == 2 && value[3] == 2 && map[x][y+2] == 2)
					{
						
					}
				}
				
/*			□　□　□
				★　C　□
				□　□　□			*/				
	      //自分の左がアイテムの場合 ok ok
				else if(value[4] == 3)
				{
					mode = 4;
	/*			□　■　□　□
					?　★　C　□
					□　■　□　□		*/
					//アイテムの上下がカベで奥が分からないとき ok
					if(value[1] == 2 && value[7] == 2 && map[x-2][y] == 10)
					{
						mode = 34
					}
		
	/*			□　■　□　□
					■　★　C　□
					□　■　□　□		*/
					//アイテムの上下がカベで奥がカベのとき 
					else if(old_mode == 32){
						mode = 32;
					}
				}
							
/*			□　□　□
				□　C　★
				□　□　□			*/					
		    //自分の右がアイテムの場合 ok
				else if(value[6] == 3)
				{
					mode = 6;
	/*			□　□　■　□
					□　C　★　?
					□　□　■　□		*/
					//アイテムの上下がカベで奥が分からないとき
					if(value[3] == 2 && value[9] == 2 && map[x+2][y] == 10)
					{
						
					}

	/*			□　□　■　□
					□　C　★　■
					□　□　■　□		*/
					//アイテムの上下がカベで奥がカベのとき
					else if(value[3] == 2 && value[9] == 2 && map[x+2][y] == 2)
					{
						
					}
				}
				
/*			□　□　□
				□　C　□
				□　★　□			*/					
		    //自分の下がアイテムの場合 ok
				else if(value[8] == 3)
				{
					mode = 8;
					
	/*			□　□　□
					□　C　□
					■　★　■
					□　?　□	*/
					//アイテムの左右がカベで奥が分からないとき
					if(value[7] == 2 && value[9] == 2 && map[x][y-2] == 10)
					{					
					}
					

	/*			□　□　□
					□　C　□
					■　★　■			
					□　■　□	*/
					//アイテムの左右がカベで奥がカベのとき
					else if(value[7] == 2 && value[9] == 2 && map[x][y-2] == 2)
					{				
					}
				}

/*			★ □ □
				□　C　□
				□　□　□			*/
				//自分の左上がアイテムの場合 ok
				else if(value[1] == 3)
				{
					mode = makeRoute(x - 10, y + 10);
				}

/*			□　□　★
				□　C　□
				□　□　□			*/				
				//自分の右上がアイテムの場合 ok
				else if(value[3] == 3)
				{
					mode = makeRoute(x + 10, y + 10);
				}
				
/*			□　□　□
				□　C　□
				★　□　□			*/					
		    //自分の左下がアイテムの場合 ok
	      else if(value[7] == 3)
				{
					mode = makeRoute(x - 10, y - 10);
				}

/*			□　□　□
				□　C　□
				□　□　★			*/					
		    //自分の右下がアイテムの場合 ok
	      else if(value[9] == 3)
	      {
					mode = makeRoute(x + 10, y - 10);
				}

/*			□　□　□
				□　C　□
				□　□　□			*/
				//周りにアイテムが無いとき
				else if(value[2] != 3 && value[4] != 3 && value[6] != 3 && value[8] != 3) 
				{
					//0~49ターンのとき
					if(turn%200 < 50)
					{
						//右上に行く
						mode = makeRoute(x + 10, y + 10);
					}
					//50~99ターンのとき
					else if(turn%200 < 100)
					{
						//左上に行く
						mode = makeRoute(x - 10, y + 10);
					}
					//99~149ターンのとき
					else if(turn%200 < 150)
					{
						//左下に行く
						mode = makeRoute(x - 10, y - 10);
					}
					//150~199ターンのとき
					else if(turn%200 < 200)
					{
						//右下に行く
						mode = makeRoute(x + 10, y - 10);
					}
					
					
					//ループ回避、その他行動
					
				}

				
				// else if(value[2] != 3 && value[4] != 3 && value[6] != 3 && value[8] != 3) 
				// {
				// 	if(old_mode == 2){
				// 		// 前処理が「上に移動」した場合
				// 		mode = 22;
				// 	}else if(old_mode == 4){
				// 		// 前処理が「左に移動」した場合
				// 		mode = 24;
				// 	}else if(old_mode == 6){
				// 		// 前処理が「右に移動」した場合
				// 		mode = 26;
				// 	}else if(old_mode == 8){
				// 		// 前処理が「下に移動」した場合
				// 		mode = 28;
				// 	}else if(old_mode == 22){
				// 		// 前処理が「上Look」した場合
				// 		if(map[x-1][y+3] == 2){
				// 			mode = makeRoute(x - 1, y + 3);
				// 		}else if(map[x][y+3] == 2){
				// 			mode = makeRoute(x, y + 3);
				// 		}else if(map[x+1][y+3] == 2){
				// 			mode = makeRoute(x + 1, y + 3);
				// 		}
						
				// 		// map[x-1][y+2]
				// 		// map[x][y+2]
				// 		// map[x+1][y+2]
					
				// 	}else if(a == 1){
				// 		// cool 
				// 		//0~49ターンのとき ok
				// 		if(turn%200 < 50)
				// 		{
				// 			//左下に行く
				// 			mode = makeRoute(x - 10, y - 10);						
				// 		}
				// 		//50~99ターンのとき
				// 		else if(turn%200 < 100)
				// 		{
				// 			//右下に行く
				// 			mode = makeRoute(x + 10, y + 10);
				// 		}
				// 		//99~149ターンのとき
				// 		else if(turn%200 < 150)
				// 		{
				// 			//右上に行く
				// 			mode = makeRoute(x - 10, y + 10);
				// 		}
				// 		//150~199ターンのとき
				// 		else if(turn%200 < 200)
				// 		{
				// 			//左上に行く
				// 			mode = makeRoute(x + 10, y + 10);
				// 		}
				// 	}else if(a == 2){
				// 		// hot 
				// 		//0~49ターンのとき　ok
				// 		if(turn%200 < 50)
				// 		{
				// 			//左下に行く
				// 			mode = makeRoute(x - 10, y - 10);

							
				// 		}
				// 		//50~99ターンのとき
				// 		else if(turn%200 < 100)
				// 		{
				// 			//左上に行く
				// 			mode = makeRoute(x - 10, y + 10);
				// 		}
				// 		//99~149ターンのとき
				// 		else if(turn%200 < 150)
				// 		{
				// 			//右上に行く
				// 			mode = makeRoute(x + 10, y + 10);
							
				// 		}
				// 		//150~199ターンのとき
				// 		else if(turn%200 < 200)
				// 		{
				// 			//右下に行く
				// 			mode = makeRoute(x + 10, y - 10);
				// 		}
				// 	}else{
				// 		//0~49ターンのとき ok
				// 		if(turn%200 < 50)
				// 		{
				// 			//右上に行く
				// 			mode = makeRoute(x + 10, y + 10);
				// 		}
				// 		//50~99ターンのとき
				// 		else if(turn%200 < 100)
				// 		{
				// 			//左上に行く
				// 			mode = makeRoute(x - 10, y + 10);
				// 		}
				// 		//99~149ターンのとき
				// 		else if(turn%200 < 150)
				// 		{
				// 			//左下に行く
				// 		mode = makeRoute(x - 10, y - 10);
				// 		}
				// 		//150~199ターンのとき
				// 		else if(turn%200 < 200)
				// 		{
				// 			//右下に行く
				// 			mode = makeRoute(x + 10, y - 10);
				// 		}
				// 	}
					
						
				// }

				
				// }

				// if(turn == 1){
				// 	mode = 32;
				// }else if(turn == 2){
					
				// 	// 1 cool
				// 	// 2 hot
				// 	if(map[x][y+7] == 2 && map[x][y+8] == 2 && map[x][y+9] == 2 ){
				// 		a = 1;
				// 	}else{
				// 		a = 2;
				// 	}
				// }
				

				//動作の命令がないとき(最終手段)
				if(mode == 0) 		mode = 32;

//□■□■□■□■□■□■□■□■□■□■□■□■□■□■ここから下は変更しないでください□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■
				
			//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
			//＝＝＝＝＝　　　コマンド処理　　　＝＝＝＝＝
			//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝	
			switch(mode)
			{
				//＝＝＝＝＝＝＝＝＝＝
				//＝　歩くコマンド　＝
				//＝＝＝＝＝＝＝＝＝＝
				case 2: //上に移動
						value = target.walkUp();
						y++;
						break;
				
				case 4: //左に移動
						value = target.walkLeft();
						x--;
						break;
						
				case 6: //右に移動
						value = target.walkRight();
						x++;
						break;
						
				case 8: //下に移動
						value = target.walkDown();
						y--;
						break;
				
				//＝＝＝＝＝＝＝＝＝＝＝＝＝
				//＝　カベを置くコマンド　＝
				//＝＝＝＝＝＝＝＝＝＝＝＝＝
				case 12: //上にプット
						value = target.putUp();
						break;
				
				case 14: //左にプット
						value = target.putLeft();
						break;
						
				case 16: //右にプット
						value = target.putRight();
						break;
						
				case 18: //下にプット
						value = target.putDown();
						break;
						
				//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
				//＝　周りを広く見るコマンド　＝
				//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
				case 22: //上にルック
						value = target.lookUp();
						//見えた値を保存
						map[x-1][y+3] = value[1]; map[x][y+3] = value[2]; map[x+1][y+3] = value[3];
						map[x-1][y+2] = value[4]; map[x][y+2] = value[5]; map[x+1][y+2] = value[6];
						map[x-1][y+1] = value[7]; map[x][y+1] = value[8]; map[x+1][y+1] = value[9];
						break;
				
				case 24: //左にルック
						value = target.lookLeft();
						//見えた値を保存
						map[x-3][y+1] = value[1]; map[x-2][y+1] = value[2];	map[x-1][y+1] = value[3];
						map[x-3][y]   = value[4]; map[x-2][y]   = value[5];	map[x-1][y]   = value[6];
						map[x-3][y-1] = value[7]; map[x-2][y-1] = value[8];	map[x-1][y-1] = value[9];
						break;
						
				case 26: //右にルック
						value = target.lookRight();
						//見えた値を保存
						map[x+1][y+1] = value[1]; map[x+2][y+1] = value[2];	map[x+3][y+1] = value[3];
						map[x+1][y]   = value[4]; map[x+2][y]   = value[5];	map[x+3][y]   = value[6];
						map[x+1][y-1] = value[7]; map[x+2][y-1] = value[8];	map[x+3][y-1] = value[9];
							
						break;
						
				case 28: //下にルック
						value = target.lookDown();
						//見えた値を保存
						map[x-1][y-1] = value[1]; map[x][y-1] = value[2];	map[x+1][y-1] = value[3];
						map[x-1][y-2] = value[4]; map[x][y-2] = value[5];	map[x+1][y-2] = value[6];
						map[x-1][y-3] = value[7]; map[x][y-3] = value[8];	map[x+1][y-3] = value[9];
						break;
						
				//＝＝＝＝＝＝＝＝＝＝＝＝＝
				//＝　遠くを見るコマンド　＝
				//＝＝＝＝＝＝＝＝＝＝＝＝＝
				case 32: //上にサーチ
						value = target.searchUp();
						//見えた値を保存
						map[x][y+1] = value[1];	map[x][y+2] = value[2];	map[x][y+3] = value[3];
						map[x][y+4] = value[4];	map[x][y+5] = value[5];	map[x][y+6] = value[6];
						map[x][y+7] = value[7];	map[x][y+8] = value[8];	map[x][y+9] = value[9];
						break;
				
				case 34: //左にサーチ
						value = target.searchLeft();
						//見えた値を保存
						map[x-1][y] = value[1];	map[x-2][y] = value[2];	map[x-3][y] = value[3];
						map[x-4][y] = value[4];	map[x-5][y] = value[5];	map[x-6][y] = value[6];
						map[x-7][y] = value[7];	map[x-8][y] = value[8];	map[x-9][y] = value[9];
						break;
						
				case 36: //右にサーチ
						value = target.searchRight();
						//見えた値を保存
						map[x+1][y] = value[1];	map[x+2][y] = value[2];	map[x+3][y] = value[3];
						map[x+4][y] = value[4];	map[x+5][y] = value[5];	map[x+6][y] = value[6];
						map[x+7][y] = value[7];	map[x+8][y] = value[8];	map[x+9][y] = value[9];
						break;
						
				case 38: //下にサーチ
						value = target.searchDown();
						//見えた値を保存
						map[x][y-1] = value[1];	map[x][y-2] = value[2];	map[x][y-3] = value[3];
						map[x][y-4] = value[4];	map[x][y-5] = value[5];	map[x][y-6] = value[6];
						map[x][y-7] = value[7];	map[x][y-8] = value[8];	map[x][y-9] = value[9];
						break;
						
				default: //どれにも当てはまらなかったとき
			}
			if(value[0]==0)break;
		}
		
		//値の保存
		map[x-1][y+1] = value[1];
		map[x][y+1]   = value[2];
		map[x+1][y+1] = value[3];
		map[x-1][y]   = value[4];
		map[x][y]     = value[5];
		map[x+1][y]   = value[6];
		map[x-1][y-1] = value[7];
		map[x][y-1]   = value[8];
		map[x+1][y-1] = value[9];
		/****競技サーバから切断****/
		target.exit();
	}
	
	
	//***↓↓↓↓(makeRoute関数)↓↓↓↓***//
	
	//引数の座標を始点とした遠くに行くほど値の大きくなるマップの作成
	public static void makeMap(int sx, int sy) {
		int x, y, i;
		for(y = 0; y < 100; y++) {
			for(x = 0; x < 100; x++) {
				if(y == 0 || y == 99) {
					c_map[y][x] = 300;
				} else if(x == 0 || x == 99) {
					c_map[y][x] = 300;
				} else{
					c_map[y][x] = 999;
				}
				if(map[y][x] == 2) {
					c_map[y][x] = 300;
				}
			}
		}
		c_map[sx][sy] = 0;
		for(i = 0; i < 999; i++) {
			for(y = 0; y < 100; y++) {
				for(x = 0; x < 100; x++) {
					if(c_map[y][x] == 300) {
					}
					else if(c_map[y][x] == i) {
						if(c_map[y-1][x] == 300) {
						} else if(c_map[y-1][x] > i) {
							c_map[y-1][x] = i + 1;
						}
						if(c_map[y+1][x] == 300) {
						} else if(c_map[y+1][x] > i) {
							c_map[y+1][x] = i + 1;
						}
						if(c_map[y][x-1] == 300) {
						} else if(c_map[y][x-1] > i) {
							c_map[y][x-1] = i + 1;
						}
						if(c_map[y][x+1] == 300) {
						} else if(c_map[y][x+1] > i) {
							c_map[y][x+1] = i + 1;
						}
					}
				}
			}
		}
	}
	
	//引数の値を終点としたルートの生成
	public static int makeRoute(int sx, int sy) {
		int cost = 0,
			mode = 0;
		int x, y, i;
		
		mode = routeDP(sx, sy);
		
		return mode;
	}

	//ルート生成心臓部(再起関数使用)
	public static int routeDP(int x, int y) {
		
		int first = 32;
		int mode = first;
		
		if(c_map[x][y] == 0) {
			return mode;
		}
		
		if(c_map[x][y-1] == c_map[x][y] - 1) {
			mode = routeDP(x, y - 1);
			if(mode == first) mode = 2;
		} else if(c_map[x][y+1] == c_map[x][y] - 1) {
			mode = routeDP(x, y + 1);
			if(mode == first) mode = 8;
		} else if(c_map[x-1][y] == c_map[x][y] - 1) {
			mode = routeDP(x - 1, y);
			if(mode == first) mode = 6;
		} else if(c_map[x+1][y] == c_map[x][y] - 1) {
			mode = routeDP(x + 1, y);
			if(mode == first) mode = 4;
		} else {
			int cost = c_map[x][y];
			int sx = x,
				sy = y;
			
			if(cost > c_map[x][y-1]) {
				cost = c_map[x][y-1];
				sx = x;
				sy = y - 1;
			}
			if(cost > c_map[x][y+1]) {
				cost = c_map[x][y+1];
				sx = x;
				sy = y + 1;
			}
			if(cost > c_map[x-1][y]) {
				cost = c_map[x-1][y];
				sx = x - 1;
				sy = y;
			}
			if(cost > c_map[x+1][y]) {
				cost = c_map[x+1][y];
				sx = x + 1;
				sy = y;
			}
			
			if(sx == x && sy == y) {
				mode = 32;
			}
			else {
				mode = routeDP(sx, sy);
			}
		}
		
		return mode;
	}
}
