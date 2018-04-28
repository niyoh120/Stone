package stone.ast;

import java.util.Iterator;
import java.util.List;

public class ASTList extends ASTree{
    protected List<ASTree> children;
    public ASTList(List<ASTree> list){
        children = list;
    }

    @Override
    public ASTree child(int i) {
        return children.get(i);
    }

    @Override
    public int numChild() {
        return children.size();
    }

    @Override
    public String location() {
        for(ASTree t:children){
            String s = t.location();
            if (s!=null){
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        String sep = "";
        for(ASTree t:children){
            sb.append(sep);
            sep = " ";
            sb.append(t.toString());
        }
        return sb.append(')').toString();
    }

    @Override
    public Iterator<ASTree> children() {
        return children.iterator();
    }
}
