package rev.aoc.days.d16;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;
import rev.aoc.math.vec.Vec2;
import rev.aoc.util.TreeNode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FloorLava extends AocSolution<Long>
{
    public static final Vec2 UP = new Vec2(0,-1); //top of array is y=0, increasing coordinates as we go down.
    public static final Vec2 DOWN = new Vec2(0,1);
    public static final Vec2 LEFT = new Vec2(-1,0);
    public static final Vec2 RIGHT = new Vec2(1,0);

    public FloorLava(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        char[][] mirrorContraption = parse(lines);

        int height = mirrorContraption.length;
        int width = mirrorContraption[0].length;

        Set<TreeNode<Pair<Vec2,Vec2>>> branches = getInitialBranches(mirrorContraption);
        Set<TreeNode<Pair<Vec2,Vec2>>> leaves = new HashSet<>();

        while (!branches.isEmpty()) {
            Set<TreeNode<Pair<Vec2,Vec2>>> nextBranches = new HashSet<>();
            Iterator<TreeNode<Pair<Vec2,Vec2>>> it = branches.iterator();
            while (it.hasNext()) {
                TreeNode<Pair<Vec2,Vec2>> node = it.next();
                Vec2 coord = node.getData().getLeft();
                Vec2 direction = node.getData().getRight();
                Set<Vec2> nextDirections = getOutputForInput(mirrorContraption[(int)coord.y][(int)coord.x], direction);
                Iterator<Vec2> itNext = nextDirections.iterator();
                boolean continuedBranch = false;
                while (itNext.hasNext()) {
                    Vec2 nextDir = itNext.next();
                    Vec2 nCoord = coord.add(nextDir);
                    if (!(nCoord.x<0 || nCoord.x>=width || nCoord.y<0 || nCoord.y>=height) && !node.branchContains(Pair.of(nCoord,nextDir))) {
                        nextBranches.add(node.addChild(Pair.of(nCoord,nextDir)));
                        continuedBranch = true;
                    }
                }
                if (!continuedBranch) {
                    leaves.add(node);
                }
            }
            branches = nextBranches;
        }

        return (long)calculateEnergy(leaves);
    }

    private int calculateEnergy(Set<TreeNode<Pair<Vec2, Vec2>>> leaves)
    {
        Set<Vec2> energisedNodes = new HashSet<>();
        Iterator<TreeNode<Pair<Vec2,Vec2>>> it = leaves.iterator();
        while (it.hasNext()) {
            TreeNode<Pair<Vec2,Vec2>> leaf = it.next();
            Set<Vec2> nodesOnBranch = getCoordsOnBranch(leaf);
            energisedNodes.addAll(nodesOnBranch);

        }
        return energisedNodes.size();
    }

    private Set<Vec2> getCoordsOnBranch(TreeNode<Pair<Vec2, Vec2>> leaf)
    {
        Set<Vec2> nodeCoords = new HashSet<>();

        Set<Pair<Vec2,Vec2>> nodeData = leaf.getAllDataOnBranch();
        Iterator<Pair<Vec2,Vec2>> it = nodeData.iterator();
        while (it.hasNext()) {
            nodeCoords.add(it.next().getLeft());
        }
        return nodeCoords;
    }

    private Set<TreeNode<Pair<Vec2,Vec2>>> getInitialBranches(char[][] mirrorContraption)
    {
        char startChar = mirrorContraption[0][0];
        Set<Vec2> initialDirections = getOutputForInput(startChar, RIGHT);

        Set<TreeNode<Pair<Vec2,Vec2>>> branches = new HashSet<>();
        Iterator<Vec2> it = initialDirections.iterator();
        while (it.hasNext()) {
            branches.add(new TreeNode<>(null, Pair.of(new Vec2(0,0), it.next())));
        }
        return branches;
    }

    private Set<Vec2> getOutputForInput(char c, Vec2 input)
    {
        if (c == '.') {
            return Set.of(input);
        }

        if (c == '|') {
            if (input.equals(UP) || input.equals(DOWN)) {
                return Set.of(input);
            }
            return Set.of(UP,DOWN);
        }

        if (c == '-') {
            if (input.equals(LEFT) || input.equals(RIGHT)) {
                return Set.of(input);
            }
            return Set.of(LEFT,RIGHT);
        }

        if (c == '/') {
            if (input.equals(DOWN)) {
                return Set.of(LEFT);
            }
            if (input.equals(RIGHT)) {
                return Set.of(UP);
            }
            if (input.equals(UP)) {
                return Set.of(RIGHT);
            }
            return Set.of(DOWN);
        }

        //else t == \
        if (input.equals(DOWN)) {
            return Set.of(RIGHT);
        }
        if (input.equals(RIGHT)) {
            return Set.of(DOWN);
        }
        if (input.equals(UP)) {
            return Set.of(LEFT);
        }
        return Set.of(UP);

    }

    private char[][] parse(List<String> lines)
    {
        char[][] contraption = new char[lines.size()][];
        for (int i=0; i<lines.size(); i++) {
            String line = lines.get(i);
            contraption[i] = line.toCharArray();
        }
        return contraption;
    }
}
