import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class bj1991X {

    static class Node {
        public char value;
        Node left;
        Node right;

        public Node(char value){
            this.value = value;
        }
    }

    static Map<Character, Node> tree = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i=0; i<n; i++){
            char parent = sc.next().charAt(0);
            char left = sc.next().charAt(0);
            char right = sc.next().charAt(0);

            tree.putIfAbsent(parent, new Node(parent));

            Node cur = tree.get(parent);

            if (left != '.') {
                tree.putIfAbsent(left, new Node(left));
                cur.left = tree.get(left);
            }

            if (right != '.') {
                tree.putIfAbsent(right, new Node(right));
                cur.right = tree.get(right);
            }
        }

        Node root = tree.get('A');

        preorder(root);
        System.out.println();
        inorder(root);
        System.out.println();
        postorder(root);
        System.out.println();
    }

    static void preorder(Node node){
        if(node == null) return;
        System.out.print(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node){
        if(node == null) return;
        inorder(node.left);
        System.out.print(node.value);
        inorder(node.right);
    }

    static void postorder(Node node){
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value);
    }
}
// 내부 클래스로 노드 만들어서 처음 해 본다 + 재귀 / 트리 순회 개념 - 전위 중위 후위
// tree를 맵으로 구성할 생각,,

// for IO성능 - 입력 : br, isr, si + IOException메서드 / 출력 sb
// 내부 클래스와 일반 클래스 차이
// util의 자료구조들과 메서드들 : Map 쓰는 법도 putIfAbsent등 메서드도 모름
