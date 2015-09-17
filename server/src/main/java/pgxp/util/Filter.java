/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.util;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author 70744416353
 */
@PreMatching
@Provider
public class Filter implements ClientRequestFilter, ClientResponseFilter, ContainerRequestFilter {

    @Override
    public void filter(ClientRequestContext requestContext) {
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) {
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
//        try {
//            SimplePiwikTracker instance = new SimplePiwikTracker("http://192.168.100.3/piwik");
//            instance.setIdSite(3);
//            System.out.println(requestContext.getUriInfo().getAbsolutePath().toString());
//            ResponseData result = instance.sendRequest(requestContext.getUriInfo().getAbsolutePath().toURL());
//            for (javax.servlet.http.Cookie c : result.getCookies()) {
//                System.out.println(c.getComment());
//                System.out.println(c.getDomain());
//                System.out.println(c.getMaxAge());
//                System.out.println(c.getName());
//                System.out.println(c.getPath());
//                System.out.println(c.getSecure());
//                System.out.println(c.getValue());
//                System.out.println(c.getVersion());
//                System.out.println("-------------------------");
//            }
//
//        } catch (PiwikException | MalformedURLException ex) {
//            Logger.getLogger(Filter.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
