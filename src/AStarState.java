
import java.util.*;
/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;



    // Инициализируем поле для открытых вершин
    private Map<Location, Waypoint> open = new HashMap<Location, Waypoint> ();

    // Инициализируем поле для закрытых вершин. **/
    private Map<Location, Waypoint> closed = new HashMap<Location, Waypoint> ();




    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        // Если в "открытом" наборе нет вершин, возвращаем NULL.
        if (numOpenWaypoints() == 0)
            return null;


        Set openKeys = open.keySet();      //получаем ключи открытых вершин
        Iterator i = openKeys.iterator();  //инициализируем итератор
        Waypoint min = null;               //Создаем пустую переменную с наименьшей общей стоимостью
        float minCost = Float.MAX_VALUE;  //Инициализируем переменную для наименьшей общей
                                            // стоимости наибольшим возможным float

        // Проходим по всем открытым вершинам
        while (i.hasNext())
        {

            Location loc = (Location)i.next(); //Просматриваем текущую локацию
            Waypoint waypoint = open.get(loc);// просматриваем текущую точку
            float totalCost = waypoint.getTotalCost();//получаем вес точки


            if (totalCost < minCost)
            {
                min = open.get(loc);
                minCost = totalCost;
            }

        }
        // Returns the waypoint with the minimum total cost.
        return min;
    }



    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Location loc = newWP.getLocation(); //получаем локацию точки
        if (open.containsKey(loc)) { //если в открытых вершинах есть данная локация, то сравниваем их вес
            Waypoint curent = open.get(loc);
            if (curent.getTotalCost() > newWP.getTotalCost()) { //если новой точки меньше, чем текущей
                open.put(loc, newWP); //заменяем предыдущую на новую
                return true;
            }
            return false;
        }
        else {
            open.put(loc, newWP);  //если нет данной локации в открытых точках, добавляем ее
            return true;
        }
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        // возвращает количество точек в наборе открытых вершин
        return open.size();
    }

    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint waypoint = open.remove(loc); //удаляем вершину с данной локацией из открытых
        closed.put(loc, waypoint);            //добавляем вершину с данной локацией в закрытые
        // TODO:  Implement.
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {

        // TODO:  Implement.
        return closed.containsKey(loc);//возвращаем встречается ли данная локация в наборе закрытых вершин
    }
}
