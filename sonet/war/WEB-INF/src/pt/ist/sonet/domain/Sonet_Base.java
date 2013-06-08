package pt.ist.sonet.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class Sonet_Base extends pt.ist.fenixframework.pstm.OneBoxDomainObject {
    public final static dml.runtime.RoleMany<pt.ist.sonet.domain.Sonet,pt.ist.sonet.domain.Agent> role$$agent = new dml.runtime.RoleMany<pt.ist.sonet.domain.Sonet,pt.ist.sonet.domain.Agent>() {
        public dml.runtime.RelationBaseSet<pt.ist.sonet.domain.Agent> getSet(pt.ist.sonet.domain.Sonet o1) {
            return ((Sonet_Base)o1).get$rl$agent();
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Sonet> getInverseRole() {
            return pt.ist.sonet.domain.Agent.role$$sonet;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.sonet.domain.Sonet,pt.ist.sonet.domain.Publication> role$$publication = new dml.runtime.RoleMany<pt.ist.sonet.domain.Sonet,pt.ist.sonet.domain.Publication>() {
        public dml.runtime.RelationBaseSet<pt.ist.sonet.domain.Publication> getSet(pt.ist.sonet.domain.Sonet o1) {
            return ((Sonet_Base)o1).get$rl$publication();
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Sonet> getInverseRole() {
            return pt.ist.sonet.domain.Publication.role$$sonet;
        }
        
    };
    public static dml.runtime.Relation<pt.ist.sonet.domain.Sonet,pt.ist.sonet.domain.Agent> SonetContainsAgents;
    public static dml.runtime.Relation<pt.ist.sonet.domain.Sonet,pt.ist.sonet.domain.Publication> SonetContainsPublications;
    
    
    private RelationList<pt.ist.sonet.domain.Sonet,pt.ist.sonet.domain.Agent> get$rl$agent() {
        return get$$relationList("agent", SonetContainsAgents);
        
    }
    private RelationList<pt.ist.sonet.domain.Sonet,pt.ist.sonet.domain.Publication> get$rl$publication() {
        return get$$relationList("publication", SonetContainsPublications);
        
    }
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  Sonet_Base() {
        super();
    }
    
    public java.lang.Integer getFriendLimit() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "friendLimit");
        return ((DO_State)this.get$obj$state(false)).friendLimit;
    }
    
    public void setFriendLimit(java.lang.Integer friendLimit) {
        ((DO_State)this.get$obj$state(true)).friendLimit = friendLimit;
    }
    
    private java.lang.Integer get$friendLimit() {
        java.lang.Integer value = ((DO_State)this.get$obj$state(false)).friendLimit;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForInteger(value);
    }
    
    private final void set$friendLimit(java.lang.Integer arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).friendLimit = (java.lang.Integer)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.Integer getPolemicPubLimit() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "polemicPubLimit");
        return ((DO_State)this.get$obj$state(false)).polemicPubLimit;
    }
    
    public void setPolemicPubLimit(java.lang.Integer polemicPubLimit) {
        ((DO_State)this.get$obj$state(true)).polemicPubLimit = polemicPubLimit;
    }
    
    private java.lang.Integer get$polemicPubLimit() {
        java.lang.Integer value = ((DO_State)this.get$obj$state(false)).polemicPubLimit;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForInteger(value);
    }
    
    private final void set$polemicPubLimit(java.lang.Integer arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).polemicPubLimit = (java.lang.Integer)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.Integer getIdCounter() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "idCounter");
        return ((DO_State)this.get$obj$state(false)).idCounter;
    }
    
    public void setIdCounter(java.lang.Integer idCounter) {
        ((DO_State)this.get$obj$state(true)).idCounter = idCounter;
    }
    
    private java.lang.Integer get$idCounter() {
        java.lang.Integer value = ((DO_State)this.get$obj$state(false)).idCounter;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForInteger(value);
    }
    
    private final void set$idCounter(java.lang.Integer arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).idCounter = (java.lang.Integer)((arg0 == null) ? null : arg0);
    }
    
    public int getAgentCount() {
        return get$rl$agent().size();
    }
    
    public boolean hasAnyAgent() {
        return (! get$rl$agent().isEmpty());
    }
    
    public boolean hasAgent(pt.ist.sonet.domain.Agent agent) {
        return get$rl$agent().contains(agent);
    }
    
    public java.util.Set<pt.ist.sonet.domain.Agent> getAgentSet() {
        return get$rl$agent();
    }
    
    public void addAgent(pt.ist.sonet.domain.Agent agent) {
        SonetContainsAgents.add((pt.ist.sonet.domain.Sonet)this, agent);
    }
    
    public void removeAgent(pt.ist.sonet.domain.Agent agent) {
        SonetContainsAgents.remove((pt.ist.sonet.domain.Sonet)this, agent);
    }
    
    public java.util.List<pt.ist.sonet.domain.Agent> getAgent() {
        return get$rl$agent();
    }
    
    public void set$agent(OJBFunctionalSetWrapper agent) {
        get$rl$agent().setFromOJB(this, "agent", agent);
    }
    
    public java.util.Iterator<pt.ist.sonet.domain.Agent> getAgentIterator() {
        return get$rl$agent().iterator();
    }
    
    public int getPublicationCount() {
        return get$rl$publication().size();
    }
    
    public boolean hasAnyPublication() {
        return (! get$rl$publication().isEmpty());
    }
    
    public boolean hasPublication(pt.ist.sonet.domain.Publication publication) {
        return get$rl$publication().contains(publication);
    }
    
    public java.util.Set<pt.ist.sonet.domain.Publication> getPublicationSet() {
        return get$rl$publication();
    }
    
    public void addPublication(pt.ist.sonet.domain.Publication publication) {
        SonetContainsPublications.add((pt.ist.sonet.domain.Sonet)this, publication);
    }
    
    public void removePublication(pt.ist.sonet.domain.Publication publication) {
        SonetContainsPublications.remove((pt.ist.sonet.domain.Sonet)this, publication);
    }
    
    public java.util.List<pt.ist.sonet.domain.Publication> getPublication() {
        return get$rl$publication();
    }
    
    public void set$publication(OJBFunctionalSetWrapper publication) {
        get$rl$publication().setFromOJB(this, "publication", publication);
    }
    
    public java.util.Iterator<pt.ist.sonet.domain.Publication> getPublicationIterator() {
        return get$rl$publication().iterator();
    }
    
    protected void checkDisconnected() {
        if (hasAnyAgent()) handleAttemptToDeleteConnectedObject();
        if (hasAnyPublication()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        DO_State castedState = (DO_State)state;
        set$friendLimit(pt.ist.fenixframework.pstm.ResultSetReader.readInteger(rs, "FRIEND_LIMIT"), state);
        set$polemicPubLimit(pt.ist.fenixframework.pstm.ResultSetReader.readInteger(rs, "POLEMIC_PUB_LIMIT"), state);
        set$idCounter(pt.ist.fenixframework.pstm.ResultSetReader.readInteger(rs, "ID_COUNTER"), state);
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        if (attrName.equals("agent")) return SonetContainsAgents;
        if (attrName.equals("publication")) return SonetContainsPublications;
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        get$$relationList("agent", SonetContainsAgents);
        get$$relationList("publication", SonetContainsPublications);
        
    }
    protected static class DO_State extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State {
        private java.lang.Integer friendLimit;
        private java.lang.Integer polemicPubLimit;
        private java.lang.Integer idCounter;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.friendLimit = this.friendLimit;
            newCasted.polemicPubLimit = this.polemicPubLimit;
            newCasted.idCounter = this.idCounter;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private java.lang.Integer friendLimit;
            private java.lang.Integer polemicPubLimit;
            private java.lang.Integer idCounter;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.friendLimit = obj.friendLimit;
                this.polemicPubLimit = obj.polemicPubLimit;
                this.idCounter = obj.idCounter;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.friendLimit = this.friendLimit;
                state.polemicPubLimit = this.polemicPubLimit;
                state.idCounter = this.idCounter;
                
            }
            
        }
        
    }
    
}
