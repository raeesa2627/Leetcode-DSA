/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int firstIdx = -1;   // index of first critical point
        int prevIdx = -1;    // index of previous critical point
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        ListNode prev = head;
        ListNode curr = head.next;
        int idx = 1; // index of curr

        while (curr.next != null) {
            ListNode next = curr.next;

            boolean isMaxima = curr.val > prev.val && curr.val > next.val;
            boolean isMinima = curr.val < prev.val && curr.val < next.val;

            if (isMaxima || isMinima) {
                if (firstIdx == -1) {
                    firstIdx = idx;
                } else {
                    minDistance = Math.min(minDistance, idx - prevIdx);
                    maxDistance = idx - firstIdx;
                }
                prevIdx = idx;
            }

            prev = curr;
            curr = next;
            idx++;
        }

        if (firstIdx == -1 || prevIdx == firstIdx) {
            // fewer than 2 critical points found
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, maxDistance};
    }
}