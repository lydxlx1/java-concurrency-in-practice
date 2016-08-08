package ch2;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lydxlx on 8/7/16.
 */
@ThreadSafe
public abstract class CountingFactorizer implements Servlet {
    private final AtomicLong count = new AtomicLong(0L);

    public long getCount() {
        return count.get();
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(resp, factors);
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return BigInteger.valueOf(7);
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{i}; // really not factorize it
    }

    protected abstract void encodeIntoResponse(ServletResponse resp, BigInteger[] factors);

}
