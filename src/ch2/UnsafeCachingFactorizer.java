package ch2;

import net.jcip.annotations.NotThreadSafe;
import sun.nio.cs.ext.DoubleByte;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by lydxlx on 8/7/16.
 */
@NotThreadSafe
public abstract class UnsafeCachingFactorizer implements Servlet {
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber.get())) { // This is still check-then-act.
            encodeIntoResponse(resp, lastFactors.get());
        } else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i); // These two steps should be atomic.
            lastFactors.set(factors);
            encodeIntoResponse(resp, factors);
        }
    }

    protected abstract BigInteger[] factor(BigInteger i);

    protected abstract BigInteger extractFromRequest(ServletRequest req);

    protected abstract void encodeIntoResponse(ServletResponse resp, BigInteger[] factors);


}
