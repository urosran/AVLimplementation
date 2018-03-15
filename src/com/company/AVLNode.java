/**
 * Uros Randelovic
 * urosr@brandeis.edu
 */
package com.company;


public class AVLNode<T> {
    private T data;
    private int value;
    private AVLNode<T> parent;
    private AVLNode<T> leftChild;
    private AVLNode<T> rightChild;
    private int rightWeight;
    private int balanceFactor;
    private int visina;

    /**
     * Constructor O1
     * @param data
     * @param value
     */
    public AVLNode(T data, int value) {
        this.data = data;
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
        this.visina = 1;
    }

    /**
     * Height - O(1)
     * @param koren
     * @return
     */
    private int visina(AVLNode koren) {
        if (koren == null) {
            return 0;
        } else {
            return koren.getVisina();
        }
    }

    /**
     * Left Rotation - O(n)
     * @param koren
     * @return
     */
    private AVLNode levaRotacija(AVLNode koren) {
        System.out.println("leva rotacija (" + koren.getValue() + ")");
        AVLNode x = koren;
        AVLNode y = koren.getRightChild();
        AVLNode T2 = y.getLeftChild();

        x.setRightChild(T2);
        y.setLeftChild(x);

        x.setVisina(Math.max(visina(x.getLeftChild()), visina(x.getRightChild())) + 1);
        y.setVisina(Math.max(visina(y.getLeftChild()), visina(y.getRightChild())) + 1);

        return y;
    }

    /**
     * Right Rotation - O(n)
     * @param koren
     * @return
     */

    private AVLNode desnaRotacija(AVLNode koren) {
        System.out.println("desna rotacija (" + koren.getValue() + ")");
        AVLNode y = koren;
        AVLNode x = koren.getLeftChild();
        AVLNode T2 = x.getRightChild();
        //rotating
        y.setLeftChild(T2);
        x.setRightChild(y);
        //update height
        y.setVisina(Math.max(visina(y.getLeftChild()), visina(y.getRightChild())) + 1);
        x.setVisina(Math.max(visina(x.getLeftChild()), visina(x.getRightChild())) + 1);
        //new root
        return x;
    }

    /**
     * Insert O(1)
     * @param koren
     * @param n
     * @param newData
     * @return
     */
    public AVLNode dodaj(AVLNode koren, int n, T newData) {
        //add if null
        if (koren == null) {
            AVLNode novi = new AVLNode(newData, n);
            return novi;
        }
        //choose which child to add
        if (n < koren.getValue()) {
            koren.setLeftChild(dodaj(koren.getLeftChild(), n, newData));
        } else if (n > koren.getValue()) {
            koren.setRightChild(dodaj(koren.getRightChild(), n, newData));
        } else {
            return koren;
        }
        //set height
        koren.setVisina(Math.max(visina(koren.getLeftChild()), visina(koren.getRightChild())) + 1);
        //calculate balance
        int balansiranost = visina(koren.getLeftChild()) - visina(koren.getRightChild());
        //balance the tree
        // LL
        if (balansiranost > 1 && n < koren.getLeftChild().getValue())
            return desnaRotacija(koren);
        // RR
        if (balansiranost < -1 && n > koren.getRightChild().getValue())
            return levaRotacija(koren);

        // LR
        if (balansiranost > 1 && n > koren.getLeftChild().getValue()) {
            koren.setLeftChild(levaRotacija(koren.getLeftChild()));
            return desnaRotacija(koren);
        }

        // RL
        if (balansiranost < -1 && n < koren.getRightChild().getValue()) {
            koren.setRightChild(desnaRotacija(koren.getRightChild()));
            return levaRotacija(koren);
        }
        return koren;
    }

    /**
     * Removal - O(1)
     * @param koren
     * @param n
     * @return
     */
    public AVLNode ukloni(AVLNode koren, int n) {
        //base case
        if (koren == null) {
            return koren;
        }
        //recursive case
        if (n < koren.getValue()) {
            koren.setLeftChild(ukloni(koren.getLeftChild(), n));
        } else if (n > koren.getValue()) {
            koren.setRightChild(ukloni(koren.getRightChild(), n));
        } else {
            if (koren.getLeftChild() == null &&
                    koren.getRightChild() == null) {
                koren = null;
            } else if (koren.getLeftChild() == null) {
                AVLNode tmp = koren.getRightChild();
                koren = tmp;
            } else if (koren.getRightChild() == null) {
                AVLNode tmp = koren.getLeftChild();
                koren = tmp;
            } else {
                int br = najmanji(koren.getRightChild());
                koren.setValue(br);
                koren.setRightChild(ukloni(koren.getRightChild(), br));
            }
        }
        if (koren == null) {
            return koren;
        }
        //set new height
        koren.setVisina(Math.max(visina(koren.getLeftChild()), visina(koren.getRightChild())) + 1);
        //calculate balance factor
        int balansiranost = visina(koren.getLeftChild()) - visina(koren.getRightChild());

        //balance the tree
        // LL
        if (balansiranost > 1 && (visina(koren.getLeftChild().getLeftChild()) - visina(koren.getLeftChild().getRightChild()) >= 0)) //
            return desnaRotacija(koren);
        // RR
        if (balansiranost < -1 && (visina(koren.getRightChild().getLeftChild()) - visina(koren.getRightChild().getRightChild()) <= 0))
            return levaRotacija(koren);

        // LR
        if (balansiranost > 1 && (visina(koren.getLeftChild().getLeftChild()) - visina(koren.getLeftChild().getRightChild()) < 0)) {
            koren.setLeftChild(levaRotacija(koren.getLeftChild()));
            return desnaRotacija(koren);
        }

        // RL
        if (balansiranost < -1 && (visina(koren.getRightChild().getLeftChild()) - visina(koren.getRightChild().getRightChild()) > 0)) {
            koren.setRightChild(desnaRotacija(koren.getRightChild()));
            return levaRotacija(koren);
        }

        return koren;
    }

    /**
     * Smallest node in the tree O(height)
     * @param koren
     * @return
     */
    private int najmanji(AVLNode koren) {
        if (koren.getLeftChild() == null) {
            return koren.getValue();
        }
        return najmanji((koren.getLeftChild()));
    }

    /**
     * inOrder Traversal O(height)
     * @param koren
     */
    void LKD(AVLNode koren) {
        if (koren != null) {
            LKD(koren.getLeftChild());
            System.out.println(koren.getValue());
            LKD(koren.getRightChild());
        }
    }

    /**
     * String representation of the tree O(n)
     * @return
     */
    public String toString() {
        String result = "";
        String leftBranch = "";
        String rightBranch ="";
        if (leftChild != null) {
            leftBranch += this.getLeftChild().toString();
        }
        result+=leftBranch+data;
        if (rightChild != null) {
            rightBranch +=this.getRightChild().toString();
        }
        result+=rightBranch;
        return "("+result+")";
    }

    /**
     * Getters and setters below, all O(1)
     * @return
     */
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public AVLNode<T> getParent() {
        return parent;
    }

    public void setParent(AVLNode<T> parent) {
        this.parent = parent;
    }

    public AVLNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(AVLNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public AVLNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(AVLNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public int getRightWeight() {
        return rightWeight;
    }

    public void setRightWeight(int rightWeight) {
        this.rightWeight = rightWeight;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }


    public int getVisina() {
        return visina;
    }

    public void setVisina(int visina) {
        this.visina = visina;
    }
}