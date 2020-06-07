package dev.navids.soottutorial.hellosoot;

import soot.Unit;
import soot.UnitPatchingChain;
import soot.jimple.JimpleBody;
import soot.jimple.internal.*;
import soot.toolkits.graph.UnitGraph;

import java.util.*;

public class GraphUtils {

    private static GraphUtils instance;
    private final Unit[] unitList = new Unit[1000];
    private final Set<Unit> visitedUnits = new HashSet<>();
    private int numberUnits = 0;
    private UnitGraph unitGraph;
    private Map<Unit, Integer> indexs = new HashMap<>();
    private Map<Unit, UnitState> stateMap = new HashMap<>();


    private List<Unit> dfsUnits = new ArrayList<>();

    private GraphUtils() {
    }

    public static GraphUtils getInstance() {
        if (instance == null) {
            instance = new GraphUtils();
        }
        return instance;
    }

    public void getPaths(UnitGraph graph) {
        initUnits(graph);
        Unit start = unitList[0];
        Unit target = unitList[numberUnits - 1];
        dfs(graph, start);
    }

    private void initUnits(UnitGraph graph) {
        unitGraph = graph;
        UnitPatchingChain units = graph.getBody().getUnits();
        numberUnits = units.size();
        int index = 0;
        for (Unit unit : units) {
          //  System.out.println("" + index + " " + unit.getClass().getSimpleName());
            UnitState state = new UnitState(index, getVisitableTimes(unit));
            stateMap.put(unit, state);
            unitList[index++] = unit;
        }
    }

    private int getVisitableTimes(Unit unit) {
        return (unit instanceof JReturnVoidStmt || unit instanceof JReturnStmt) ? 1 : unitGraph.getSuccsOf(unit).size();
    }

    private void dfs(UnitGraph graph, Unit node) {
        dfsUnits.add(node);
        stateMap.get(node).decreaseVisitTimes();
        if (node instanceof JReturnVoidStmt) {
            printPath();
            return;
        }
        List<Unit> children = graph.getSuccsOf(node);
        for (Unit unit : children) {
            UnitState unitState = stateMap.get(unit);
            if (unitState.isVisitable()) {
                dfs(graph, unit);
                unitState.increaseVisitTimes();
            }
        }
    }

    private void printPath() {
        if (dfsUnits.isEmpty() || !(dfsUnits.get(0) instanceof JIdentityStmt)) return;
        System.out.print("Path: ");
        for (Unit unit : dfsUnits) {
            System.out.print("" + (stateMap.get(unit).getIndex() + 1) + " ");
        }
        System.out.println();

        int index = dfsUnits.size() - 1;
        while (index >= 0 && !stateMap.get(dfsUnits.get(index)).isVisitable()) {
            dfsUnits.remove(index);
            index--;
        }
        if (index >= 0) stateMap.get(dfsUnits.get(index)).decreaseVisitTimes();
    }

    private void deleteBranch() {
    }

    public void getPaths(JimpleBody body) {
        UnitPatchingChain units = body.getUnits();

    }
}
