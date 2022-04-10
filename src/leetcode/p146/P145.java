package leetcode.p146;

import java.util.HashMap;

public class P145 {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);

        int[][] operations =  new int[][]{ {2, 1}, {1, 1}, {2, 3}, {4, 1}, {1}, {2}};
        test(lruCache, operations);

        operations =new int[][]{{1, 1}, {2, 2}, {1}, {3, 3}, {2}, {4, 4}, {1}, {3}, {4}};

        test(lruCache, operations);

    }

    private static void test(LRUCache lruCache, int[][] operations) {
        for (int[] option : operations) {
            int key = option[0];
            if (option.length == 1) {
                int v = lruCache.get(key);
                System.out.printf("Get %d=%d %s%n", key, v, lruCache);

            } else {
                int v = option[1];
                lruCache.put(key, v);
                System.out.printf("Put %d=%d %s%n", key, v, lruCache);
            }
        }
        System.out.println("-----");
    }

}


// 双链表+哈希表
class LRUCache {
    private final HashMap<Integer, DLinkedNode> map;
    private final DLinkedNode head;
    private final int capacity;
    private int size = 0;
    private DLinkedNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = head;
        map = new HashMap<>((int) ((double) capacity / 0.75));
    }

    /**
     * 这个是为了维护头尾指针而做的添加节点的包装方法
     *
     * @param node 需要添加的节点
     */
    private void append(DLinkedNode node) {
        if (head == tail) {
            tail = node;
        }
        head.appendSingleNode(node);
        ++size;

    }

    /**
     * 为了维护头尾指针而添加的断开链接方法
     *
     * @param node 需要断开的节点
     */
    private void detach(DLinkedNode node) {
        if (node == tail) {
            tail = node.prev;
        }
        node.detach();
        --size;

    }


    public int get(int key) {
        DLinkedNode node = map.get(key);
        if (node == null) {
            return -1;
        }
        // 从原有链中断开
        detach(node);
        // 加到头上
        append(node);

        return node.value;

    }

    public void put(int key, int value) {
        DLinkedNode node = map.get(key);
        if (node == null) {
            // 节点不存在,考虑插入
            //  达到最大容量, 复用尾部节点
            if (size >= capacity) {
                node = tail;
                detach(node);
                // 清除原来的旧key
                map.put(node.key, null);
            } else {
                // 容量还够 直接添加新node
                node = new DLinkedNode();
            }
        }else{
            detach(node);
        }
        node.key = key;
        node.value = value;
        map.put(key, node);
        append(node);
    }

    @Override
    public String toString() {
        if (head.next == null) {
            return "[]";
        }
        return this.head.next.toString();
    }

    /**
     * 双链表
     */
    static class DLinkedNode {
        int key;
        int value;
        DLinkedNode next;
        DLinkedNode prev;

        public DLinkedNode() {
        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        /**
         * 从链表中断开
         *
         * @return 节点自身
         */
        DLinkedNode detach() {
            if (next != null)
                next.prev = prev;
            if (prev != null)
                prev.next = next;

            return this;
        }

        /**
         * 头插法加入节点, 后加入的永远在前面
         * 只添加一个节点,不管添加的节点后面有没有next
         *
         * @param newNode 没有前驱后继的节点
         * @return 加入的节点
         */
        DLinkedNode appendSingleNode(DLinkedNode newNode) {
            if (newNode == null) {
                return this;
            }
            if (next != null) {
                next.prev = newNode;

            }
            newNode.next = next;
            next = newNode;
            newNode.prev = this;

            return newNode;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            DLinkedNode p = this;
            sb.append('[');

            while (p != null) {
                sb.append(p.key);
                sb.append(':');
                sb.append(p.value);

                sb.append(',');
                p = p.next;

            }
            sb.append(']');

            return sb.toString();
        }
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */