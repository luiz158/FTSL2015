/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.security;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author 70744416353
 */
@PreMatching
@Provider
public class Filter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();

        if (requestContext.getRequest().getMethod().equals("OPTIONS")) {
            CacheControl cc = new CacheControl();
            cc.setNoCache(false);
            cc.setNoStore(false);
            cc.setPrivate(false);
            cc.setMaxAge(3600000);
            cc.setMustRevalidate(false);
            requestContext.abortWith(Response.status(Response.Status.OK).cacheControl(cc).build());
            return;
        }

        if (!path.contains("Auth")) {
            return;
        }
    }

}
