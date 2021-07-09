package ListNodeTest;

/**
 * @author KIKOFranklin
 * @create 2021/4/18 0018 12:57
 * @apiNote 链表反转 定义两个变量
 *          next:当前节点指向的下一个元素
 *          prev:当前节点指向的上一个元素
 *          挨个处理节点 先把当前节点curr的next保存至next变量
 *          再将当前节点curr指向prev
 *          将当前节点curr赋值给prev
 *          再将next赋值给curr
 */
public class ListNodeTest {

    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reserveList(ListNode head){
        ListNode next, prev = null;
        ListNode curr = head;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode listNode5 = new ListNode(5,null);
        ListNode listNode4 = new ListNode(4,listNode5);
        ListNode listNode3 = new ListNode(3,listNode4);
        ListNode listNode2 = new ListNode(2,listNode3);
        ListNode listNode1 = new ListNode(1,listNode2);

        ListNode listNode = reserveList(listNode1);
        System.out.println(listNode);
    }
}
