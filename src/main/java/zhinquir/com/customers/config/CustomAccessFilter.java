package zhinquir.com.customers.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import zhinquir.com.customers.utils.JwtUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * Clase que funciona como filtro, con un metodo que realiza el filtro y otro que verifica si esta autorizado.
 */
@Component
public class CustomAccessFilter implements Filter {

    /**
     * Se usa @Component para poder instanciar en otra clase
     */
    public CustomAccessFilter() {
    }

    /**
     * Metodo que se encarga de realizar le filtro de request y validar si estan autorizadas.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean authorized = isAuthorized(request);
        if (authorized) {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(null, null, Collections.emptyList()));
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private boolean isAuthorized(HttpServletRequest request) {
        String currentUrl = request.getRequestURI(); // obtiene la url actual
        String[] availableUrl = new String[] {
                "/api/auth/login",
                "/api/auth/register"
        }; // arreglo urls que siempre estan permitidas

        boolean authorized = Arrays.asList(availableUrl).contains(currentUrl); // valida si la url actual esta dentro de las urls que siempre estan permitidas
        boolean isApiResource = currentUrl.startsWith("/api/");
        if (authorized || !isApiResource) {
            return true;
        }

        try {
            String token = request.getHeader("Authorization"); // obtiene el token de la cebecera
            String userId = JwtUtil.getUserIdByToken(token); // obtiene el usuarID del token ya con eso se puede consultar en base para roles u otra logica.
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
