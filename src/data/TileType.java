package data;

public enum TileType {

	//Name(<texture> , <can you build towers on it>)
	Grass("TileGrass", true),
	Dirt("TileDirt", false),
	Water("TileWater", false),
	Rock("TileRock", false),
	Forest("TileForest", false),
	
	NULL ("TileNull", false);
	
	String textureName;
	boolean buildable;
	
	TileType(String textureName, boolean buildable){
		
		this.textureName = textureName;
		this.buildable = buildable;
	}
}
