import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.lang.*;

/**
 * An implementation for Block
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
  Hash curHash;


  /*---------------------------
   * |      Constructor       |
   * --------------------------
   */

  /*
   * Block constructor that requires mining for a nonce
   */
  public Block(int num, int amount, Hash prevHash) {
    this.blockNum = num;
    this.amount = amount;
    this.prevHash = prevHash;

    //perform the mining operation for nonce and hash
    try {
      MessageDigest md = MessageDigest.getInstance("sha-256");
      this.curHash = generateValidNonce(md);
    } catch (Exception e) {
      System.err.println("Cannot mine valid nonce! : " + e.getMessage());
    }
  } // Block (int, amount, Hash)

  /*
   * Block constructor that is given a nonce
   */
  public Block(int num, int amount, Hash prevHash, long nonce){
    this.blockNum = num;
    this.amount = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;
    try {
      MessageDigest md = MessageDigest.getInstance("sha-256");
      this.curHash = new Hash(generateGivenNonce(md, nonce));
    } catch (Exception e) {
      System.err.println("Cannot generate hash! : " + e.getMessage());
    }
  } // Block (int, int, Hash, long)


  /*---------------------------
   * |       Methods          |
   * --------------------------
   */

  /*
   * getNum()
   * returns the block num
   */
  public int getNum(){
    return this.blockNum;
  } // getNum()

    /*
     * getAmount()
     * returns the amount transfered that is recorded in this block
     */
    public int getAmount(){
        return this.amount;
    } // getAmount()
    
    /*
     * getNonce()
     * returns the nonce of this block
     */
    public long getNonce(){
      return this.nonce;
    } // getNonce()

    /*
     * getPrevHash()
     * returns the hash of the previous block in the blockchain
     */
    public Hash getPrevHash(){
      return this.prevHash;
    } // getPrevHash ()

    /*
     * getHash()
     * returns the hash of this block
     */
    public Hash getHash() {
      return this.curHash;
    } // getHash ()

    private Hash generateValidNonce(MessageDigest md) throws NoSuchAlgorithmException {
      long i = 0;
      Hash tmpHash = new Hash(generateGivenNonce(md, i));
      while(!tmpHash.isValid()) {
        tmpHash = new Hash(generateGivenNonce(md, ++i));
      }//while
      this.nonce = i;
      return tmpHash;
    } // mine ()

    private byte[] generateGivenNonce(MessageDigest md, long nonce){
      md.reset();
      md.update(ByteBuffer.allocate(4).putInt(this.getNum()).array());
      md.update(ByteBuffer.allocate(4).putInt(this.getAmount()).array());
      if (getPrevHash() != null)
        md.update(getPrevHash().toString().getBytes()); // Only when prev exist
      md.update(ByteBuffer.allocate(8).putLong(nonce).array());
      byte[] temp = md.digest();
      md.reset();
      return temp;
    } // generateGivenNonce(long)

    /*
     * toString()
     * returns a string representation of the block
     */
    public String toString(){
      return "Block " + this.blockNum + " (Amount: " + this.amount + 
      ", Nonce: " + this.nonce + ", prevHash: " + this.prevHash + ", hash: "
      + this.curHash + ")";
    } // toString()
}
