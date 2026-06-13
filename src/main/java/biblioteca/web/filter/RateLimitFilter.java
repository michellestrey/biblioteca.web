package biblioteca.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class RateLimitFilter implements Filter {


    private Map<String, Integer> requests = new HashMap<>();

    private long inicio = System.currentTimeMillis();


    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;


        String ip = req.getRemoteAddr();


        long agora = System.currentTimeMillis();


        // reseta contador a cada 1 minuto
        if (agora - inicio > 60000) {

            requests.clear();
            inicio = agora;

        }


        int quantidade = requests.getOrDefault(ip, 0);


        // limite: 100 requisições por minuto por IP
        if (quantidade >= 100) {

            resp.setStatus(429);

            resp.getWriter()
                .write("Muitas requisições. Aguarde.");

            return;

        }


        requests.put(ip, quantidade + 1);


        chain.doFilter(request, response);

    }

}