package stone.ast;

import java.util.Iterator;

abstract public class ASTree implements Iterable<ASTree> {
    public abstract ASTree child(int i);
    public abstract int numChild();
    public abstract Iterator<ASTree> children();
    public abstract String location();
    public Iterator<ASTree> iterator(){
        return children();
    }
}
