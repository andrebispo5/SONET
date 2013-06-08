package pt.pagamigo.ws.handler;

import java.io.*;
import java.util.*;

import javax.xml.datatype.*;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.*;
import javax.xml.ws.handler.soap.*;

import pt.pagamigo.ws.xid.XidImpl;


 
public class PAHandlerResolver implements HandlerResolver {

	public PAHandlerResolver() {
    }

    @Override
    public List<Handler> getHandlerChain(PortInfo portInfo) {

        List<Handler> hchain = new ArrayList<Handler>();
        hchain.add(new HeaderHandler(XidImpl.getXid()));
        return hchain;
    }
}