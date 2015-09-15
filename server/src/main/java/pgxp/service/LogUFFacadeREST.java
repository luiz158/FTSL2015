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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pgxp.domain.LogFaixaUf;

/**
 *
 * @author gladson
 */
@Stateless
@Path("uf")
public class LogUFFacadeREST extends AbstractFacade<LogFaixaUf> {

    @PersistenceContext(unitName = "pgxp_cep_war_1.0.0PU")
    private EntityManager em;

    public LogUFFacadeREST() {
        super(LogFaixaUf.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response find() {
        return Response.ok().entity(super.findNamedQuary("LogFaixaUf.findAll")).build();
    }

}
