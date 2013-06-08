package pt.bank.ws.handler;

import java.io.*;
import java.util.*;

import javax.xml.datatype.*;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.*;
import javax.xml.ws.handler.soap.*;


 
public class BankHandlerResolver implements HandlerResolver {

	public BankHandlerResolver() {
    }

    @Override
    public List<Handler> getHandlerChain(PortInfo portInfo) {

        List<Handler> hchain = new ArrayList<Handler>();
        hchain.add(new HeaderHandler());
        return hchain;
    }
}