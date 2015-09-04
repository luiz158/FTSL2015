/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import pgxp.domain.Todo;

/**
 *
 * @author gladson
 */
@Path("todo")
public class TodoREST extends AbstractREST<Todo> {

    public TodoREST() {
        super(Todo.class);
    }

    @POST
    @Path("fazendo")
    public Response fazendo() {
        return Response.ok().entity("").build();
    }

    @POST
    @Path("feito")
    public Response feito() {
        return Response.ok().entity("").build();
    }

}
