package cn.yang.learning.notes.note2025.链表;

import lombok.Data;

@Data
public class ListNode {
    int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
}
