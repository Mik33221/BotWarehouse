package project.view;

import project.model.Cord;
import project.model.GridEntity;
import project.model.WarehouseGrid;

import java.util.ArrayList;

public class WarehousePrinter {

    public void print(WarehouseGrid warehouse) {
        System.out.println(stringify(warehouse));
    }

    // Represents every Robot as R and Shelf as S in a grid
    public String stringify(WarehouseGrid warehouse) {
        int x = warehouse.getWidth();
        int y = warehouse.getHeight();

        ArrayList<GridEntity> shelves = new ArrayList<>();
        shelves.addAll(warehouse.getShelves());
        ArrayList<Cord> shelvesCords = getAllCords(shelves);

        ArrayList<GridEntity> robots = new ArrayList<>();
        robots.addAll(warehouse.getRobots());
        ArrayList<Cord> robotCords = getAllCords(robots);

        ArrayList<String>[][] view = new ArrayList[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                view[i][j] = new ArrayList<String>();
                view[i][j].add("0");
            }
        }
        replaceZero(view, shelvesCords, "S");
        replaceZero(view, robotCords, "R");

        String output = "";
        for (int i = y-1; i >= 0; i--) {
            for (int j = 0; j < x; j++) {
                output += view[j][i].get(0);
                output += " ";
            }
            output += "\n<br>";
        }
        return output;
    }

    private void replaceZero(ArrayList<String>[][] view, ArrayList<Cord> cords, String character){
        for (Cord cord : cords) {
            ArrayList<String> cell = view[cord.getX()][cord.getY()];
            if (cell.size() == 1 && cell.get(0).equals("0")){
                cell.remove(0);
                cell.add(character);
            }
            else {
                cell.add(character);
            }
        }
    }

    private ArrayList<Cord> getAllCords(ArrayList<GridEntity> objects) {
        ArrayList<Cord> coordinates = new ArrayList<>();
        for (GridEntity object : objects) {
            coordinates.add(object.getCord());
        }
        return coordinates;
    }

}
