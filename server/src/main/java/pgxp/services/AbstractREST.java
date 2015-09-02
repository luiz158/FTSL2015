/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.services;

import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import pgxp.Util;
import pgxp.dao.AbstractDAO;
import pgxp.security.PGXPAuthenticator;

/**
 *
 * @author 70744416353
 */
@Produces({"application/json"})
@Consumes({"application/json"})
public abstract class AbstractREST<T> {

    private final Class<T> entityClass;

    @Inject
    PGXPAuthenticator authenticator;

    @Inject
    protected AbstractDAO<T> dao;

    public AbstractREST(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @POST
    public void create(T entity) {
        dao.create(entity);
    }

    @PUT
    public void edit(T entity) {
        dao.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        dao.remove(id);
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id) {
        return Response.ok().entity((T) dao.find(id)).build();
    }

    @GET
    @Path("list/{field}/{order}/{init}/{qtde}")
    public Response list(@PathParam("field") String field, @PathParam("order") String order, @PathParam("init") int init,
                         @PathParam("qtde") int qtde) {
        Class<T> instancia;
        if ((order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("desc")) && (Util.fieldInClass(field, entityClass))) {
            return Response.ok().entity(dao.list(field, order, init, qtde)).build();
        }
        return null;
    }

    @GET
    public Response findAll() {
        return Response.ok().entity(dao.findAll()).build();
    }

    @GET
    @Path("count")
    public int count() {
        return dao.count();
    }

}
