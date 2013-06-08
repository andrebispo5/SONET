package pt.ist.sonet.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class Comment_Base extends pt.ist.fenixframework.pstm.OneBoxDomainObject {
    public final static pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Comment,pt.ist.sonet.domain.Publication> role$$publication = new pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Comment,pt.ist.sonet.domain.Publication>() {
        public pt.ist.sonet.domain.Publication getValue(pt.ist.sonet.domain.Comment o1) {
            return ((Comment_Base.DO_State)o1.get$obj$state(false)).publication;
        }
        public void setValue(pt.ist.sonet.domain.Comment o1, pt.ist.sonet.domain.Publication o2) {
            ((Comment_Base.DO_State)o1.get$obj$state(true)).publication = o2;
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Comment> getInverseRole() {
            return pt.ist.sonet.domain.Publication.role$$comment;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Comment,pt.ist.sonet.domain.Publication> PublicationsContainsComments = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Comment,pt.ist.sonet.domain.Publication>(role$$publication);
    static {
        pt.ist.sonet.domain.Publication.PublicationsContainsComments = PublicationsContainsComments.getInverseRelation();
    }
    
    static {
        PublicationsContainsComments.setRelationName("pt.ist.sonet.domain.Comment.PublicationsContainsComments");
    }
    
    
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  Comment_Base() {
        super();
    }
    
    public java.lang.Integer getId() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "id");
        return ((DO_State)this.get$obj$state(false)).id;
    }
    
    public void setId(java.lang.Integer id) {
        ((DO_State)this.get$obj$state(true)).id = id;
    }
    
    private java.lang.Integer get$id() {
        java.lang.Integer value = ((DO_State)this.get$obj$state(false)).id;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForInteger(value);
    }
    
    private final void set$id(java.lang.Integer arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).id = (java.lang.Integer)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.String getAgentName() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "agentName");
        return ((DO_State)this.get$obj$state(false)).agentName;
    }
    
    public void setAgentName(java.lang.String agentName) {
        ((DO_State)this.get$obj$state(true)).agentName = agentName;
    }
    
    private java.lang.String get$agentName() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).agentName;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$agentName(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).agentName = (java.lang.String)((arg0 == null) ? null : arg0);
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
    
    public pt.ist.sonet.domain.Publication getPublication() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "publication");
        return ((DO_State)this.get$obj$state(false)).publication;
    }
    
    public void setPublication(pt.ist.sonet.domain.Publication publication) {
        PublicationsContainsComments.add((pt.ist.sonet.domain.Comment)this, publication);
    }
    
    public boolean hasPublication() {
        return (getPublication() != null);
    }
    
    public void removePublication() {
        setPublication(null);
    }
    
    private java.lang.Long get$oidPublication() {
        pt.ist.fenixframework.pstm.AbstractDomainObject value = ((DO_State)this.get$obj$state(false)).publication;
        return (value == null) ? null : value.getOid();
    }
    
    protected void checkDisconnected() {
        if (hasPublication()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        DO_State castedState = (DO_State)state;
        set$id(pt.ist.fenixframework.pstm.ResultSetReader.readInteger(rs, "ID"), state);
        set$agentName(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "AGENT_NAME"), state);
        set$text(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "TEXT"), state);
        castedState.publication = pt.ist.fenixframework.pstm.ResultSetReader.readDomainObject(rs, "OID_PUBLICATION");
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
    protected static class DO_State extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State {
        private java.lang.Integer id;
        private java.lang.String agentName;
        private java.lang.String text;
        private pt.ist.sonet.domain.Publication publication;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.id = this.id;
            newCasted.agentName = this.agentName;
            newCasted.text = this.text;
            newCasted.publication = this.publication;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private java.lang.Integer id;
            private java.lang.String agentName;
            private java.lang.String text;
            private pt.ist.sonet.domain.Publication publication;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.id = obj.id;
                this.agentName = obj.agentName;
                this.text = obj.text;
                this.publication = obj.publication;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.id = this.id;
                state.agentName = this.agentName;
                state.text = this.text;
                state.publication = this.publication;
                
            }
            
        }
        
    }
    
}
