package helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import data.Tile;
import data.TileGrid;
import data.TileType;

public class Leveler {
	
	// Creates maps by saving them as String on files
	public static void SaveMap(String mapName, TileGrid grid) {
		String mapData = "";

		for (int i = 0; i < grid.getTilesWide(); i++) {
			for (int j = 0; j < grid.getTilesHigh(); j++) {
				mapData += getTIleID(grid.getTile(i, j));
			}
		}
		
		// File methods
		try {
			File file = new File(mapName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(mapData);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	//Load map to game
	public static TileGrid LoadMap(String mapName){
		TileGrid grid = new TileGrid();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(mapName));
			//write file to string
			String data = br.readLine();
			//set grid from that string
			for (int i = 0; i < grid.getTilesWide(); i++) {
				for (int j = 0; j < grid.getTilesHigh(); j++) {
					grid.setTile(i, j, getTileType(data.substring(i * grid.getTilesHigh() + j, i * grid.getTilesHigh() + j + 1)));
				}
			}	
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return grid;
	}
	
	//translate String to Tile
	public static TileType getTileType(String ID) {
		
		TileType type = TileType.NULL;
		
		switch (ID){
		case "0":
			type = TileType.Grass;
			break;
		case "1":
			type = TileType.Dirt;
			break;
		case "2":
			type = TileType.Water;
			break;
		case "3":
			type = TileType.Rock;
			break;
		case "4":
			type = TileType.Forest;
			break;
		case "5":
			type = TileType.NULL;
			break;
		}
		return type;
	}
	
	//translate Tile to String
	public static String getTIleID(Tile t) {
		String ID = "E"; // E - error

		switch (t.getType()) {
		case Grass:
			ID = "0";
			break;
		case Dirt:
			ID = "1";
			break;
		case Water:
			ID = "2";
			break;
		case Rock:
			ID = "3";
			break;
		case Forest:
			ID = "4";
			break;
		case NULL:
			ID = "5";
			break;
		}
		return ID;
	}
}
