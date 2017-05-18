package ieee.secondoes.problema;

public class IEEEProblemaA {

    public static void main(String[] args) {
        long start, end, delta, somma, div1, div2, mcm;
        final long EDGE = new Long("100000");
        div1 = 70; div2 = 3; somma = 0;
        mcm = mcm(div1, div2);
        
        start = System.currentTimeMillis();
        somma += somma(EDGE, div1);
        somma += somma(EDGE, div2);
        somma -= somma(EDGE, mcm);
        end = System.currentTimeMillis();
        
        delta = end - start;
        System.out.println("Risultato:\t\t" + somma);
        System.out.println("Tempo di esecuzione:\t" + delta + " millisecondi");
    }
    
    private static long mcm(long a, long b) {
        boolean trovato = false; long i = Long.max(a, b) - 1;
        while(!trovato) {
            i ++;
            if(i % a == 0 && i % b == 0) { trovato = true; }
        }
        return i;
    }
    
    private static long somma(long n, long div) {
        if(n < div) return 0;
        if(n % div == 0) n--;
        n -= n % div;
        return sommaDivs(n, div);
    }
    
    private static long sommaDivs(long n, long div) {
        boolean ridotto = false; long temp = 0;
        long v = n / div;
        if(v % 2 == 0) { temp = n; n -= div; ridotto = true; }
        v = n / div;
        long t = n / div;
        t = t / 2;
        t = t + 1;
        t = t * div;
        t = t * v;
        if(ridotto) t = t + temp;
        return t;
    }
    
}
