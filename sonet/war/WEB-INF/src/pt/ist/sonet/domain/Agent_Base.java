package pt.ist.sonet.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class Agent_Base extends pt.ist.fenixframework.pstm.OneBoxDomainObject {
    public final static pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Sonet> role$$sonet = new pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Sonet>() {
        public pt.ist.sonet.domain.Sonet getValue(pt.ist.sonet.domain.Agent o1) {
            return ((Agent_Base.DO_State)o1.get$obj$state(false)).sonet;
        }
        public void setValue(pt.ist.sonet.domain.Agent o1, pt.ist.sonet.domain.Sonet o2) {
            ((Agent_Base.DO_State)o1.get$obj$state(true)).sonet = o2;
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Sonet,pt.ist.sonet.domain.Agent> getInverseRole() {
            return pt.ist.sonet.domain.Sonet.role$$agent;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> role$$requests = new dml.runtime.RoleMany<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent>() {
        public dml.runtime.RelationBaseSet<pt.ist.sonet.domain.Agent> getSet(pt.ist.sonet.domain.Agent o1) {
            return ((Agent_Base)o1).get$rl$requests();
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> getInverseRole() {
            return pt.ist.sonet.domain.Agent.role$$requestList;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> role$$requestList = new pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent>() {
        public pt.ist.sonet.domain.Agent getValue(pt.ist.sonet.domain.Agent o1) {
            return ((Agent_Base.DO_State)o1.get$obj$state(false)).requestList;
        }
        public void setValue(pt.ist.sonet.domain.Agent o1, pt.ist.sonet.domain.Agent o2) {
            ((Agent_Base.DO_State)o1.get$obj$state(true)).requestList = o2;
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> getInverseRole() {
            return pt.ist.sonet.domain.Agent.role$$requests;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> role$$friends = new dml.runtime.RoleMany<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent>() {
        public dml.runtime.RelationBaseSet<pt.ist.sonet.domain.Agent> getSet(pt.ist.sonet.domain.Agent o1) {
            return ((Agent_Base)o1).get$rl$friends();
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> getInverseRole() {
            return pt.ist.sonet.domain.Agent.role$$friendList;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> role$$friendList = new dml.runtime.RoleMany<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent>() {
        public dml.runtime.RelationBaseSet<pt.ist.sonet.domain.Agent> getSet(pt.ist.sonet.domain.Agent o1) {
            return ((Agent_Base)o1).get$rl$friendList();
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> getInverseRole() {
            return pt.ist.sonet.domain.Agent.role$$friends;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> role$$blocks = new dml.runtime.RoleMany<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent>() {
        public dml.runtime.RelationBaseSet<pt.ist.sonet.domain.Agent> getSet(pt.ist.sonet.domain.Agent o1) {
            return ((Agent_Base)o1).get$rl$blocks();
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> getInverseRole() {
            return pt.ist.sonet.domain.Agent.role$$blockedList;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> role$$blockedList = new pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent>() {
        public pt.ist.sonet.domain.Agent getValue(pt.ist.sonet.domain.Agent o1) {
            return ((Agent_Base.DO_State)o1.get$obj$state(false)).blockedList;
        }
        public void setValue(pt.ist.sonet.domain.Agent o1, pt.ist.sonet.domain.Agent o2) {
            ((Agent_Base.DO_State)o1.get$obj$state(true)).blockedList = o2;
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> getInverseRole() {
            return pt.ist.sonet.domain.Agent.role$$blocks;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Publication> role$$publication = new dml.runtime.RoleMany<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Publication>() {
        public dml.runtime.RelationBaseSet<pt.ist.sonet.domain.Publication> getSet(pt.ist.sonet.domain.Agent o1) {
            return ((Agent_Base)o1).get$rl$publication();
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Agent> getInverseRole() {
            return pt.ist.sonet.domain.Publication.role$$agent;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Publication> role$$pub = new pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Publication>() {
        public pt.ist.sonet.domain.Publication getValue(pt.ist.sonet.domain.Agent o1) {
            return ((Agent_Base.DO_State)o1.get$obj$state(false)).pub;
        }
        public void setValue(pt.ist.sonet.domain.Agent o1, pt.ist.sonet.domain.Publication o2) {
            ((Agent_Base.DO_State)o1.get$obj$state(true)).pub = o2;
        }
        public dml.runtime.Role<pt.ist.sonet.domain.Publication,pt.ist.sonet.domain.Agent> getInverseRole() {
            return pt.ist.sonet.domain.Publication.role$$voters;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Sonet> SonetContainsAgents = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Sonet>(role$$sonet);
    static {
        pt.ist.sonet.domain.Sonet.SonetContainsAgents = SonetContainsAgents.getInverseRelation();
    }
    
    static {
        SonetContainsAgents.setRelationName("pt.ist.sonet.domain.Agent.SonetContainsAgents");
    }
    public static dml.runtime.Relation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> AgentContainsFriendRequests$Inverse;
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> AgentContainsFriendRequests = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent>(role$$requestList);
    static {
        pt.ist.sonet.domain.Agent.AgentContainsFriendRequests$Inverse = AgentContainsFriendRequests.getInverseRelation();
    }
    
    static {
        AgentContainsFriendRequests.setRelationName("pt.ist.sonet.domain.Agent.AgentContainsFriendRequests");
    }
    public static dml.runtime.Relation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> AgentContainsFriends$Inverse;
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> AgentContainsFriends = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent>(role$$friendList);
    static {
        pt.ist.sonet.domain.Agent.AgentContainsFriends$Inverse = AgentContainsFriends.getInverseRelation();
    }
    
    static {
        AgentContainsFriends.setRelationName("pt.ist.sonet.domain.Agent.AgentContainsFriends");
        AgentContainsFriends.addListener(new dml.runtime.RelationAdapter<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent>() {
            @Override
            public void beforeAdd(pt.ist.sonet.domain.Agent arg0, pt.ist.sonet.domain.Agent arg1) {
                pt.ist.fenixframework.pstm.Transaction.addRelationTuple("AgentContainsFriends", arg1, "friends", arg0, "friendList");
            }
            @Override
            public void beforeRemove(pt.ist.sonet.domain.Agent arg0, pt.ist.sonet.domain.Agent arg1) {
                pt.ist.fenixframework.pstm.Transaction.removeRelationTuple("AgentContainsFriends", arg1, "friends", arg0, "friendList");
            }
            
        }
        );
    }
    public static dml.runtime.Relation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> AgentContainsBlockedAgent$Inverse;
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> AgentContainsBlockedAgent = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent>(role$$blockedList);
    static {
        pt.ist.sonet.domain.Agent.AgentContainsBlockedAgent$Inverse = AgentContainsBlockedAgent.getInverseRelation();
    }
    
    static {
        AgentContainsBlockedAgent.setRelationName("pt.ist.sonet.domain.Agent.AgentContainsBlockedAgent");
    }
    public static dml.runtime.Relation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Publication> AgentContainsPublications;
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Publication> PublicationsContainsAgent = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Publication>(role$$pub);
    static {
        pt.ist.sonet.domain.Publication.PublicationsContainsAgent = PublicationsContainsAgent.getInverseRelation();
    }
    
    static {
        PublicationsContainsAgent.setRelationName("pt.ist.sonet.domain.Agent.PublicationsContainsAgent");
    }
    
    
    private RelationList<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> get$rl$requests() {
        return get$$relationList("requests", AgentContainsFriendRequests$Inverse);
        
    }
    private RelationList<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> get$rl$friends() {
        return get$$relationList("friends", AgentContainsFriends$Inverse);
        
    }
    private RelationList<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> get$rl$friendList() {
        return get$$relationList("friendList", AgentContainsFriends);
        
    }
    private RelationList<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Agent> get$rl$blocks() {
        return get$$relationList("blocks", AgentContainsBlockedAgent$Inverse);
        
    }
    private RelationList<pt.ist.sonet.domain.Agent,pt.ist.sonet.domain.Publication> get$rl$publication() {
        return get$$relationList("publication", AgentContainsPublications);
        
    }
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  Agent_Base() {
        super();
    }
    
    public pt.ist.sonet.domain.Permission getPermission() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "permission");
        return ((DO_State)this.get$obj$state(false)).permission;
    }
    
    public void setPermission(pt.ist.sonet.domain.Permission permission) {
        ((DO_State)this.get$obj$state(true)).permission = permission;
    }
    
    private java.lang.String get$permission() {
        pt.ist.sonet.domain.Permission value = ((DO_State)this.get$obj$state(false)).permission;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForEnum(value);
    }
    
    private final void set$permission(pt.ist.sonet.domain.Permission arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).permission = (pt.ist.sonet.domain.Permission)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.String getUsername() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "username");
        return ((DO_State)this.get$obj$state(false)).username;
    }
    
    public void setUsername(java.lang.String username) {
        ((DO_State)this.get$obj$state(true)).username = username;
    }
    
    private java.lang.String get$username() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).username;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$username(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).username = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.String getName() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "name");
        return ((DO_State)this.get$obj$state(false)).name;
    }
    
    public void setName(java.lang.String name) {
        ((DO_State)this.get$obj$state(true)).name = name;
    }
    
    private java.lang.String get$name() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).name;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$name(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).name = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.String getEmail() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "email");
        return ((DO_State)this.get$obj$state(false)).email;
    }
    
    public void setEmail(java.lang.String email) {
        ((DO_State)this.get$obj$state(true)).email = email;
    }
    
    private java.lang.String get$email() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).email;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$email(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).email = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.String getCity() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "city");
        return ((DO_State)this.get$obj$state(false)).city;
    }
    
    public void setCity(java.lang.String city) {
        ((DO_State)this.get$obj$state(true)).city = city;
    }
    
    private java.lang.String get$city() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).city;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$city(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).city = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.String getCountry() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "country");
        return ((DO_State)this.get$obj$state(false)).country;
    }
    
    public void setCountry(java.lang.String country) {
        ((DO_State)this.get$obj$state(true)).country = country;
    }
    
    private java.lang.String get$country() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).country;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$country(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).country = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.String getPassword() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "password");
        return ((DO_State)this.get$obj$state(false)).password;
    }
    
    public void setPassword(java.lang.String password) {
        ((DO_State)this.get$obj$state(true)).password = password;
    }
    
    private java.lang.String get$password() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).password;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$password(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).password = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public pt.ist.sonet.domain.Sonet getSonet() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "sonet");
        return ((DO_State)this.get$obj$state(false)).sonet;
    }
    
    public void setSonet(pt.ist.sonet.domain.Sonet sonet) {
        SonetContainsAgents.add((pt.ist.sonet.domain.Agent)this, sonet);
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
    
    public int getRequestsCount() {
        return get$rl$requests().size();
    }
    
    public boolean hasAnyRequests() {
        return (! get$rl$requests().isEmpty());
    }
    
    public boolean hasRequests(pt.ist.sonet.domain.Agent requests) {
        return get$rl$requests().contains(requests);
    }
    
    public java.util.Set<pt.ist.sonet.domain.Agent> getRequestsSet() {
        return get$rl$requests();
    }
    
    public void addRequests(pt.ist.sonet.domain.Agent requests) {
        AgentContainsFriendRequests$Inverse.add((pt.ist.sonet.domain.Agent)this, requests);
    }
    
    public void removeRequests(pt.ist.sonet.domain.Agent requests) {
        AgentContainsFriendRequests$Inverse.remove((pt.ist.sonet.domain.Agent)this, requests);
    }
    
    public java.util.List<pt.ist.sonet.domain.Agent> getRequests() {
        return get$rl$requests();
    }
    
    public void set$requests(OJBFunctionalSetWrapper requests) {
        get$rl$requests().setFromOJB(this, "requests", requests);
    }
    
    public java.util.Iterator<pt.ist.sonet.domain.Agent> getRequestsIterator() {
        return get$rl$requests().iterator();
    }
    
    public pt.ist.sonet.domain.Agent getRequestList() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "requestList");
        return ((DO_State)this.get$obj$state(false)).requestList;
    }
    
    public void setRequestList(pt.ist.sonet.domain.Agent requestList) {
        AgentContainsFriendRequests.add((pt.ist.sonet.domain.Agent)this, requestList);
    }
    
    public boolean hasRequestList() {
        return (getRequestList() != null);
    }
    
    public void removeRequestList() {
        setRequestList(null);
    }
    
    private java.lang.Long get$oidRequestList() {
        pt.ist.fenixframework.pstm.AbstractDomainObject value = ((DO_State)this.get$obj$state(false)).requestList;
        return (value == null) ? null : value.getOid();
    }
    
    public int getFriendsCount() {
        return get$rl$friends().size();
    }
    
    public boolean hasAnyFriends() {
        return (! get$rl$friends().isEmpty());
    }
    
    public boolean hasFriends(pt.ist.sonet.domain.Agent friends) {
        return get$rl$friends().contains(friends);
    }
    
    public java.util.Set<pt.ist.sonet.domain.Agent> getFriendsSet() {
        return get$rl$friends();
    }
    
    public void addFriends(pt.ist.sonet.domain.Agent friends) {
        AgentContainsFriends$Inverse.add((pt.ist.sonet.domain.Agent)this, friends);
    }
    
    public void removeFriends(pt.ist.sonet.domain.Agent friends) {
        AgentContainsFriends$Inverse.remove((pt.ist.sonet.domain.Agent)this, friends);
    }
    
    public java.util.List<pt.ist.sonet.domain.Agent> getFriends() {
        return get$rl$friends();
    }
    
    public void set$friends(OJBFunctionalSetWrapper friends) {
        get$rl$friends().setFromOJB(this, "friends", friends);
    }
    
    public java.util.Iterator<pt.ist.sonet.domain.Agent> getFriendsIterator() {
        return get$rl$friends().iterator();
    }
    
    public int getFriendListCount() {
        return get$rl$friendList().size();
    }
    
    public boolean hasAnyFriendList() {
        return (! get$rl$friendList().isEmpty());
    }
    
    public boolean hasFriendList(pt.ist.sonet.domain.Agent friendList) {
        return get$rl$friendList().contains(friendList);
    }
    
    public java.util.Set<pt.ist.sonet.domain.Agent> getFriendListSet() {
        return get$rl$friendList();
    }
    
    public void addFriendList(pt.ist.sonet.domain.Agent friendList) {
        AgentContainsFriends.add((pt.ist.sonet.domain.Agent)this, friendList);
    }
    
    public void removeFriendList(pt.ist.sonet.domain.Agent friendList) {
        AgentContainsFriends.remove((pt.ist.sonet.domain.Agent)this, friendList);
    }
    
    public java.util.List<pt.ist.sonet.domain.Agent> getFriendList() {
        return get$rl$friendList();
    }
    
    public void set$friendList(OJBFunctionalSetWrapper friendList) {
        get$rl$friendList().setFromOJB(this, "friendList", friendList);
    }
    
    public java.util.Iterator<pt.ist.sonet.domain.Agent> getFriendListIterator() {
        return get$rl$friendList().iterator();
    }
    
    public int getBlocksCount() {
        return get$rl$blocks().size();
    }
    
    public boolean hasAnyBlocks() {
        return (! get$rl$blocks().isEmpty());
    }
    
    public boolean hasBlocks(pt.ist.sonet.domain.Agent blocks) {
        return get$rl$blocks().contains(blocks);
    }
    
    public java.util.Set<pt.ist.sonet.domain.Agent> getBlocksSet() {
        return get$rl$blocks();
    }
    
    public void addBlocks(pt.ist.sonet.domain.Agent blocks) {
        AgentContainsBlockedAgent$Inverse.add((pt.ist.sonet.domain.Agent)this, blocks);
    }
    
    public void removeBlocks(pt.ist.sonet.domain.Agent blocks) {
        AgentContainsBlockedAgent$Inverse.remove((pt.ist.sonet.domain.Agent)this, blocks);
    }
    
    public java.util.List<pt.ist.sonet.domain.Agent> getBlocks() {
        return get$rl$blocks();
    }
    
    public void set$blocks(OJBFunctionalSetWrapper blocks) {
        get$rl$blocks().setFromOJB(this, "blocks", blocks);
    }
    
    public java.util.Iterator<pt.ist.sonet.domain.Agent> getBlocksIterator() {
        return get$rl$blocks().iterator();
    }
    
    public pt.ist.sonet.domain.Agent getBlockedList() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "blockedList");
        return ((DO_State)this.get$obj$state(false)).blockedList;
    }
    
    public void setBlockedList(pt.ist.sonet.domain.Agent blockedList) {
        AgentContainsBlockedAgent.add((pt.ist.sonet.domain.Agent)this, blockedList);
    }
    
    public boolean hasBlockedList() {
        return (getBlockedList() != null);
    }
    
    public void removeBlockedList() {
        setBlockedList(null);
    }
    
    private java.lang.Long get$oidBlockedList() {
        pt.ist.fenixframework.pstm.AbstractDomainObject value = ((DO_State)this.get$obj$state(false)).blockedList;
        return (value == null) ? null : value.getOid();
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
        AgentContainsPublications.add((pt.ist.sonet.domain.Agent)this, publication);
    }
    
    public void removePublication(pt.ist.sonet.domain.Publication publication) {
        AgentContainsPublications.remove((pt.ist.sonet.domain.Agent)this, publication);
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
    
    public pt.ist.sonet.domain.Publication getPub() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "pub");
        return ((DO_State)this.get$obj$state(false)).pub;
    }
    
    public void setPub(pt.ist.sonet.domain.Publication pub) {
        PublicationsContainsAgent.add((pt.ist.sonet.domain.Agent)this, pub);
    }
    
    public boolean hasPub() {
        return (getPub() != null);
    }
    
    public void removePub() {
        setPub(null);
    }
    
    private java.lang.Long get$oidPub() {
        pt.ist.fenixframework.pstm.AbstractDomainObject value = ((DO_State)this.get$obj$state(false)).pub;
        return (value == null) ? null : value.getOid();
    }
    
    protected void checkDisconnected() {
        if (hasSonet()) handleAttemptToDeleteConnectedObject();
        if (hasAnyRequests()) handleAttemptToDeleteConnectedObject();
        if (hasRequestList()) handleAttemptToDeleteConnectedObject();
        if (hasAnyFriends()) handleAttemptToDeleteConnectedObject();
        if (hasAnyFriendList()) handleAttemptToDeleteConnectedObject();
        if (hasAnyBlocks()) handleAttemptToDeleteConnectedObject();
        if (hasBlockedList()) handleAttemptToDeleteConnectedObject();
        if (hasAnyPublication()) handleAttemptToDeleteConnectedObject();
        if (hasPub()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        DO_State castedState = (DO_State)state;
        set$permission(pt.ist.fenixframework.pstm.ResultSetReader.readEnum(pt.ist.sonet.domain.Permission.class, rs, "PERMISSION"), state);
        set$username(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "USERNAME"), state);
        set$name(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "NAME"), state);
        set$email(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "EMAIL"), state);
        set$city(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "CITY"), state);
        set$country(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "COUNTRY"), state);
        set$password(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "PASSWORD"), state);
        castedState.sonet = pt.ist.fenixframework.pstm.ResultSetReader.readDomainObject(rs, "OID_SONET");
        castedState.requestList = pt.ist.fenixframework.pstm.ResultSetReader.readDomainObject(rs, "OID_REQUEST_LIST");
        castedState.blockedList = pt.ist.fenixframework.pstm.ResultSetReader.readDomainObject(rs, "OID_BLOCKED_LIST");
        castedState.pub = pt.ist.fenixframework.pstm.ResultSetReader.readDomainObject(rs, "OID_PUB");
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        if (attrName.equals("requests")) return AgentContainsFriendRequests$Inverse;
        if (attrName.equals("friends")) return AgentContainsFriends$Inverse;
        if (attrName.equals("friendList")) return AgentContainsFriends;
        if (attrName.equals("blocks")) return AgentContainsBlockedAgent$Inverse;
        if (attrName.equals("publication")) return AgentContainsPublications;
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        get$$relationList("requests", AgentContainsFriendRequests$Inverse);
        get$$relationList("friends", AgentContainsFriends$Inverse);
        get$$relationList("friendList", AgentContainsFriends);
        get$$relationList("blocks", AgentContainsBlockedAgent$Inverse);
        get$$relationList("publication", AgentContainsPublications);
        
    }
    protected static class DO_State extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State {
        private pt.ist.sonet.domain.Permission permission;
        private java.lang.String username;
        private java.lang.String name;
        private java.lang.String email;
        private java.lang.String city;
        private java.lang.String country;
        private java.lang.String password;
        private pt.ist.sonet.domain.Sonet sonet;
        private pt.ist.sonet.domain.Agent requestList;
        private pt.ist.sonet.domain.Agent blockedList;
        private pt.ist.sonet.domain.Publication pub;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.permission = this.permission;
            newCasted.username = this.username;
            newCasted.name = this.name;
            newCasted.email = this.email;
            newCasted.city = this.city;
            newCasted.country = this.country;
            newCasted.password = this.password;
            newCasted.sonet = this.sonet;
            newCasted.requestList = this.requestList;
            newCasted.blockedList = this.blockedList;
            newCasted.pub = this.pub;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private pt.ist.sonet.domain.Permission permission;
            private java.lang.String username;
            private java.lang.String name;
            private java.lang.String email;
            private java.lang.String city;
            private java.lang.String country;
            private java.lang.String password;
            private pt.ist.sonet.domain.Sonet sonet;
            private pt.ist.sonet.domain.Agent requestList;
            private pt.ist.sonet.domain.Agent blockedList;
            private pt.ist.sonet.domain.Publication pub;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.permission = obj.permission;
                this.username = obj.username;
                this.name = obj.name;
                this.email = obj.email;
                this.city = obj.city;
                this.country = obj.country;
                this.password = obj.password;
                this.sonet = obj.sonet;
                this.requestList = obj.requestList;
                this.blockedList = obj.blockedList;
                this.pub = obj.pub;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.permission = this.permission;
                state.username = this.username;
                state.name = this.name;
                state.email = this.email;
                state.city = this.city;
                state.country = this.country;
                state.password = this.password;
                state.sonet = this.sonet;
                state.requestList = this.requestList;
                state.blockedList = this.blockedList;
                state.pub = this.pub;
                
            }
            
        }
        
    }
    
}
