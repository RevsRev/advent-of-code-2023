package rev.aoc.util;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class TreeNode<T>
{
    private final TreeNode parent;

    @Getter
    private final T data;
    private final HashSet<TreeNode> children = new HashSet<>();

    public TreeNode(TreeNode parent, T data)
    {
        this.parent = parent;
        this.data = data;
    }

    public TreeNode addChild(T childData) {
        TreeNode<T> child = new TreeNode(this, childData);
        children.add(child);
        return child;
    }

    public boolean branchContains(T data) {
        TreeNode<T> node = this;
        while (node != null) {
            T nodeData = node.data;
            if (nodeData == null) {
                if (data == null) {
                    return true;
                }
            } else {
                if (nodeData.equals(data)) {
                    return true;
                }
            }
            node = node.parent;
        }
        return false;
    }

    public Set<T> getAllDataOnBranch()
    {
        Set<T> retval = new HashSet<>();
        TreeNode<T> node = this;
        while (node!=null) {
            retval.add(node.getData());
            node = node.parent;
        }
        return retval;
    }
}
