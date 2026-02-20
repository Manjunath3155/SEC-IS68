import java.util.*;

class BitmaskDPAssignment {

    int[][] cost;
    int n;
    int[] dp;

    public int minCost(int[][] cost) {
        this.cost = cost;
        this.n = cost.length;
        int size = 1 << n;
        dp = new int[size];
        Arrays.fill(dp, -1);
        return solve(0, 0);
    }

    private int solve(int worker, int mask) {
        if (worker == n) return 0;
        if (dp[mask] != -1) return dp[mask];

        int ans = Integer.MAX_VALUE;

        for (int task = 0; task < n; task++) {
            if ((mask & (1 << task)) == 0) {
                ans = Math.min(
                    ans,
                    cost[worker][task] + solve(worker + 1, mask | (1 << task))
                );
            }
        }
        return dp[mask] = ans;
    }

    public static void main(String[] args) {
        int[][] cost = { { 9, 2, 7 }, { 6, 4, 3 }, { 5, 8, 1 } };
        BitmaskDPAssignment obj = new BitmaskDPAssignment();
        System.out.println(obj.minCost(cost));
    }
}
