package src;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.lang.*;

/**
 * <Description>
 *
 * @author Joyce Gill
 * @author Maria Rodriguez
 */
public class Block {
    
/*---------------------------
 * Fields |
 * --------------------------
 */

    //the number of the block in the blockchain
    int blockNum;

    //the amt transferred between two parties
    int amount;

    //the hash of the prev. block
    Hash prevHash;

    //the nonce
    long nonce;

    //the hash of this block
    Hash hash;


/*---------------------------
 * Constructor |
 * --------------------------
 */

    /*
     * Block constructor that requires mining for a nonce
     */
    public Block(int num, int amount, Hash prevHash){
        this.blockNum = num;
        this.amount = amount;
        this.prevHash = prevHash;

        //perform the mining operation for nonce and hash

    }

    /*
     * Block constructor that is given a nonce
     */
    public Block(int num, int amount, Hash prevHash, long nonce){
        this.blockNum = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;

    }


/*---------------------------
 * Methods |
 * --------------------------
 */

    /*
     * getNum()
     * returns the block num
     */
    public int getNum(){
        return this.blockNum;
    }

    /*
     * getAmount()
     * returns the amount transfered that is recorded in this block
     */
    public int getAmount(){
        return this.amount;
    }
    
    /*
     * getNonce()
     * returns the nonce of this block
     */
    public long getNonce(){
        return this.nonce;
    }

    /*
     * getPrevHash()
     * returns the hash of the previous block in the blockchain
     */
    public Hash getPrevHash(){
        return this.prevHash;
    }

    /*
     * getHash()
     * returns the hash of this block
     */
    public Hash getHash(){
        return this.hash;
    }

    /*
     * toString()
     * returns a string representation of the block
     */
    public String toString(){
        return "Block " + this.blockNum + " (Amount: " + this.amount + 
        ", Nonce: " + this.nonce + ", prevHash: " + this.prevHash + ", hash: "
        + this.hash + ")";
    }
}
