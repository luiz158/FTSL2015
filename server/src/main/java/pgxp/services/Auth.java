/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.services;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pgxp.Util;
import pgxp.cover.Credentials;
import pgxp.domain.Usuario;
import pgxp.security.PGXPAuthenticator;

/**
 *
 * @author gladson
 */
@Path("auth")
@Produces({"application/json"})
@Consumes({"application/json"})
public class Auth {

    @Inject
    PGXPAuthenticator authenticator;

    @POST
    @Path("login")
    public Response login(Credentials credentials) {
        try {
            if (credentials.getNome() != null && credentials.getPassword() != null) {
                Usuario usuario = authenticator.login(credentials.getNome());
                if (usuario != null) {
                    if (usuario.getSenha().equals(Util.md5(credentials.getPassword()))) {
                        return Response.ok().entity(usuario).build();
                    } else {
                        return Response.status(Response.Status.UNAUTHORIZED).entity("Senha inválida, se não lembrar clique em: Esqueci minha senha").build();
                    }
                }
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (final Exception ex) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Entre em contato com o administrador").build();
        }
    }

    @DELETE
    @Path("logout")
    public Response logout(@Context HttpHeaders httpHeaders) {
        return Response.status(Response.Status.OK).build();
    }

}
