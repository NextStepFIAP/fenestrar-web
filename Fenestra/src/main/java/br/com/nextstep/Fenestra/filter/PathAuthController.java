package br.com.nextstep.Fenestra.filter;
import br.com.nextstep.Fenestra.model.User;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component()
@Order(1)
public class PathAuthController implements Filter {

    @Override
    public void doFilter(
            ServletRequest req,
            ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        //Adquirindo atributos da requisição
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI().toString();
        User user = (User) session.getAttribute("user");

        //evitando looping desnecessário de usuário não logado
        if (uri.equals("/login") || user != null) {
            chain.doFilter(req, res);
            return;
        }

        //Autorizando o uso de css e js e imágens na página
        if (uri.contains("css") || uri.contains("styles") || uri.contains("js") || uri.contains("assets")) {
            chain.doFilter(req, res);
            return;
        }

        if(uri.contains("h2")){
            chain.doFilter(req,res);
            return;
        }

        if(uri.contains("api")){
            chain.doFilter(req,res);
            return;
        }

        //Para caso o usuário não tenha logado,vai ser redirecionado para a página welcome
        req = new HttpServletRequestWrapper((HttpServletRequest) request) {
            @Override
            public String getRequestURI() {
                return "/login";
            }
        };
        chain.doFilter(req, res);
    }
}
