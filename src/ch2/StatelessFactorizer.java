package ch2;


import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by lydxlx on 8/6/16.
 */
@ThreadSafe
public abstract class StatelessFactorizer implements Servlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(servletResponse, factors);
    }

    public abstract BigInteger extractFromRequest(ServletRequest req);

    public abstract BigInteger[] factor(BigInteger i);

    public abstract void encodeIntoResponse(ServletResponse resp, BigInteger[] factors);
}
