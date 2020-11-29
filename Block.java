import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

public class Block {
    String PreviousBlockHash;
    String CurrentBlockHash;
    Transaction Data;
    Long TimeStamp;
    Integer nonce;


    public Block( Transaction data,String previousBlockHash ,Long timeStamp) {
        this.CurrentBlockHash = calculateBlockHash();
        this.PreviousBlockHash = previousBlockHash;
        this.Data = data;
        this.TimeStamp = timeStamp;
        Random r = new Random(9999);
        this.nonce = r.nextInt();
    }

    public String calculateBlockHash() {
        String dataToHash = this.PreviousBlockHash
                + Long.toString(this.TimeStamp)
                + Integer.toString(this.nonce)
                + this.Data.toString();
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("The encoding is not supported");
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();

    }
    public String mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!this.CurrentBlockHash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            this.CurrentBlockHash = calculateBlockHash();
        }
        return this.CurrentBlockHash;
    }








    // Getters and Setters defined below


    public String getHash() {

        return this.CurrentBlockHash;

    }
}
