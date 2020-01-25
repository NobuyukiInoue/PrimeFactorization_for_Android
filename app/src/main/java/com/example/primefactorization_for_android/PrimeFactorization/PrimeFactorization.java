package com.example.primefactorization_for_android.PrimeFactorization;

import java.util.ArrayList;
import java.util.List;

import com.example.primefactorization_for_android.PrimeFactorization.SystemInformation.SystemInformation;

public class PrimeFactorization {
    private String resultStr;

    public void Main(long n) {
        StringBuilder resultStrBuffer = new StringBuilder();

        if (n < 2) {
            resultStrBuffer.append(Long.toString(n) + " is not positive longeger.\n");
            return;
        }

        resultStr = "";
        SystemInformation si = new SystemInformation();
        si.printProperties();

        resultStrBuffer.append("n = " + Long.toString(n) + "\n");
        resultStrBuffer.append(Long.toString(n) + " < pow(2, " + Long.toString(GetBitLength(n, 4097)) + ")\n");

        long start = System.currentTimeMillis();

        long[] result = trial_division(n);

        long end = System.currentTimeMillis();

        resultStrBuffer.append("primes = [" + ArraylongToString(result) + "]\n");
        resultStrBuffer.append("Answer Check : " + AnswerCheck(n, result) + "\n");
        resultStrBuffer.append("Execute time : " + (end - start)/1000.0 + "[s]\n\n");

        resultStr = resultStrBuffer.toString();
    }

    public String getResultStr() {
        return resultStr;
    }

    private long GetBitLength(long N, long max) {
        for (long i = 1; i < max; i++) {
            if (N <= Math.pow(2, i)) {
                return i;
            }
        }
        return -1;
    }

    private long[] trial_division(long n) {
        List<Long> prime_list = new ArrayList<>();
        long max = (long)(Math.sqrt(n)) + 1;

        // divide 2
        while (n % 2 == 0) {
            prime_list.add((long)2);
            n /= 2;
        }

        // divide 3
        while (n % 3 == 0) {
            prime_list.add((long)3);
            n /= 3;
        }

        // divide 5...
        boolean flag = true;
        for (long i = 5; i < max; ) {
            while (n % i == 0) {
                prime_list.add(i);
                n /= i;
                if (n == 1)
                    i = max;
            }

            if (flag)
                i += 2;
            else
                i += 4;

            flag = !flag;
        }

        if (n != 1) {
            prime_list.add(n);
        }

        // Long[] result = prime_list.toArray(new Long[prime_list.size()]);
        long[] result = new long[prime_list.size()];
        for (int i = 0; i < prime_list.size(); i++) {
            result[i] = prime_list.get(i);
        }
        return result;
    }

    private String ArraylongToString(long[] primes) {
        if (primes.length <= 0) {
            return "";
        }

        String resultStr = Long.toString(primes[0]);
        for (int i = 1; i < primes.length; i++) {
            resultStr += ", " + Long.toString(primes[i]);
            
        }

        return resultStr;
    }

    private String AnswerCheck(long n, long[] primes) {
        long answer = 1;

        for (long target : primes)
            answer *= target;

        if (answer == n)
            return "OK";
        else
            return "NG";

    }
}
