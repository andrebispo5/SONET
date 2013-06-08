package pt.ist.sonet.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class Url_Base extends pt.ist.sonet.domain.Publication {
    
    
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  Url_Base() {
        super();
    }
    
    public java.lang.String getUrl() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "url");
        return ((DO_State)this.get$obj$state(false)).url;
    }
    
    public void setUrl(java.lang.String url) {
        ((DO_State)this.get$obj$state(true)).url = url;
    }
    
    private java.lang.String get$url() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).url;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$url(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).url = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.Integer getPrice() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "price");
        return ((DO_State)this.get$obj$state(false)).price;
    }
    
    public void setPrice(java.lang.Integer price) {
        ((DO_State)this.get$obj$state(true)).price = price;
    }
    
    private java.lang.Integer get$price() {
        java.lang.Integer value = ((DO_State)this.get$obj$state(false)).price;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForInteger(value);
    }
    
    private final void set$price(java.lang.Integer arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).price = (java.lang.Integer)((arg0 == null) ? null : arg0);
    }
    
    protected void checkDisconnected() {
        super.checkDisconnected();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        super.readStateFromResultSet(rs, state);
        DO_State castedState = (DO_State)state;
        set$url(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "URL"), state);
        set$price(pt.ist.fenixframework.pstm.ResultSetReader.readInteger(rs, "PRICE"), state);
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
        private java.lang.String url;
        private java.lang.Integer price;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.url = this.url;
            newCasted.price = this.price;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.sonet.domain.Publication.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private java.lang.String url;
            private java.lang.Integer price;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.url = obj.url;
                this.price = obj.price;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.url = this.url;
                state.price = this.price;
                
            }
            
        }
        
    }
    
}
