/*
 * DrakkarKeel - An Enterprise Collaborative Search Platform
 *
 * The contents of this file are subject under the terms described in the
 * DRAKKARKEEL_LICENSE file included in this distribution; you may not use this
 * file except in compliance with the License. 
 *
 * 2013-2014 DrakkarKeel Platform.
 */
package drakkar.oar.facade.event;

import drakkar.oar.Response;
import java.util.EventObject;

/**
 * Esta clase representa el raíz todos los eventos desktop de DrakkarKeel
 *
 * 
 *
 * @deprecated use drakkar.prow.facade.desktop.event.FacadeDesktopEvent
 *
 * 
 *
 */
public class DesktopEvent  extends EventObject  {

    protected Response response;

    /**
     * Contructor de la clase
     *
     * @param source objeto
     */
    public DesktopEvent(Object source) {
        super(source);
        response = new Response();
    }

    /**
     * Contructor de la clase
     *
     * @param source   objeto
     * @param response parametros de la operación
     */
    public DesktopEvent(Object source, Response response) {
        super(source);
        this.response = response;
    }

    /**
     * Devuelve el objeto response del evento
     *
     * @return response
     */
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

   

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DesktopEvent other = (DesktopEvent) obj;
        if (this.response != other.response && (this.response == null || !this.response.equals(other.response))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.response != null ? this.response.hashCode() : 0);
        return hash;
    }

}
