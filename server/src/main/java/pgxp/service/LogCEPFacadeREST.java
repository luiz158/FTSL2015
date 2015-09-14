/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pgxp.domain.LogLogradouro;

/**
 *
 * @author gladson
 */
@Stateless
@Path("cod")
public class LogCEPFacadeREST extends AbstractFacade<LogLogradouro> {

    @PersistenceContext(unitName = "pgxp_cep_war_1.0.0PU")
    private EntityManager em;

    public LogCEPFacadeREST() {
        super(LogLogradouro.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Path("{cep}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("cep") String id) {
        return Response.ok().entity(super.findNamedQuary("LogLogradouro.findByCep", "cep", id)).build();
    }

}
