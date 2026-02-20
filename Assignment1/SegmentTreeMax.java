class SegmentTreeMax {

    int[] tree;
    int n;

    public SegmentTreeMax(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    private void build(int[] arr, int node, int l, int r) {
        if (l == r) {
            tree[node] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        build(arr, 2 * node + 1, l, mid);
        build(arr, 2 * node + 2, mid + 1, r);
        tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
    }

    public void update(int idx, int val) {
        update(0, 0, n - 1, idx, val);
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            tree[node] = val;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(2 * node + 1, l, mid, idx, val);
        else update(2 * node + 2, mid + 1, r, idx, val);
        tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
    }

    public int queryMax(int L, int R) {
        return query(0, 0, n - 1, L, R);
    }

    private int query(int node, int l, int r, int L, int R) {
        if (r < L || l > R) return Integer.MIN_VALUE;
        if (L <= l && r <= R) return tree[node];
        int mid = (l + r) / 2;
        return Math.max(
            query(2 * node + 1, l, mid, L, R),
            query(2 * node + 2, mid + 1, r, L, R)
        );
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9, 11 };
        SegmentTreeMax st = new SegmentTreeMax(arr);
        System.out.println(st.queryMax(1, 4));
        st.update(2, 20);
        System.out.println(st.queryMax(1, 4));
    }
}
