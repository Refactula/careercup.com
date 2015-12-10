import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree print it in inward spiral order i.e first print level 1, then level n, then level 2, then n-1 and so on.
 *
 * For Ex -
 * 1
 * 2	3
 * 4 5 6 7
 * 8 9 10 11 12 13 14 15
 *
 * Print- 1 15 14 13 12 11 10 9 8 2 3 7 6 5 4
 *
 * Follow up question - Extend the algorithm to n-ary tree.
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode tree =
                new TreeNode(1,
                        new TreeNode(2,
                                new TreeNode(4,
                                        new TreeNode(8),
                                        new TreeNode(9)),
                                new TreeNode(5,
                                        new TreeNode(10),
                                        new TreeNode(11))),
                        new TreeNode(3,
                                new TreeNode(6,
                                        new TreeNode(12),
                                        new TreeNode(13)),
                                new TreeNode(7,
                                        new TreeNode(14),
                                        new TreeNode(15))));

        List<Integer> spiral = toSpiral(tree);
        System.out.println(spiral);
    }

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this(value, null, null);
        }

        public TreeNode(int value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> toSpiral(TreeNode n) {
        List<List<TreeNode>> layers = getLayers(n);
        List<Integer> result = new ArrayList<>();

        int i = 0;
        int j = layers.size() - 1;

        while (i <= j) {
            List<TreeNode> layer = layers.get(i);
            for (int k = 0; k < layer.size(); k++) {
                result.add(layer.get(k).value);
            }
            if (j > i) {
                layer = layers.get(j);
                for (int k = layer.size() - 1; k >= 0; k--) {
                    result.add(layer.get(k).value);
                }
            }
            i++;
            j--;
        }

        return result;
    }

    private static List<List<TreeNode>> getLayers(TreeNode n) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(n);
        queue.add(null);

        List<List<TreeNode>> result = new ArrayList<>();
        List<TreeNode> buffer = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode enqueued = queue.poll();
            if (enqueued != null) {
                buffer.add(enqueued);
                if (enqueued.left != null) {
                    queue.add(enqueued.left);
                }
                if (enqueued.right != null) {
                    queue.add(enqueued.right);
                }
            } else {
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
                result.add(buffer);
                buffer = new ArrayList<>();
            }
        }

        return result;
    }
}
