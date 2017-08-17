package data;
import static helpers.Artist.*;



public class TileGrid {

	public Tile[][] map;
	private int tilesWide, tilesHigh;
	
	
	public TileGrid(){
		
		this.tilesWide = 20;
		this.tilesHigh = 15;
		
		//make Tile Grid
		map = new Tile[tilesWide][tilesHigh];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE , TILE_SIZE, TileType.Grass);
			}
		}
	}
	
	
	//custom map tileGrid with map
	public TileGrid(int [][] newMap){
		this.tilesWide = newMap[0].length;
		this.tilesHigh = newMap.length;
		map = new Tile[tilesWide] [tilesHigh] ;
		
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				switch(newMap[j][i]){
					case 0 :	//grass Tile
						map[i] [j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE , TILE_SIZE, TileType.Grass);
						break;
					case 1 :	//dirt Tile
						map[i] [j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE , TILE_SIZE, TileType.Dirt);
						break;
					case 2 :	//water Tile
						map[i] [j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE , TILE_SIZE, TileType.Water);
						break;
					case 3 :	//water Tile
						map[i] [j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE , TILE_SIZE, TileType.Rock);
						break;
					case 4 :	//water Tile
						map[i] [j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE , TILE_SIZE, TileType.Forest);
						break;
				}
			}
		}
	}
	
	// Allows for dynamic change of the map Tiles
	public void setTile(int xCoord, int yCoord, TileType type){
		map[xCoord][yCoord] = new Tile(xCoord * TILE_SIZE, yCoord * TILE_SIZE, TILE_SIZE , TILE_SIZE, type);
	}
	
	public Tile getTile(int xPlace, int yPlace){
		if(xPlace < tilesWide && yPlace < tilesHigh && xPlace > -1 && yPlace > -1){
			return map[xPlace] [yPlace];
		}
		else{
			//if we go out of the map, return tile border = NULL
			return new Tile(0, 0, 0, 0, TileType.NULL);
		}
	}
	
	public void draw(){
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].draw();//that is Tile.Draw() method
			}
		}
	}

	public int getTilesWide() {
		return tilesWide;
	}


	public void setTilesWide(int tilesWide) {
		this.tilesWide = tilesWide;
	}

	public int getTilesHigh() {
		return tilesHigh;
	}

	public void setTilesHigh(int tilesHigh) {
		this.tilesHigh = tilesHigh;
	}
}
