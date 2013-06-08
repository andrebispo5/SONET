package pt.ist.sonet.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class Publication_Base extends pt.ist.fenixframework.pstm.OneBoxDomainObject {
    public final static pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Sonet> role$$sonet = new pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Sonet>() {
        public pt.ist.sonet.domain.Sonet getValue(pt.ist.sonet.domain.Publication o1) {
            return ((Publication_Base.DO_State)o1.get$obj$state(false)).sonet;
        }
        public void setValue(pt.ist.sonet.domain.Publication o1, pt.ist.sonet.domain.Sonet o2) {
            ((Publication_Base.DO_State)o1.get$obj$state(true)).sonet = o2;
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Sonet,pt.ist.sonet.domain.Publication> getInverseRole() {
            return pt.ist.sonet.domain.Sonet.role$$publication;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Agent> role$$agent = new pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Agent>() {
        public pt.ist.sonet.domain.Agent getValue(pt.ist.sonet.domain.Publication o1) {
            return ((Publication_Base.DO_State)o1.get$obj$state(false)).agent;
        }
        public void setValue(pt.ist.sonet.domain.Publication o1, pt.ist.sonet.domain.Agent o2) {
            ((Publication_Base.DO_State)o1.get$obj$state(true)).agent = o2;
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Publication> getInverseRole() {
            return pt.ist.sonet.domain.Agent.role$$publication;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Comment> role$$comment = new dml.runtime.RoleMany<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Comment>() {
        public dml.runtime.RelationBaseSet<pt.ist.sonet.domain.Comment> getSet(pt.ist.sonet.domain.Publication o1) {
            return ((Publication_Base)o1).get$rl$comment();
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Comment,pt.ist.sonet.domain.Publication> getInverseRole() {
            return pt.ist.sonet.domain.Comment.role$$publication;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Agent> role$$voters = new dml.runtime.RoleMany<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Agent>() {
        public dml.runtime.RelationBaseSet<pt.ist.sonet.domain.Agent> getSet(pt.ist.sonet.domain.Publication o1) {
            return ((Publication_Base)o1).get$rl$voters();
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Publication> getInverseRole() {
            return pt.ist.sonet.domain.Agent.role$$pub;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Sonet> SonetContainsPublications = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Sonet>(role$$sonet);
    static {
        pt.ist.sonet.domain.Sonet.SonetContainsPublications = SonetContainsPublications.getInverseRelation();
    }
    
    static {
        SonetContainsPublications.setRelationName("pt.ist.sonet.domain.Publication.SonetContainsPublications");
    }
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Agent> AgentContainsPublications = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Agent>(role$$agent);
    static {
        pt.ist.sonet.domain.Agent.AgentContainsPublications = AgentContainsPublications.getInverseRelation();
    }
    
    static {
        AgentContainsPublications.setRelationName("pt.ist.sonet.domain.Publication.AgentContainsPublications");
    }
    public static dml.runtime.Relation<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Comment> PublicationsContainsComments;
    public static dml.runtime.Relation<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Agent> PublicationsContainsAgent;
    
    
    private RelationList<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Comment> get$rl$comment() {
        return get$$relationList("comment", PublicationsContainsComments);
        
    }
    private RelationList<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Agent> get$rl$voters() {
        return get$$relationList("voters", PublicationsContainsAgent);
        
    }
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  Publication_Base() {
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
    
    public java.lang.String getCaption() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "caption");
        return ((DO_State)this.get$obj$state(false)).caption;
    }
    
    public void setCaption(java.lang.String caption) {
        ((DO_State)this.get$obj$state(true)).caption = caption;
    }
    
    private java.lang.String get$caption() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).caption;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$caption(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).caption = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.Integer getScorePlus() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "scorePlus");
        return ((DO_State)this.get$obj$state(false)).scorePlus;
    }
    
    public void setScorePlus(java.lang.Integer scorePlus) {
        ((DO_State)this.get$obj$state(true)).scorePlus = scorePlus;
    }
    
    private java.lang.Integer get$scorePlus() {
        java.lang.Integer value = ((DO_State)this.get$obj$state(false)).scorePlus;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForInteger(value);
    }
    
    private final void set$scorePlus(java.lang.Integer arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).scorePlus = (java.lang.Integer)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.Integer getScoreMinus() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "scoreMinus");
        return ((DO_State)this.get$obj$state(false)).scoreMinus;
    }
    
    public void setScoreMinus(java.lang.Integer scoreMinus) {
        ((DO_State)this.get$obj$state(true)).scoreMinus = scoreMinus;
    }
    
    private java.lang.Integer get$scoreMinus() {
        java.lang.Integer value = ((DO_State)this.get$obj$state(false)).scoreMinus;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForInteger(value);
    }
    
    private final void set$scoreMinus(java.lang.Integer arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).scoreMinus = (java.lang.Integer)((arg0 == null) ? null : arg0);
    }
    
    public pt.ist.sonet.domain.Sonet getSonet() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "sonet");
        return ((DO_State)this.get$obj$state(false)).sonet;
    }
    
    public void setSonet(pt.ist.sonet.domain.Sonet sonet) {
        SonetContainsPublications.add((pt.ist.sonet.domain.Publication)this, sonet);
    }
    
    public boolean hasSonet() {
        return (getSonet() != null);
    }
    
    public void removeSonet() {
        setSonet(null);
    }
    
    private java.lang.Long get$oidSonet() {
        pt.ist.fenixframework.pstm.AbstractDomainObject value = ((DO_State)this.get$obj$state(false)).sonet;
        return (value == null) ? null : value.getOid();
    }
    
    public pt.ist.sonet.domain.Agent getAgent() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "agent");
        return ((DO_State)this.get$obj$state(false)).agent;
    }
    
    public void setAgent(pt.ist.sonet.domain.Agent agent) {
        AgentContainsPublications.add((pt.ist.sonet.domain.Publication)this, agent);
    }
    
    public boolean hasAgent() {
        return (getAgent() != null);
    }
    
    public void removeAgent() {
        setAgent(null);
    }
    
    private java.lang.Long get$oidAgent() {
        pt.ist.fenixframework.pstm.AbstractDomainObject value = ((DO_State)this.get$obj$state(false)).agent;
        return (value == null) ? null : value.getOid();
    }
    
    public int getCommentCount() {
        return get$rl$comment().size();
    }
    
    public boolean hasAnyComment() {
        return (! get$rl$comment().isEmpty());
    }
    
    public boolean hasComment(pt.ist.sonet.domain.Comment comment) {
        return get$rl$comment().contains(comment);
    }
    
    public java.util.Set<pt.ist.sonet.domain.Comment> getCommentSet() {
        return get$rl$comment();
    }
    
    public void addComment(pt.ist.sonet.domain.Comment comment) {
        PublicationsContainsComments.add((pt.ist.sonet.domain.Publication)this, comment);
    }
    
    public void removeComment(pt.ist.sonet.domain.Comment comment) {
        PublicationsContainsComments.remove((pt.ist.sonet.domain.Publication)this, comment);
    }
    
    public java.util.List<pt.ist.sonet.domain.Comment> getComment() {
        return get$rl$comment();
    }
    
    public void set$comment(OJBFunctionalSetWrapper comment) {
        get$rl$comment().setFromOJB(this, "comment", comment);
    }
    
    public java.util.Iterator<pt.ist.sonet.domain.Comment> getCommentIterator() {
        return get$rl$comment().iterator();
    }
    
    public int getVotersCount() {
        return get$rl$voters().size();
    }
    
    public boolean hasAnyVoters() {
        return (! get$rl$voters().isEmpty());
    }
    
    public boolean hasVoters(pt.ist.sonet.domain.Agent voters) {
        return get$rl$voters().contains(voters);
    }
    
    public java.util.Set<pt.ist.sonet.domain.Agent> getVotersSet() {
        return get$rl$voters();
    }
    
    public void addVoters(pt.ist.sonet.domain.Agent voters) {
        PublicationsContainsAgent.add((pt.ist.sonet.domain.Publication)this, voters);
    }
    
    public void removeVoters(pt.ist.sonet.domain.Agent voters) {
        PublicationsContainsAgent.remove((pt.ist.sonet.domain.Publication)this, voters);
    }
    
    public java.util.List<pt.ist.sonet.domain.Agent> getVoters() {
        return get$rl$voters();
    }
    
    public void set$voters(OJBFunctionalSetWrapper voters) {
        get$rl$voters().setFromOJB(this, "voters", voters);
    }
    
    public java.util.Iterator<pt.ist.sonet.domain.Agent> getVotersIterator() {
        return get$rl$voters().iterator();
    }
    
    protected void checkDisconnected() {
        if (hasSonet()) handleAttemptToDeleteConnectedObject();
        if (hasAgent()) handleAttemptToDeleteConnectedObject();
        if (hasAnyComment()) handleAttemptToDeleteConnectedObject();
        if (hasAnyVoters()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        DO_State castedState = (DO_State)state;
        set$id(pt.ist.fenixframework.pstm.ResultSetReader.readInteger(rs, "ID"), state);
        set$caption(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "CAPTION"), state);
        set$scorePlus(pt.ist.fenixframework.pstm.ResultSetReader.readInteger(rs, "SCORE_PLUS"), state);
        set$scoreMinus(pt.ist.fenixframework.pstm.ResultSetReader.readInteger(rs, "SCORE_MINUS"), state);
        castedState.sonet = pt.ist.fenixframework.pstm.ResultSetReader.readDomainObject(rs, "OID_SONET");
        castedState.agent = pt.ist.fenixframework.pstm.ResultSetReader.readDomainObject(rs, "OID_AGENT");
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        if (attrName.equals("comment")) return PublicationsContainsComments;
        if (attrName.equals("voters")) return PublicationsContainsAgent;
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        get$$relationList("comment", PublicationsContainsComments);
        get$$relationList("voters", PublicationsContainsAgent);
        
    }
    protected static class DO_State extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State {
        private java.lang.Integer id;
        private java.lang.String caption;
        private java.lang.Integer scorePlus;
        private java.lang.Integer scoreMinus;
        private pt.ist.sonet.domain.Sonet sonet;
        private pt.ist.sonet.domain.Agent agent;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.id = this.id;
            newCasted.caption = this.caption;
            newCasted.scorePlus = this.scorePlus;
            newCasted.scoreMinus = this.scoreMinus;
            newCasted.sonet = this.sonet;
            newCasted.agent = this.agent;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private java.lang.Integer id;
            private java.lang.String caption;
            private java.lang.Integer scorePlus;
            private java.lang.Integer scoreMinus;
            private pt.ist.sonet.domain.Sonet sonet;
            private pt.ist.sonet.domain.Agent agent;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.id = obj.id;
                this.caption = obj.caption;
                this.scorePlus = obj.scorePlus;
                this.scoreMinus = obj.scoreMinus;
                this.sonet = obj.sonet;
                this.agent = obj.agent;
                
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
                state.caption = this.caption;
                state.scorePlus = this.scorePlus;
                state.scoreMinus = this.scoreMinus;
                state.sonet = this.sonet;
                state.agent = this.agent;
                
            }
            
        }
        
    }
    
}
