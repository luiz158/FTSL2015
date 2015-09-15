/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pgxp.util;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author 70744416353
 */
@PreMatching
@Provider
public class Cors implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) {
        response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.getHeaders().putSingle("Access-Control-Allow-Headers", "content-type");
    }

}
