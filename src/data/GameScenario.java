package data;

import static helpers.Leveler.LoadMap;

public class GameScenario {

	public static Game[] levels = new Game[11];

	static TileGrid map1 = LoadMap("campaignLevel_1");
	static TileGrid map2 = LoadMap("campaignLevel_2");
	static TileGrid map3 = LoadMap("campaignLevel_3");
	static TileGrid map4 = LoadMap("campaignLevel_4");
	static TileGrid map5 = LoadMap("campaignLevel_5");
	static TileGrid map6 = LoadMap("campaignLevel_6");
	static TileGrid map7 = LoadMap("campaignLevel_7");
	static TileGrid map8 = LoadMap("campaignLevel_8");
	static TileGrid victoryMap = LoadMap("victoryMap");
	
	//LEVEL 1
	public static void Level_1(){
		
		Enemy[] Enemies = new Enemy[3];
		Enemies[0] = new EnemyZombie(4, 4, map1);
		Enemies[1] = new EnemyCar(4, 4, map1);
		Enemies[2] = new EnemyTank(4, 4, map1);
		
		//WaveManager: <spawningPoints>, <timeBetweenEnemies>, <enemiesPerWave[]>, <wavesForTheLevel>
		
		WaveManager waveManager = new WaveManager(Enemies, 2, new int[]{5, 3, 1}, Enemies.length);
		
		if(levels[0] == null){
			levels[0] = new Game(map1, "                   Lv1: Something, something dark side...",
					waveManager);
		}
		levels[0].update();
	}
	
	//LEVEL 2
	public static void Level_2(){
		
		//(17,1)  (9,7)
		
		Enemy[] Enemies = new Enemy[6];
		Enemies[0] = new EnemyZombie(17, 1, map2);
		Enemies[1] = new EnemyZombie(9, 7, map2);
		
		Enemies[2] = new EnemyCar(17, 1, map2);
		Enemies[3] = new EnemyCar(9, 7, map2);
		
		Enemies[4] = new EnemyPlane(17, 1, map2);
		Enemies[5] = new EnemyPlane(9, 7, map2);
		WaveManager waveManager = new WaveManager(Enemies, 2, new int[]{7, 7, 4, 4, 2, 2}, Enemies.length);
		
		if(levels[1] == null){
			levels[1] = new Game(map2, "                      Lv2: Double the gun, double the fun",
					waveManager);
		}
		levels[1].update();
	}
	
	//LEVEL 3
	public static void Level_3(){
		
		//(10,2)  (12,12)
		
		Enemy[] Enemies = new Enemy[8];
		Enemies[0] = new EnemyZombie(10, 2, map3);
		Enemies[1] = new EnemyZombie(12, 12, map3);
		
		Enemies[2] = new EnemyCar(10, 2, map3);
		Enemies[3] = new EnemyCar(12, 12, map3);
		
		Enemies[4] = new EnemyPlane(10, 2, map3);
		Enemies[5] = new EnemyPlane(12, 12, map3);
		
		Enemies[6] = new EnemyTank(10, 2, map3);
		Enemies[7] = new EnemyTank(12, 12, map3);
		
		WaveManager waveManager = new WaveManager(Enemies, 2, new int[]{8, 8, 4, 4, 3, 3, 3, 3}, Enemies.length);
		
		if(levels[2] == null){
			levels[2] = new Game(map3, "Lv3: The stupidest can ask more than the wisest can answer",
					waveManager);
		}
		levels[2].update();
	}
	
	//LEVEL 4
	public static void Level_4(){
		
		//(2,10)  (10,1)  (18,5)
		
		Enemy[] Enemies = new Enemy[15];
		Enemies[0] = new EnemyZombie(2, 10, map4);
		Enemies[1] = new EnemyZombie(10, 1, map4);
		Enemies[2] = new EnemyZombie(18, 5, map4);
		
		Enemies[3] = new EnemyCar(2, 10, map4);
		Enemies[4] = new EnemyCar(18, 5, map4);
		Enemies[5] = new EnemyCar(10, 1, map4);
		
		Enemies[6] = new EnemyPlane(18, 5, map4);
		Enemies[7] = new EnemyPlane(10, 1, map4);
		Enemies[8] = new EnemyPlane(2, 10, map4);
		
		Enemies[9] = new EnemyTank(10, 1, map4);
		Enemies[10] = new EnemyTank(18, 5, map4);
		Enemies[11] = new EnemyTank(2, 10, map4);
		
		Enemies[12] = new EnemyFlyingSaucer(2, 10, map4);
		Enemies[13] = new EnemyFlyingSaucer(10, 1, map4);
		Enemies[14] = new EnemyFlyingSaucer(18, 5, map4);
		
		WaveManager waveManager = new WaveManager(Enemies, 2, new int[]{9, 9, 9, 5, 5, 5, 4, 4, 4, 3, 3, 3, 1, 1, 1}, Enemies.length);
		
		if(levels[3] == null){
			levels[3] = new Game(map4, "                       Lv4: A man chooses; a slave obeys.",
					waveManager);
		}
		levels[3].update();
	}
	
	//LEVEL 5
	
	public static void Level_5(){
		
		Enemy[] Enemies = new Enemy[15];
		
		Enemies[0] = new EnemyZombie(12, 0, map5);
		Enemies[1] = new EnemyZombie(12, 0, map5);
		Enemies[2] = new EnemyCar(12, 0, map5);
		
		Enemies[3] = new EnemyCar(12, 0, map5);
		Enemies[4] = new EnemyPlane(12, 0, map5);
		Enemies[5] = new EnemyZombie(12, 0, map5);
		
		Enemies[6] = new EnemyPlane(12, 0, map5);
		Enemies[7] = new EnemyCar(12, 0, map5);
		Enemies[8] = new EnemyTank(12, 0, map5);
		
		Enemies[9] = new EnemyPlane(12, 0, map5);
		Enemies[10] = new EnemyTank(12, 0, map5);
		Enemies[11] = new EnemyFlyingSaucer(12, 0, map5);
		
		Enemies[12] = new EnemyFlyingSaucer(12, 0, map5);
		Enemies[13] = new EnemyTank(12, 0, map5);
		Enemies[14] = new EnemyFlyingSaucer(12, 0, map5);
		
		WaveManager waveManager = new WaveManager(Enemies, 2, new int[]{
				11, 11, 9, 9, 6, 11, 6, 9, 5, 6, 5, 4, 4, 5, 4}, Enemies.length);
		
		if(levels[4] == null){
			levels[4] = new Game(map5, "                             Lv5: Stay awhile, and listen!",
					waveManager);
		}
		levels[4].update();
	}
	
	//LEVEL 6
	public static void Level_6(){
		
		Enemy[] Enemies = new Enemy[5];
		Enemies[0] = new EnemyZombie(12, 14, map6);
		Enemies[1] = new EnemyCar(12, 14, map6);
		Enemies[2] = new EnemyPlane(12, 14, map6);
		Enemies[3] = new EnemyTank(12, 14, map6);
		Enemies[4] = new EnemyFlyingSaucer(12, 14, map6);
		
		WaveManager waveManager = new WaveManager(Enemies, 1, new int[]{32, 22, 18, 12, 8}, Enemies.length);
		
		if(levels[6] == null){
			levels[6] = new Game(map6, "                 Lv6: I feel the need – the need for speed",
					waveManager);
		}
		levels[6].update();
	}
	
	//LEVEL 7
	public static void Level_7(){
		
		//(2,12)  (3,12)  (16,0)  (19,3)
		
		Enemy[] Enemies = new Enemy[12];
		
		Enemies[0] = new EnemyPlane(2, 12, map7);
		Enemies[1] = new EnemyTank(19, 3, map7);
		Enemies[2] = new EnemyTank(16, 0, map7);
		
		Enemies[3] = new EnemyPlane(3, 12, map7);
		Enemies[4] = new EnemyTank(2, 12, map7);
		Enemies[5] = new EnemyPlane(16, 0, map7);
		
		Enemies[6] = new EnemyFlyingSaucer(16, 0, map7);
		Enemies[7] = new EnemyTank(3, 12, map7);
		Enemies[8] = new EnemyFlyingSaucer(2, 12, map7);
	
		Enemies[9] = new EnemyPlane(16, 0, map7);
		Enemies[10] = new EnemyTank(3, 12, map7);
		Enemies[11] = new EnemyFlyingSaucer(2, 12, map7);
		
		
		
		WaveManager waveManager = new WaveManager(Enemies, 3, new int[]{
				8, 6, 6, 8, 6, 8, 4, 6, 4, 8, 6, 8}, Enemies.length);
		
		if(levels[8] == null){
			levels[8] = new Game(map7, "                            Lv7: Trust me, i'm an engineer",
					waveManager);
		}
		levels[8].update();
	}
	
	//LEVEL 8
	public static void Level_8(){
		
		//(3,13)
		
		Enemy[] Enemies = new Enemy[15];
		
		Enemies[0] = new EnemyZombie(3, 13, map8);
		Enemies[1] = new EnemyCar(3, 13, map8);
		Enemies[2] = new EnemyZombie(3, 13, map8);
		Enemies[3] = new EnemyCar(3, 13, map8);
		Enemies[4] = new EnemyPlane(3, 13, map8);
		Enemies[5] = new EnemyTank(3, 13, map8);
		Enemies[6] = new EnemyCar(3, 13, map8);
		Enemies[7] = new EnemyPlane(3, 13, map8);
		Enemies[8] = new EnemyTank(3, 13, map8);
		Enemies[9] = new EnemyCar(3, 13, map8);
		Enemies[10] = new EnemyZombie(3, 13, map8);
		Enemies[11] = new EnemyCar(3, 13, map8);
		Enemies[12] = new EnemyFlyingSaucer(3, 13, map8);
		Enemies[13] = new EnemyPlane(3, 13, map8);
		Enemies[14] = new EnemyFlyingSaucer(3, 13, map8);
		
		WaveManager waveManager = new WaveManager(Enemies, 1.8f, new int[]{
				8, 6, 8, 6, 5, 5, 6, 5, 5, 6, 12, 6, 8, 6, 8}, Enemies.length);
		
		if(levels[9] == null){
			levels[9] = new Game(map8, "                                  Lv8: Show me the money",
					waveManager);
		}
		levels[9].update();
	}
	
	public static void Victory(){
		
		Enemy[] Enemies = new Enemy[1];
		Enemies[0] = new EnemyZombie(1, 1, victoryMap);
		
		WaveManager waveManager = new WaveManager(Enemies, 1, new int[]{1}, Enemies.length);
		
		if(levels[10] == null){
			levels[10] = new Game(victoryMap, "           Congratulations, you finished the game!",
					waveManager);
		}
		levels[10].update();
	}
}
