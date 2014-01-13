/*
 * DrakkarKeel - An Enterprise Collaborative Search Platform
 *
 * The contents of this file are subject under the terms described in the
 * DRAKKARKEEL_LICENSE file included in this distribution; you may not use this
 * file except in compliance with the License. 
 *
 * 2013-2014 DrakkarKeel Platform.
 */
package drakkar.prow.controller;

import drakkar.oar.Response;
import Ice.Current;
import drakkar.prow.facade.desktop.event.CollaborativeEnvironmentEvent;
import drakkar.prow.facade.desktop.event.DefaultListenerManager;
import drakkar.prow.facade.desktop.event.ExplicitRecommendationEvent;
import drakkar.prow.facade.desktop.event.ImplicitRecommendationEvent;
import drakkar.prow.facade.desktop.event.SearchEvent;
import drakkar.prow.facade.desktop.event.SeekerEvent;
import drakkar.prow.facade.desktop.event.ServerEvent;
import drakkar.prow.facade.desktop.event.SynchronousAwarenessEvent;
import drakkar.prow.facade.desktop.event.TextMessageEvent;
import drakkar.prow.facade.desktop.event.TransactionEvent;
import drakkar.oar.facade.event.FacadeDesktopEvent;
import drakkar.oar.slice.action.twoway.AMD_Get_getAMID;
import drakkar.oar.slice.client._ClientSideDisp;
import drakkar.oar.slice.error.RequestException;
import static drakkar.oar.util.KeySession.*;
import static drakkar.oar.util.KeyTransaction.*;
import static drakkar.oar.util.NotifyAction.*;
import drakkar.oar.util.OutputMonitor;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase representa el sirviente del Objeto Ice ClientSide. Esta clase es la
 * encargada de la implementación del método declarado en  la interfaz de la
 * definición slice. Es decir la que determina la operación ha realizar, para
 * actualizar la aplicación del cliente, cuando se reporta una notificación por
 * parte de la aplicación servidora
 *
 * @deprecated As of DrakkarKeel version 1.1,
 * replaced by <code>ProwController</code>.
 *
 * @see drakkar.prow.communication.ClientController
 */
public class ProwAppController extends _ClientSideDisp implements Serializable{
    private static final long serialVersionUID = 80000000000022L;

    private DefaultListenerManager listener;
    

    /**
     * Constructor de la clase
     *
     * @param communication objeto de comunicación
     *
     */
    public ProwAppController() {
        this.listener = new DefaultListenerManager();
       

    }

    /**
     * Devuelve la instancia de la clase ListenersManager
     *
     * @return  instancia de ListenersManager
     */
    public DefaultListenerManager getClientListenerManager() {
        return listener;
    }

    /**
     * Modifica la instancia de la clase ListenersManager
     *
     * @param notif  nueva instancia de ListenersManager
     */
    public void setClientListenerManager(DefaultListenerManager notif) {
        this.listener = notif;
    }

    /**
     * Este método es el encargado de llevar a cabo la deserialización del objeto
     * Response enviado por la aplicación servidora, para efectuar la notificación
     * correspondiente en la aplicación cliente
     *
     * @param response  objeto Response serializado
     * @param current   instancia del objeto Current de Ice, quien proporciona
     *                  información sobre la invocación
     *
     */
    public void _notify(byte[] response, Current current) {

        try {

            final Response resp = Response.arrayToResponse(response, current.adapter.getCommunicator());
            // se envia el objeto Response recuperado al método dispatchController,
            // para que este realize el mapeo para la notificación correspondiente.

            Thread t = new Thread(new Runnable() {

                public void run() {
                    dispatch(resp);
                }
            });
            t.start();
        } catch (ClassNotFoundException ex) {
            OutputMonitor.printStream("To load in a class", ex);
        } catch (IOException ex) {
            OutputMonitor.printStream("Failed or interrupted I/O operations.", ex);

        }

    }

    /**
     * Este método realiza el mapeo de la notificación que dio origen al objeto
     * response, para efectuar la correspondiente notificación a la aplicación
     * cliente
     *
     * @param response  este objeto contiene la operación de notificación a efectuar,
     *                  así como los parámeros de la misma
     */
    private void dispatch(Response response) {
        // se obtiene la operación de notificación a realizar
        // objeto Response
        int operation;
        try {
            operation = Integer.valueOf(response.getParameters().get(OPERATION).toString());

        } catch (Exception e) {
            OutputMonitor.printStream("Corrupted request", e);
            return;
        }

        switch (operation) {

            case NOTIFY_TEXT_MESSAGE:
                TextMessageEvent evtm = new TextMessageEvent(this, response);
                listener.dispatchTextMessage(evtm);
//                OutputMonitor.printLine("Notification of text message.", OutputMonitor.INFORMATION_MESSAGE);
                break;
            ////////////////////////////////////////////////////////////////////
            case NOTIFY_SEARCH_RESULTS:
                SearchEvent evts = new SearchEvent(this, response);
//                OutputMonitor.printLine("Notification of search results.", OutputMonitor.INFORMATION_MESSAGE);
                listener.dispatchSearchResults(evts);

                break;
            ////////////////////////////////////////////////////////////////////
            case NOTIFY_EXPLICIT_RECOMMENDATION:
                ExplicitRecommendationEvent evtr = new ExplicitRecommendationEvent(this, response);
                listener.dispatchExplicitRecommendation(evtr);
//                OutputMonitor.printLine("Notification of explicit recommendation.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_ACTION_TRACK:
                CollaborativeEnvironmentEvent evtsu = new CollaborativeEnvironmentEvent(this, response);
                listener.dispatchActionTrack(evtsu);
//                OutputMonitor.printLine("Notification of action track.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_AVAILABLE_COLLAB_SESSION:
                CollaborativeEnvironmentEvent evtsu1 = new CollaborativeEnvironmentEvent(this, response);
                listener.dispatchAvailableCollabSession(evtsu1);
//                OutputMonitor.printLine("Notification of available collaborative session.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_CHAIRMAN_SETTING:
                CollaborativeEnvironmentEvent evtsu2 = new CollaborativeEnvironmentEvent(this, response);
                listener.dispatchChairmanSetting(evtsu2);
//                OutputMonitor.printLine("Notification of chairman setting.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_COLLAB_SESSION_ACCEPTANCE:
                CollaborativeEnvironmentEvent evtsu3 = new CollaborativeEnvironmentEvent(this, response);
                listener.dispatchCollabSessionAcceptance(evtsu3);
//                OutputMonitor.printLine("Notification of acceptance to the collaborative session.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_COLLAB_SESSION_AUTHENTICATION:
                CollaborativeEnvironmentEvent evtsu4 = new CollaborativeEnvironmentEvent(this, response);
                listener.dispatchCollabSessionAuthentication(evtsu4);
//                OutputMonitor.printLine("Notification of response to the collaborative session authentication.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_COLLAB_SESSION_EVENT:
                CollaborativeEnvironmentEvent evtsu5 = new CollaborativeEnvironmentEvent(this, response);
                dispatchCollabSessionEvent(evtsu5);
//                OutputMonitor.printLine("Notification of event to the collaborative session.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_SEEKER_EVENT:
                SeekerEvent evtdm = new SeekerEvent(this, response);
                listener.dispatchSeekerEvent(evtdm);
//                OutputMonitor.printLine("Notification of event to the seeker.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            /////////////////////////////////////////////

            case NOTIFY_REQUEST_CONNECTION:
                SeekerEvent evtconnection = new SeekerEvent(this, response);
                listener.dispatchRequestConnection(evtconnection);
//                OutputMonitor.printLine("Notification of request connection.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_SERVER_STATE:
                ServerEvent evtss = new ServerEvent(this, response);
                listener.dispatchServerState(evtss);
//                OutputMonitor.printLine("Notification of server state.", OutputMonitor.INFORMATION_MESSAGE);
                break;
            ////////////////////////////////////////////////////////////////////
            case NOTIFY_COMMIT_TRANSACTION:
                TransactionEvent evtp = new TransactionEvent(this, response);
                listener.dispatchCommitTransaction(evtp);
//                OutputMonitor.printLine("Notification of commit transaction.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_AVAILABLE_SEARCHERS:
                SearchEvent evta = new SearchEvent(this, response);
                listener.dispatchAvailableSearchers(evta);
//                OutputMonitor.printLine("Notification of available searchers.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            case NOTIFY_AVAILABLE_SVN_REPOSITORIES:
                SearchEvent evtrepo = new SearchEvent(this, response);
                listener.dispatchAvailableSVNRepositories(evtrepo);
//                OutputMonitor.printLine("Notification of available SVN repositories.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////       
            case NOTIFY_AVAILABLE_SEARCH_PRINCIPLES:
                SearchEvent evtsp = new SearchEvent(this, response);
                listener.dispatchAvailableSearchPrinciples(evtsp);
//                OutputMonitor.printLine("Notification of available search principles.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_PUTTING_QUERY_TERMS_TOGETHER:
                SynchronousAwarenessEvent evtsw1 = new SynchronousAwarenessEvent(this, response);
                listener.dispatchPuttingQueryTermsTogether(evtsw1);
//                OutputMonitor.printLine("Notification of putting query terms together.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_QUERY_CHANGE:
                SynchronousAwarenessEvent evtsw2 = new SynchronousAwarenessEvent(this, response);
                listener.dispatchQueryChange(evtsw2);
//                OutputMonitor.printLine("Notification the query change.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_QUERY_TYPED:
                SynchronousAwarenessEvent evtsw3 = new SynchronousAwarenessEvent(this, response);
                listener.dispatchQueryTyped(evtsw3);
//                OutputMonitor.printLine("Notification the query typed.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_QUERY_TERM_ACCEPTANCE:
                SynchronousAwarenessEvent evtsw4 = new SynchronousAwarenessEvent(this, response);
                listener.dispatchQueryTermAcceptance(evtsw4);
//                OutputMonitor.printLine("Notification of acceptance of the query term.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_COLLAB_TERMS_SUGGEST:
                ImplicitRecommendationEvent evtsw6 = new ImplicitRecommendationEvent(this, response);
                listener.dispatchCollabTermsRecommendation(evtsw6);
//                OutputMonitor.printLine("Notification of collaborative terms suggest.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_QUERY_TERMS_SUGGEST:
                ImplicitRecommendationEvent evtsw5 = new ImplicitRecommendationEvent(this, response);
                listener.dispatchQueryTermsRecommendation(evtsw5);
//                OutputMonitor.printLine("Notification the query terms suggest.", OutputMonitor.INFORMATION_MESSAGE);
                break;

            ////////////////////////////////////////////////////////////////////
            case NOTIFY_EVENT:
                FacadeDesktopEvent evt = new FacadeDesktopEvent(this, response);
                listener.dispatch(evt);
//                OutputMonitor.printLine("Notification of generic event.", OutputMonitor.INFORMATION_MESSAGE);
                break;
        }


    }

    public void getAMID_async(AMD_Get_getAMID cb, byte[] request, Current current) throws RequestException {
    }

    public byte[] getSAMI(byte[] request, Current current) throws RequestException {
        return null;
    }

    public void disconnect(Current current) {
        Thread t = new Thread(new Runnable() {

            public void run() {
                OutputMonitor.printLine("Destroyed proxy client.", OutputMonitor.INFORMATION_MESSAGE);
                Map<Object, Object> hash = new HashMap<>(3);
                hash.put(OPERATION, NOTIFY_CLOSE_CONNECTION);

                Response rsp = new Response(hash);
                listener.dispatchCloseConnection(new SeekerEvent(this, rsp));
            }
        });
        t.start();


    }

    private void dispatchCollabSessionEvent(CollaborativeEnvironmentEvent evt) {

        int event = (Integer) evt.getResponse().get(DISTRIBUTED_EVENT);

        switch (event) {
            case CREATED_COLLAB_SESSION:
                listener.dispatchCollabSessionCreation(evt);
                break;

            case FINALIZED_COLLAB_SESSION:
                listener.dispatchCollabSessionEnding(evt);
                break;

            case DELETED_COLLAB_SESSION:
                listener.dispatchCollabSessionDeleted(evt);
                break;
        }


    }
}
