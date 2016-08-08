package ch2;


import net.jcip.annotations.NotThreadSafe;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by lydxlx on 8/6/16.
 */
@NotThreadSafe
public abstract class UnsafeCountingFactorizer implements Servlet {
    private long count = 0;

    public long getCount() {
        return count;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(i);
        ++count; // This is read-modify-write
        encodeIntoResponse(servletResponse, factors);
    }

    public abstract BigInteger extractFromRequest(ServletRequest req);

    public abstract BigInteger[] factor(BigInteger i);

    public abstract void encodeIntoResponse(ServletResponse resp, BigInteger[] factors);
}
