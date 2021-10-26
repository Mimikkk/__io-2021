package pl.poznan.put.cs.io.errors.processors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Processor implementing the BFS graph traversal from a given adjacency matrix.
 */
public class BFSProcessor {
    /**
     * @param matrix Input adjacency matrix.
     * @return a list of nodes in the BFS traversal order.
     */
    public List<Integer> Traverse(int[][] matrix) {
        this.Matrix = matrix;
        NodeOrder.clear();
        Visited.clear();

        TraverseGraph();

        return NodeOrder;
    }

    /**
     * Traverses graph in a BFS fashion.
     */
    private void TraverseGraph() {
        Integer node;
        while ((node = FindNextUnvisitedNode()) != null) TraverseNode(node);
    }

    /**
     * Traverses the node in the BFS fashion.
     *
     * @param node - node to traverse.
     */
    private void TraverseNode(int node) {
        Visited.add(node);
        NodeOrder.add(node);

        List<Integer> allUnvisitedNeighbors = FindAllUnvisitedNodeNeighbors(node);
        Queue.addAll(allUnvisitedNeighbors);
        Visited.addAll(allUnvisitedNeighbors);

        if (Queue.isEmpty()) return;
        TraverseNode(Queue.remove());
    }

    /**
     * @return a next unvisited node of the graph or a null value if no unvisited node is found.
     */
    private @Nullable Integer FindNextUnvisitedNode() {
        return NodeIterator().filter(this::IsUnvisited).findFirst().orElse(null);
    }

    /**
     * @param node - given node to check.
     * @return a list of node's neighbours.
     */
    private @NotNull List<Integer> FindAllUnvisitedNodeNeighbors(int node) {
        Function<Integer, Boolean> IsNodeNeighbour = i -> IsNeighbour(node, i);

        return NodeIterator()
                .filter(IsNodeNeighbour::apply)
                .filter(this::IsUnvisited)
                .collect(Collectors.toList());
    }

    /**
     * @return an iterator through nodes of a graph.
     */
    private @NotNull Stream<Integer> NodeIterator() {
        return IntStream.range(0, Matrix.length).boxed();
    }

    /**
     * Checks whether a node is a neighbour.
     *
     * @param node   - node to check.
     * @param second - potential neighbour.
     * @return Boolean.
     */
    private boolean IsNeighbour(int node, int second) {
        return Matrix[node][second] == 1;
    }

    /**
     * Checks whether a node is unvisited.
     *
     * @param node - node to check.
     * @return Boolean.
     */
    private boolean IsUnvisited(int node) {
        return !Visited.contains(node);
    }

    private final @NotNull Set<Integer> Visited = new HashSet<>();
    private final @NotNull Queue<Integer> Queue = new LinkedList<>();
    private final @NotNull List<Integer> NodeOrder = new ArrayList<>();
    private int[][] Matrix;
}
