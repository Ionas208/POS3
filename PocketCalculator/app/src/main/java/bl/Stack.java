package bl;

public class Stack {

    public double[] stack = new double[10];

    public int tos = 0;

    public void push(double num){
        if(isFull()){
            throw new RuntimeException("Stack overflow");
        }
        stack[tos] = num;
        tos++;
    }

    public double pop(){
        if(isEmpty()){
            throw new RuntimeException("Stack overflow");
        }
        return stack[--tos];
    }

    private boolean isEmpty(){
        return tos==0;
    }

    private boolean isFull(){
        return tos==stack.length;
    }

    public void clear(){
        tos = 0;
    }
}