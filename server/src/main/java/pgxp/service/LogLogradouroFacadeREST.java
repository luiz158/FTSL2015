/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
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
@Path("log")
public class LogLogradouroFacadeREST extends AbstractFacade<LogLogradouro> {

    @PersistenceContext(unitName = "pgxp_cep_war_1.0.0PU")
    private EntityManager em;

    public LogLogradouroFacadeREST() {
        super(LogLogradouro.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Path("{uf}/{nome}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("uf") String uf, @PathParam("nome") String nome) {
        return Response.ok().entity(super.findNamedQuary("LogLogradouro.findByLogNome", "ufeSg", uf, "logNome", "%" + nome + "%")).build();
    }

}
