package battleshipMenu.model;

import battleshipMenu.model.*;
import battleshipMenu.view.*;

public class BattleshipMenuModel {
    
    private OpponentType opponentType;
    private ShipColor shipColor;
    private MapSize mapSize;

    public BattleshipMenuModel() {
        // Set default values
        opponentType = OpponentType.PLAYER_VS_PLAYER;
        shipColor = ShipColor.GREEN;
        mapSize = MapSize.SMALL_7x7;
    }

    public OpponentType getOpponentType() {
        return opponentType;
    }

    public void setOpponentType(OpponentType opponentType) {
        this.opponentType = opponentType;
    }

    public ShipColor getShipColor() {
        return shipColor;
    }

    public void setShipColor(ShipColor shipColor) {
        this.shipColor = shipColor;
    }

    public MapSize getMapSize() {
        return mapSize;
    }

    public void setMapSize(MapSize mapSize) {
        this.mapSize = mapSize;
    }
}