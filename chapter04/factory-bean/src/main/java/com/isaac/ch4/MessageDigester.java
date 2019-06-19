package com.isaac.ch4;

import java.security.MessageDigest;

public class MessageDigester {
    private MessageDigest digester1;
    private MessageDigest digester2;

    public void setDigester1(MessageDigest digester1) {
        this.digester1 = digester1;
    }

    public void setDigester2(MessageDigest digester2) {
        this.digester2 = digester2;
    }

    public void digest(String msg) {
        System.out.println("Using digest1:");
        digest(msg, digester1);

        System.out.println("Using digest2:");
        digest(msg, digester2);
    }

    private void digest(String msg, MessageDigest digest) {
        System.out.println("Using alogrithm:" + digest.getAlgorithm());
        digest.reset();
        byte[] bytes = msg.getBytes();
        byte[] out = digest.digest(bytes);
        System.out.println("Digest result:" + out);
    }


}
