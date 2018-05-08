package stone.ast;

import java.lang.reflect.Method;
import java.util.Iterator;

abstract public class ASTree implements Iterable<ASTree> {
    public abstract ASTree child(int i);
    public abstract int numChild();
    public abstract Iterator<ASTree> children();
    public abstract String location();
    public Iterator<ASTree> iterator(){
        return children();
    }
    public final void accept(Object visitor) throws Exception{
        Method method  = findMethod(visitor,getClass());
        if(null!=method){
            method.invoke(visitor,this);
        }
    }
    public static Method findMethod(Object visitor,Class<?> type){
        if(Object.class == type){
            return null;
        }
        try{
            return visitor.getClass().getMethod("visitor",type);
        }catch (NoSuchMethodException e){
            return findMethod(visitor,type.getSuperclass());
        }
    }

}
