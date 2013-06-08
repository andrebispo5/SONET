package pt.ist.sonet.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class Note_Base extends pt.ist.sonet.domain.Publication {
    
    
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  Note_Base() {
        super();
    }
    
    public java.lang.String getText() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "text");
        return ((DO_State)this.get$obj$state(false)).text;
    }
    
    public void setText(java.lang.String text) {
        ((DO_State)this.get$obj$state(true)).text = text;
    }
    
    private java.lang.String get$text() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).text;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$text(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).text = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    protected void checkDisconnected() {
        super.checkDisconnected();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        super.readStateFromResultSet(rs, state);
        DO_State castedState = (DO_State)state;
        set$text(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "TEXT"), state);
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        
    }
    protected static class DO_State extends pt.ist.sonet.domain.Publication.DO_State {
        private java.lang.String text;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.text = this.text;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.sonet.domain.Publication.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private java.lang.String text;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.text = obj.text;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.text = this.text;
                
            }
            
        }
        
    }
    
}
