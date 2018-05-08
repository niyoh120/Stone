package stone.ast;

public class EvalVisitor {
    private Object result;

    public void visit(NumberLiteral num){
        result = num.value();
    }
    public void visit(BinaryExpr expr) throws Exception{
        String op = expr.operator();
        expr.left().accept(this);
        int left = (int)(result);
        expr.right().accept(this);
        int right = (int)result;
        if("+".equals(op)){
            result = left+right;
        }else if("-".equals(op)){
            result = left-right;
        }else if("*".equals(op)){
            result = left*right;
        }else
            throw new Exception("bad operator "+ op);
    }
}
